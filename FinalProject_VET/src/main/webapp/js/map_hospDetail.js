var hosp_addr = document.getElementById("hosp_addr");
var hosp_name = document.getElementById("hosp_name");
var addr = document.getElementById("addr");
var hospitalName = document.getElementById("hospitalName");
//console.log(hosp_addr.innerHTML);
//--------------------------------------------------------------------------------------------
$(document).ready(function() {
      // 카카오 지도 초기화
      var mapContainer = document.getElementById('map');
      var mapOptions = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 초기 중심 좌표
        level: 2 // 지도 확대 레벨
      };
      var map = new kakao.maps.Map(mapContainer, mapOptions);

      // 주소를 좌표로 변환
      var address = document.getElementById("hosp_addr").innerHTML;
      console.log(address)
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(address, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 마커 생성
          var marker = new kakao.maps.Marker({
            position: coords,
            map: map
          });

          // 지도 중심을 마커로 이동
          map.setCenter(coords);
        }
      });

	//북마크 등록 여부 판단   
  	var hosp_id = document.getElementById("hosp_id").value;
  	console.log(hosp_id);
      $.ajax({
		url:"./bookMarkYorN.do",
		method:"post",
		data:{"bm_hospid":hosp_id},
		success:function(data){
			console.log("북마크에 존재? : "+data);
			if(data == "true"){
				$("#title button").attr('id','bookmarkPart_yes');
			}else if(data =="false"){
				$("#title button").attr('id','bookmarkPart_no');
			}
		},
		error:function(){
		}
	});
  });
//--------------------------------------------------------------------------------------------

//console.log(hosp_name.innerHTML);
//console.log(addr.innerHTML);

var hosp_time = document.getElementById("hosp_time");
if(hosp_time != null){
//	console.log(typeof(hosp_time.innerHTML));
	hosp_time.style.display = "none";
	var hosp_runTime = JSON.parse(hosp_time.innerHTML);
	console.log(hosp_runTime);
	var hosp_runtime = document.getElementById("hosp_runtime");
	hosp_runtime.innerHTML = hosp_runTime.open +"~"+ hosp_runTime.close + "시";
}

function resrv_request(value){
	console.log(value);
	var url = "./resrv_requestPage.do?resrv_hops="+value;
	var title = "예약신청 페이지";
	var prop = "top=100px, left=100px, width=500px, height=600px";
	window.open(url, title, prop);
}

//--------------------------------------------------------------------------------
//북마크관련
$(document).on("click","#title button",function(){
		var bookmarkStatus = $("#title button").attr('id');
		var hosp_id = $("#title button").val();
		console.log(bookmarkStatus);
		console.log(hosp_id);
		
		if(bookmarkStatus == "bookmarkPart_no"){
			console.log("북마크 등록 실행");
			var result = confirm("즐겨찾기에 등록하시겠습니까?");
			if(result){
				$.ajax({
					url:"./insertNewBookmark.do",
					method:"post",
					data:{"bm_hospid":hosp_id},
					success:function(data){
						console.log("등록성공");
						if(data == "true"){
							$("#title button").attr('id','bookmarkPart_yes');
							alert("즐겨찾기에 등록되었습니다.");
							location.reload();
						}else if(data == "false"){
							alert("즐겨찾기는 3개까지 등록가능합니다.");
							location.reload();
						}
					},
					error:function(){
						console.log("실패");
					}
				});
			}
			
		}else if(bookmarkStatus == "bookmarkPart_yes"){
				var result = confirm("즐겨찾기를 해제하시겠습니까?");
				if(result){
					$.ajax({
						url:"./deleteBookmark.do",
						method:"post",
						data:{"bm_hospid":hosp_id},
						success:function(){
							console.log("삭제성공");
							$("#title button").attr('id','bookmarkPart_no');
							alert('즐겨찾기 해제되었습니다.');
							location.reload();
						},
						error:function(){
							console.log("실패");
						}
					});
					
				}
			}
		
});