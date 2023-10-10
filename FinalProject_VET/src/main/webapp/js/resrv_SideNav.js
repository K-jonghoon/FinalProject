
function month_count(){
	console.log("월별 예약건수 호출");
	var calendar= document.getElementById("calendar");
	var myChart= document.getElementById("myChart");
	var waitList= document.getElementById("waitList");
//	console.log(calendar.innerHTML);
	calendar.style.display='none';
	waitList.style.display='none';
	myChart.style.display='block';
	$("#hosp_infomation").hide();
	$("#hosp_modifyArea").hide();
	$("#calhead").hide();
	
	var date = new Date();
	var year = date.getFullYear();
	
	$.ajax({
		url:"./resrv_monthCount.do",
		method:"POST",
		dataType:"json",
		data:{yyyy:year},
		success:function(data){
//			console.log(data.lists);
			var html = "";
				html += "<h2 id='listname'>월별 예약 건수</h2>";
			month_cnt.innerHTML=html;
			
				var monthList = [];
				var monthData = [];
				
				for(let i=0; i<data.lists.length; i++){
					monthList.push(data.lists[i].MM);
					monthData.push(data.lists[i].Y_COUNT);
				}
				console.log(monthList);
				console.log(monthData);
				
				const ctx = document.getElementById('myChart').getContext('2d');
				const myChart = new Chart(ctx, {
					type: 'line',
					data: {
						labels: monthList,
						datasets: [{
							label:year+"년도 예약 건수" ,
							data: monthData,
							borderColor:"#3E2723",
							backgroundColor:"rgba(239,235,233,0.7)",
							pointBorderWidth: 2
						}]
					},
	
					options: {
					   responsive: true,
					   plugins: {
					      legend: {
						  display: true
						  }
					   }
					}
				});
				console.log(myChart);
			
		},
		error:function(){
			alert("호출 에러");
		}
	});
};

function resrv_wList(){
	console.log("예약승인 대기명단 호출");
	var calendar= document.getElementById("calendar");
	var myChart= document.getElementById("myChart");
	var waitList= document.getElementById("waitList");
	calendar.style.display='none';
	myChart.style.display='none';
	waitList.style.display='block';
	$("#hosp_infomation").hide();
	$("#hosp_modifyArea").hide();
	$("#listname").hide();
	$("#calhead").hide();
	
	$.ajax({
		url:"./resrv_waitLists.do",
		method:"post",
		dataType:"json",
		data:{resrv_status:"W"},
		success:function(data){
			var html = "";
				html += "<h2 id='listname'>예약대기 명단</h2>"
				html += "<table>";
				html += "	<tr>";
				html += "		<th>예약번호</th>";
				html += "		<th>예약날짜</th>";
				html += "		<th>예약시간</th>";
				html += "		<th>예약이름</th>";
				html += "		<th>예약상태</th>";
				html += "		<th>접수시간</th>";
				html += "		<th>예약승인</th>";
				html += "		<th>예약거절</th>";
				html += "	</tr>";
				for(var i=0; i<data.length; i++){
				html += "	<tr>";
				html += "		<td>"+data[i].resrv_num+"</td>";
				html += "		<td>"+data[i].resrv_visit+"</td>";
				html += "		<td>"+data[i].resrv_time+"</td>";
				html += "		<td>"+data[i].resrv_name+"</td>";
					if(data[i].resrv_status=="W"){
				html += "		<td>예약대기</td>";
					}else{
				html += "		<td>예약확정</td>";	
					}					
				html += "		<td>"+data[i].resrv_regdate+"</td>";
				html += "		<td><button class='resrv_confirm'>확정</button></td>";
				html += "		<td><button class='resrv_refuse' value='"+data[i].resrv_userid+"'>거절</button></td>";
				html += "	</tr>";
				}
				html += "</table>";
			waitList.innerHTML=html;
		},
		error:function(){
			alert("호출 에러");
		}
	});
}

$(document).on("click",".resrv_confirm",function(){
	console.log("resrv_confirm 확정버튼 작동");
	resrv_num = this.parentNode.parentNode.children[0].innerText;
	console.log(resrv_num);
	resrv_refuseBtn = this.parentNode.parentNode.children[7].children[0];
	console.log(resrv_refuseBtn);
	resrv_btn = this;
	console.log(resrv_btn);
//	console.log(resrv_num);
	$.ajax({
		url:"./resrv_confirm.do",
		method:"post",
		data:"resrv_num="+resrv_num,
		success:function(result){
			console.log(result);
			if(result == "confirm"){
				resrv_btn.style.display="none";
				resrv_refuseBtn.style.display="none";
				resrv_btn.parentNode.innerHTML="확정";
			}else{
				alert("예약 확정을 할 수 없습니다.");
			}
		},
		error:function(){
			alert("예약 확정을 할 수 없습니다.");
		}
	});
});

$(document).on("click",".resrv_refuse",function(){
	console.log(this.value);
	console.log("resrv_refuse 거절버튼 작동");
	resrv_num = this.parentNode.parentNode.children[0].innerText;
	console.log(resrv_num);
	resrv_confirmBtn = this.parentNode.parentNode.children[6].children[0];
	console.log(resrv_confirmBtn);
	resrv_btn = this;
	if(confirm("예약을 거절하시겠습니까?")){
		$.ajax({
			url:"./resrv_refuse.do",
			method:"post",
			data:{
				resrv_num:resrv_num,
				user_id:this.value
				}, 
			success:function(result){
				console.log(result);
				if(result == "resrv_refuse"){
					resrv_btn.style.display="none";
					resrv_confirmBtn.style.display="none";
					resrv_btn.parentNode.innerHTML="거절";
				}else{
					alert("예약 확정을 할 수 없습니다.");
				}
			},
			error:function(){
				alert("예약 확정을 할 수 없습니다.");
			}
		});
	}else{
		return false;
	}
});

function hosp_info(){
	$("#calendar").hide();
	$("#myChart").hide();
	$("#waitList").hide();
	$("#hosp_infomation").show();
	$("#hosp_modifyArea").show();
	$("#listname").hide();
	$("#calhead").hide();
}


