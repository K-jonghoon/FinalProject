function showSelect(){
	
	$("#region_on").children().show();
}

function sidoChange(){
	var sido = document.getElementById("option_sido");
	var selectedSiDo = sido.options[sido.selectedIndex].value;
	console.log(selectedSiDo);
	$("#option_sigungu").show();
	$.ajax({
		url:"./siGunGuLists.do",
		method:"post",
		data:"si_do="+selectedSiDo,
		dataType:"json",
		success:function(data){
			var sigungu = document.getElementById("option_sigungu");
			var html="";
			if(selectedSiDo != "세종특별자치시"){
				html += "<option>--시/군/구--</option>";
			}
			for(let i=0; i<data.length; i++){
				
				if(selectedSiDo != "세종특별자치시"){
					html += "<option value='"+data[i].si_gun_gu+"'>"+data[i].si_gun_gu+"</option>";
				}else{
					$("#option_sigungu").hide();
				}
			}
			sigungu.innerHTML = html;		
		},
		error:function (){
			alert("시/군/구 호출실패");
		}
	});
}

function regionSearch(){
	var sido = document.getElementById("option_sido");
	var selectedSiDo = sido.options[sido.selectedIndex].value;
//	console.log(selectedSiDo);
	if(selectedSiDo=="세종특별자치시"){
		$("#map").hide();
		$("#regionMap").show();
		removeAllMarkers2();
		
		$.ajax({
				url:"./nationwideHosp.do",
				method:"post",
				data:{
					si_do : selectedSiDo
					},
				dataType:"json",
				success:function(hosp_data){
					console.log(hosp_data);
					for (var i = 0; i < hosp_data.length; i++) {
		                var item = hosp_data[i];
		                addMarkerInfo(item);
           			}
				},
				error:function(){
					alert("지역별 동물병원 호출 실패");
					location.href="./map.do";
				}
			});
	}else{
		var sigungu = document.getElementById("option_sigungu");
		var selectedsigungu = sigungu.options[sigungu.selectedIndex].value;
		console.log(selectedsigungu);
		if(selectedSiDo=="--시/도--" || selectedsigungu=="--시/군/구--"){
			alert("지역을 모두 선택해주세요");
		}else{
			$("#map").hide();
			$("#regionMap").show();
			removeAllMarkers2();
			
			$.ajax({
				url:"./nationwideHosp.do",
				method:"post",
				data:{
					si_do : selectedSiDo,
					si_gun_gu : selectedsigungu
					},
				dataType:"json",
				success:function(hosp_data){
					console.log(hosp_data);
					for (var i = 0; i < hosp_data.length; i++) {
		                var item = hosp_data[i];
		                addMarkerInfo(item);
           			}
				},
				error:function(){
					alert("지역별 동물병원 호출 실패");
					location.href="./map.do";
				}
			});
		}
	}
}

var mapContainer2 = document.getElementById("regionMap"),
	mapOption = {
	    center: new kakao.maps.LatLng(37.566826, 126.9786567),
	    level: 6
	};

var map2 = new kakao.maps.Map(mapContainer2, mapOption);
var customOverlay2 = new kakao.maps.CustomOverlay({}); //커스텀 마커 생성
var markers2 = []; // 마커를 관리하기 위한 배열

// 기존의 마커들을 모두 제거하는 함수
function removeAllMarkers2() {
    for (var i = 0; i < markers2.length; i++) {
        markers2[i].setMap(null);
    }
    markers2 = []; // 배열 초기화
}

function addMarkerInfo(data) {
    var geocoder = new kakao.maps.services.Geocoder();

    geocoder.addressSearch(data.소재지전체주소, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
	
//			console.log(data.소재지전체주소)
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            var marker = new kakao.maps.Marker({
                map: map2,
                position: coords
            });
            markers2.push(marker); // 배열에 마커 추가

            // 마커 클릭 시 동물병원의 주소와 상세내역 화면으로 이동
		    kakao.maps.event.addListener(marker, "click", function() {
				$.ajax({
					url:"./getHosp_id.do",
					method:"post",
					data:"users_addr="+data.소재지전체주소,
					success:function(hosp_id){
//						console.log(hosp_id);
						 var content = "";                   
				        	 content +='<div class="wrap">' ;
				             content +='    <div class="info">' ; 
				             content +='        <div class="title">'+data.사업장명; 
				             content +='            <div class="close" onclick="closeOverlay2()" title="닫기"></div>' ;
				             content +='        </div>' ;
				             content +='        <div class="body">' ;
				             content +='            <div class="desc">' ;
				             content +='                <div class="ellipsis"><b>'+ data.소재지전체주소 +'</b></div>' ; 
				             if(!hosp_id){
				              		content +='<div>홈페이지에 등록된 정보가 없습니다.</div>'
							 }else{
									content +='                <div><a href="./select_HospDetail.do?hosp_id='+hosp_id+'" class="link">병원 상세보기</a></div>' ;	
							 }
				             content +='            </div>' ;
				             content +='        </div>' ;
				             content +='    </div>' ; 
				             content +='</div>';
			
			        customOverlay2.setContent(content);
			        customOverlay2.setPosition(marker.getPosition());
			        customOverlay2.setMap(map2);
			        
					},
					error:function(){
						alert("호출에러");
						location.href="./main.do";
					}
				});
			
		    });
            
            //마커들 좌표의 최댓값,최솟값을 구하는 함수로 이동 
			setMapCenterPosition(map2, markers2);
        }
    });
}

function closeOverlay2() {
	console.log("닫기");
    customOverlay2.setMap(null);
}

// 모든 마커의 좌표 중 최솟값,최댓값 구하여 중심 좌표를 설정하는 함수
function setMapCenterPosition(map, markers) {
    if (markers.length === 0) {
        return;
    }
    var minLat = markers[0].getPosition().getLat();
    var maxLat = markers[0].getPosition().getLat();
    var minLng = markers[0].getPosition().getLng();
    var maxLng = markers[0].getPosition().getLng();
    // 최솟값과 최댓값 찾기
    for (var i = 1; i < markers.length; i++) {
        var position = markers[i].getPosition();
        var lat = position.getLat();
        var lng = position.getLng();

        if (lat < minLat) {
            minLat = lat;
        }
        if (lat > maxLat) {
            maxLat = lat;
        }
        if (lng < minLng) {
            minLng = lng;
        }
        if (lng > maxLng) {
            maxLng = lng;
        }
    }
    // 중심 좌표 설정
    var centerLat = (minLat + maxLat) / 2;
    var centerLng = (minLng + maxLng) / 2;
    var centerPosition = new kakao.maps.LatLng(centerLat, centerLng);

    map.setCenter(centerPosition);
}
