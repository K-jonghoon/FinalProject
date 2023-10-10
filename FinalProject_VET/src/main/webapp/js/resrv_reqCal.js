document.addEventListener('DOMContentLoaded', function() {
	$(function() {
		var request = $.ajax({
			url: "./resrv_requestAjax.do",
			method: "get",
			dataType: "json"
		});

		request.done(function(data) {
			console.log(data); // 가져온 일정
			
			var today = new Date(); // 오늘 날짜 가져오기
			var oneDayBefore = new Date(today);
			oneDayBefore.setDate(today.getDate()-1);
			
			var calendarEl = document.getElementById('calendar'); //캘린더 뿌려질 위치
			var resrv_availableTime = document.getElementById('resrv_availableTime'); //캘린더 뿌려질 위치
			var hosp_run = document.getElementById("hosp_run");
			if(hosp_run != null){
				hosp_run.style.display = "none";
			}
			
		var calendar = new FullCalendar.Calendar(calendarEl, {
				initialDate: today,
				editable: false, // 일정 수정 기능 못쓰게
				selectable: true, //일자 선택 가능
				businessHours: true, //업체 휴무일 가져오기 true
				locale: "ko",
				dayCellDidMount: function(info) {
					if (info.date < oneDayBefore) {
						var bgColor = 'rgba(153, 153, 153, 0.3)';
						info.el.style.backgroundColor = bgColor;
					}
				},
				dateClick: function(info) {
					if (info.date < oneDayBefore) {
						resrv_availableTime.innerHTML="";
					}else{
						console.log(info);
						if(data.length == 0){
	//						console.log(hosp_run.innerHTML);
							var hosp_runJson = JSON.parse(hosp_run.innerHTML);
	//						console.log(hosp_runJson.open);
							var openTime = parseInt(hosp_runJson.open);
							var closeTime = parseInt(hosp_runJson.close);
							var html ="";
	//						hosp_run.innerHTML="";
							resrv_availableTime.innerHTML="";
							for(let i=openTime; i<closeTime; i++){
								console.log(i)
								if(i<10){
									html +="<button id='time"+i+"' value='0"+i+":00' onclick='timeInsert(this.value)'>0"+i+":00</button>";
								}else{
									html +="<button id='time"+i+"' value='"+i+":00' onclick='timeInsert(this.value)'>"+i+":00</button>";
								}
							}
							resrv_availableTime.innerHTML=html;
							
							for(let i=0; i<data.length; i++){
								if(data[i].start.substr(0,10) == info.dateStr){
									console.log("선택한 날짜의 예약된 시간: ",data[i].start.substring(11,13));
									var sub_time = data[i].start.substring(11,13);
									// 예약된 시간 - 버튼 비활성화
									document.getElementById("time"+sub_time+"").disabled = "disabled";
								}else{
									
								}
							}
							document.getElementById("select_date").value = info.dateStr;
							document.getElementById("select_time").value = "-- : --";
						}else{
							var openTime = parseInt(JSON.parse(data[0].hosp_time).open);
							var closeTime = parseInt(JSON.parse(data[0].hosp_time).close);
							var html = "";
	//						console.log(data[0].start.substr(0,10));
							console.log(info.dateStr);
							
							resrv_availableTime.innerHTML="";
							for(let i=openTime; i<closeTime; i++){
								if(i<10){
									html +="<button id='time0"+i+"' value='0"+i+":00' onclick='timeInsert(this.value)'>0"+i+":00</button>";
								}else{
									html +="<button id='time"+i+"' value='"+i+":00' onclick='timeInsert(this.value)'>"+i+":00</button>";
								}
							}
							resrv_availableTime.innerHTML=html;
							
							for(let i=0; i<data.length; i++){
								if(data[i].start.substr(0,10) == info.dateStr){
									console.log("선택한 날짜의 예약된 시간:",data[i].start.substring(11,13));
									var sub_time = data[i].start.substring(11,13);
									// 예약된 시간 - 버튼 비활성화
									if(i<10){
										document.getElementById("time"+sub_time+"").disabled = "disabled";
									}else{
										document.getElementById("time"+sub_time+"").disabled = "disabled";
									}
								}else{
									
								}
							}
							document.getElementById("select_date").value = info.dateStr;
							document.getElementById("select_time").value = "-- : --";
						}
					}
//					
					
				},
				headerToolbar: {
					left: '',
					center: 'title',
					right: 'prev,next today'
				},
				
			});
			
			calendar.render();
			
		});
		
		request.fail(function() {
			alert("가져올 정보가 없습니다. 다시 로그인해주세요");
			location.href="./loginForm.do";
		});
	});

});


function timeInsert(time){
	document.getElementById("select_time").value = time;
}