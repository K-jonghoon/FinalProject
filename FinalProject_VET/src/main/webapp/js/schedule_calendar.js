$(document).ready(function() {
	$(function(){
		var request = $.ajax({
			url:"./fullCalendar_sche.do",
			method: "get",
			dataType:"json"
		});
		
		request.done(function(data){
			console.log(data);
			var today = new Date();
			var calendarEl = document.getElementById('calendar');
			
			var calendar = new FullCalendar.Calendar(calendarEl,{
				initialDate: today,
				editable: false, 
				selectable: true, 
				dayMaxEvents: true,
				navLinks: true, 
				locale: "ko",
				headerToolbar: {
					left: 'dayGridMonth,addEventButton',
					center: 'title',
					right: 'prev,next today'
				},
				customButtons: {
					addEventButton:{
						text: "일정 추가",
						click : function(){
							console.log("일정추가 실행");
							$("#calendarModal").modal("show");
							
							$("#addCalendar").on("click",function(){
								var title = $("#sche_title1").val();
								var date = $("#sche_date1").val();
								var content = $("#sche_content1").val();
								var timeArray = $("#sche_time1").val().split(":");
								var hour = timeArray[0];
								var minute = timeArray[1];
								var obj = {
										"sche_title" : title,
										"sche_date" : date,
										"sche_content" : content,
										"sche_hour" : hour,
										"sche_minute" : minute
									}
								console.log(obj);
							});
							
							$("#addCalendar").on("click",function(){
								var title = $("#sche_title1").val();
								var date = $("#sche_date1").val();
								var content = $("#sche_content1").val();
								var time = $("#sche_time1").val();
								var timeArray = time.split(":");
								var hour = timeArray[0];
								var minute = timeArray[1];
								
								if(!title){
									alert("일정을 입력해주세요")
									$("#sche_title1").focus();
									return false;
								}else if(!date){
									alert("날짜를 입력해주세요")
									$("#sche_date1").focus();
									return false;
								}else if(!time){
									alert("시간을 입력해주세요")
									$("#sche_time1").focus();
									return false;
								}
								
								console.log("추가버튼 실행");
								$.ajax({
									url:"./insertNewSchedule.do",
									method: "post",
									data:{
										sche_title : title,
										sche_date : date,
										sche_content : content,
										sche_hour : hour,
										sche_minute : minute
									},
									success:function(){
										console.log("일정등록 성공");
										alert("일정이 등록되었습니다");
										location.href='./selectAllSchedule.do';
									},
									error:function(){
										alert("일정등록이 실패했습니다");
										location.href='./selectAllSchedule.do';
										console.log("일정등록 실패");
									}
								});
							});
						}
					} 
				},
				
				eventClick: function(info) {
					$("#detailModal").modal("show");
					var inputs = $("#modal_modify input");
//					console.log(inputs.length);
					inputs.attr("disabled", true);

					$.ajax({
						url:"./selectOneSchedule.do",
						method:"post",
						data:{sche_num: info.event._def.extendedProps.sche_num},
						dataType:"JSON",
						success:function(data){
							console.log(data);
							var sche_hour = data.svo.sche_hour;
							var sche_minute = data.svo.sche_minute;
							var sche_time = sche_hour.concat(":").concat(sche_minute);
							$("#sche_num").val(data.svo.sche_num);
							$("#sche_title").val(data.svo.sche_title);
							$("#sche_date").val(data.svo.sche_date);
							$("#sche_time").val(sche_time);
							$("#sche_content").val(data.svo.sche_content);
							console.log($("#sche_title").val());
							if($("#sche_title").val()=="진료예약"){
								$("#modifyCalendar").hide();
								$("#delCalendar").hide();
								$("#writeChart").show();
							}
						},
						error:function(){
							alert("전송오류입니다");
							location.href='./selectAllSchedule.do';
						}
					});
				},
				events: data
			});
			calendar.render();
		});
		request.fail(function(textStatus) {
			alert("요청 실패 : " + textStatus);
			location.href="./loginForm.do";
		});
	});
});


function modifySchedule(){
	console.log($("#modal_modify input").eq(0));
	$("#modal_modify input").eq(0).attr("disabled", false);
	$("#modal_modify input").eq(3).attr("disabled", false);
	$("#modal_modify input").eq(0).focus();
	
	$("#modifyCalendar").on("click", function(){
		$.ajax({
			url:"./modifySchedule.do",
			method:"post",
			data:{
				sche_title  : $("#modal_modify input").eq(0).val(),
				sche_content : $("#modal_modify input").eq(3).val(),
				sche_num : $("#sche_num").val()
			},
			dataType:"JSON",
			success:function(data){
				console.log("수정성공",data);
				$("#detailModal").modal("hide");
				location.href='./selectAllSchedule.do';
			},
			error:function(){
				alert("전송오류입니다");
				location.href='./selectAllSchedule.do';
			}
		});
	});
}

function deleteSchedule(){
	console.log("삭제실행, 삭제할 번호 : ",$("#sche_num").val());
	$.ajax({
		url:"./deleteSchedule.do",
		method:"post",
		data:{sche_num:$("#sche_num").val()},
		success:function(){
			if(confirm('삭제하시겠습니까?')==true){
				console.log("삭제");
				location.href='./selectAllSchedule.do';
			}
		},
		error:function(){
			alert("전송오류입니다");
			location.href='./selectAllSchedule.do';
		}
	});
}

function insertChart(){
	console.log("진료기록 작성 연동");
	
	var sche_content = $("#sche_content").val();
	var sche_date = $("#sche_date").val();
	console.log(sche_content,sche_date);
	$("#detailModal").attr("data-bs-dismiss", "modal");
	location.href='./insertNewChartForm.do?sche_content='+sche_content+'&sche_date='+sche_date;

}

function closeModal(){
	location.reload();
}

