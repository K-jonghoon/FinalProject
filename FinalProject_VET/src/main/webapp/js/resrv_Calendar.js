function resrv_calendar(){
	console.log("예약현황 호출");
	var calendar= document.getElementById("calendar");
	var myChart= document.getElementById("myChart");
	var waitList= document.getElementById("waitList");
	calendar.style.display='block';
	myChart.style.display='none';
	waitList.style.display='none';
	$("#hosp_infomation").hide();
	$("#hosp_modifyArea").hide();
	$("#listname").hide();
	$("#calhead").show();

	
	$(function() {
		var request = $.ajax({
			url: "./fullCalendar.do",
			method: "GET",
			dataType: "json"
		});

		request.done(function(data) {
			console.log(data); // 가져온 일정
			var today = new Date(); // 오늘 날짜 가져오기
			var calendarEl = document.getElementById('calendar'); //캘린더 뿌려질 위치
			
			var calendar = new FullCalendar.Calendar(calendarEl, {
				initialDate: today,
				editable: false, // 일정 수정 기능 못쓰게
				selectable: true, //일자 선택 가능
				businessHours: true, //업체 휴무일 가져오기 true
				dayMaxEvents: true, //이벤트 너무 많으면 '더 보기' 형태로 출력되게
				navLinks: true, // 날짜 선택시 Day 캘린더나 Week 캘린더로 링크
				locale: "ko",
				headerToolbar: {
					left: 'dayGridMonth,dayGridWeek,listWeek',
					center: 'title',
					right: 'prev,next today'
				},
				eventClick: function(info) {
//				    console.log("일정 클릭이벤트 :",info.event.title);
//				    console.log("일정 클릭이벤트 :",info.event.start);
				    console.log("일정 클릭이벤트 :",info.event._def.extendedProps.resrv_num);
				    $("#resrv_detailModal").modal("show");
				    var modalInput = $("#resrv_detailModal input");
//				    console.log(modalInput.length);
				    modalInput.attr("disabled", true);
 					
				    $.ajax({
						url:"./resrv_detailAjax.do",
						method:"get",
						data:{resrv_num : info.event._def.extendedProps.resrv_num},
						dataType:"json",
						success:function(detail){
							console.log(detail);
//							console.log(detail.detail.resrv_time);
							var resrv_t = detail.resrv_time.concat(":00");
		                    $("#resrv_num").val(detail.resrv_num);
		                    $("#resrv_visit").val(detail.resrv_visit);
		                    $("#resrv_time").val(resrv_t);
		                    $("#resrv_name").val(detail.resrv_name);
		                    $("#resrv_tel").val(detail.resrv_tel);
		                    $("#resrv_memo").val(detail.resrv_memo);
		                    $("#resrv_hosp").val(detail.resrv_hosp);
		                   
 							$(".modal-footer button").hide();
		                    var sysdate = new Date();
		                    var detailDate = new Date(detail.resrv_visit);
//							console.log(sysdate);
//							console.log(detailDate);
							if(detailDate>sysdate){
								$(".modal-footer button").eq(0).show();
							}
						},
						error:function(){
							alert("호출 실패");
						}
					});
				},
				events: data
				
			});
			
			calendar.render();
			
		});
		
		request.fail(function() {
			alert("요청 실패 : 다시 로그인 해주세요");
			location.href="./loginForm.do";
		});
	});

}

function resrv_modify(){
//	console.log($(".form-group input").eq());
	var resrv_detailInputs = $(".form-group input");
//	$(".form-group input").eq(0).attr("disabled",false);
	for(let i=3; i<resrv_detailInputs.eq().prevObject.length; i++){
		resrv_detailInputs.eq(i).attr("disabled",false);
	}
	$("#resrv_modifyBtn").css("display","none");
	$("#resrv_saveBtn").css("display","block");
}
function resrv_save(){
	var resrv_num = $("#resrv_num").val();
	var resrv_visit =$("#resrv_visit").val();
	var resrv_time =$("#resrv_time").val();
	var resrv_name =$("#resrv_name").val();
	var resrv_tel =$("#resrv_tel").val();
	var resrv_memo =$("#resrv_memo").val();
	var nowDate = new Date();
	console.log(nowDate);
	var detailDate = new Date(resrv_visit);
	console.log(detailDate);
	if(!resrv_visit || !resrv_time || !resrv_name || !resrv_tel){
		alert("예약날짜, 시간, 이름, 전화번호는 필수 입력값입니다. ");
		$("#resrv_name").focus();
	}else{
		$.ajax({
			url:"./resrv_detailModify.do",
			method:"post",
			data:{
				resrv_num:resrv_num,
				resrv_visit:resrv_visit,
				resrv_time:resrv_time,
				resrv_name:resrv_name,
				resrv_tel:resrv_tel,
				resrv_memo:resrv_memo
				},
			success:function(result){
				console.log(result);
				if(result == "true"){
					$("#resrv_detailModal").modal("hide");
					alert("예약 수정 완료");
					resrv_calendar();
				}else{
					alert("수정 실패");
					location.reload();
				}
			},
			error:function(){
				alert("호출 실패")
			}
		});
	}
	
	
}

window.onload = function(){
	$("#calhead").hide();
}



