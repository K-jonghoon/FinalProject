<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/hosp_detail.css">
<title>병원 상세페이지</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=238e7e3447ac847cdba75075c7d23f2e&libraries=services,clusterer,drawing"></script>
</head>
<%@ include file="./header.jsp" %>
<body>
<div id="container">
	<div class="insertDetail">
	<input type="hidden" value="${hosp_info.hospital_vo[0].hosp_id}" id="hosp_id">
		<c:if test="${hosp_info.hospital_vo[0].hosp_time ne null}">
			<button id="hosp_time">${hosp_info.hospital_vo[0].hosp_time}</button>
		</c:if>
		<c:choose>
			<c:when test="${hosp_info ne null}">
			<h2>동물병원 상세정보</h2>
				<table id="hosp_detail">
					<tr>
						<th>병원이름</th>
						<td id="hosp_name">${hosp_info.hospital_vo[0].hosp_name}</td>
						<td id="map_table" rowspan="8">
							<div class="insertMap">
								<div id="title">
									<c:choose>
										<c:when test="${sessionScope.loginVo.users_auth eq 'U'}">
											<button id="bookmarkPart_no" value="${hosp_info.users_id}" style="border: none; background-color: white;"></button>
										</c:when>
									</c:choose>
								</div>
								<div id="map-box">
									<div id="map"></div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th>병원주소</th>
						<td id="hosp_addr">${hosp_info.users_addr}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${hosp_info.users_tel}</td>
					</tr>
					<tr>
						<th>운영시간</th>
						<td id="hosp_runtime"></td>
					</tr>
					<tr>
						<th>휴무일</th>
						<td>${hosp_info.hospital_vo[0].hosp_off}요일</td>
					</tr>
					<tr>
						<th>진료과목</th>
						<td>
							<c:forEach var="medi" items="${medi_lists}">
								<c:forEach var="name" items="${medi.medicode_vo}">
									<b>${name.medi_name}</b>
								</c:forEach>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>진료동물</th>
						<td>
							<c:forEach var="anm" items="${anm_lists}">
								<c:forEach var="species" items="${anm.animalcode_vo}">
									<b>${species.anm_species}</b>
								</c:forEach>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>주차</th>
						<c:choose>
							<c:when test="${hosp_info.hospital_vo[0].hosp_park eq 'Y'}">
								<td>가능</td>
							</c:when>
							<c:otherwise>
								<td>불가</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th>병원 소개</th>
						<td id="hosp_etc">${hosp_info.hospital_vo[0].hosp_etc}</td>
					</tr>
				</table>
				<div id="resrv_area">
					<c:choose>
						<c:when test="${sessionScope.loginVo.users_auth eq 'U'}">
							<button id="reservation" onclick="resrv_request(this.value)" value="${hosp_info.users_id}">예약하기</button>
						</c:when>
						<c:otherwise>
							<b>예약은 로그인 후 이용하실 수 있습니다.</b>
						</c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:otherwise>
				<div id="hosp_noInfo">
					<h3>등록된 병원정보가 없습니다.</h3>
					<table id="hosp_noInfo_table">
						<tr>
							<th>동물병원명 :</th>
							<td><p id="hospitalName">${placeName}</p></td>
						</tr>
						<tr>
							<th>주소 :</th>
							<td><p id="addr">${address}<p></td>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
<!-- 	<div id="staticMap" style="width:400px;height:300px; margin: 0px au"></div>  -->
</div>
<script type="text/javascript" src="./js/map_hospDetail.js"></script>
</body>
<%@ include file="./footer.jsp" %>
</html>