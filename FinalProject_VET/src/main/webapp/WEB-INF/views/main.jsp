<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<link rel="stylesheet" href="./css/main_Page.css">
<%@ include file="./header.jsp" %>
<body>
<div id="mainContainer">
		<div id="map_info">
			<img id="mapImage" alt="지도" src="./img/map-image.jpg">
			<div id="map_descript">
				<h2 class="titlePart">동물병원 찾기 &amp; 진료예약</h2>
				<p class="contentPart">내 주변, 지역별 동물병원을 빠르게 검색하고,<br>
					간편한 진료예약을 이용해보세요!
				</p>
			</div>
		</div>
		<div id="question_info">
			<img id="qnaImage" alt="진료문의" src="./img/qnaImage.png">
			<div id="question_descript">
				<h2 class="titlePart">진료문의</h2>
				<p class="contentPart">소중한 우리 반려동물 어디가 아픈지, 어떤 질병인지 궁금하시다면,<br> 
					진료문의를 통해 전문가의 빠르고 정확한 답변을 받아보세요!
				</p>
			</div>
		</div>
		<div id="notice_info">
			<div id="notice_descript">
				<div id="notice">
					<b>공지사항</b>
					<a href="#" id="noti_plus">더보기 +</a>
				</div>
				<table>
					<c:forEach var="noti" items="${notice_list}">
						<tr>
							<td>
								<fmt:parseDate var="notidate" value="${noti.noti_regdate}" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${notidate}" pattern="yyyy-MM-dd"/>
							</td>
							<td>${noti.noti_title}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div id="chosen_hosp">
			<div id="chosen_hosp_descript">
				<div id="chosen">
					<b>우수 답변 동물병원</b>
				</div>
				<table>
					<tr>
						<th>순위</th>
						<th>동물병원</th>
						<th>답변</th>
						<th>채택</th>
					</tr>
					<c:forEach var="rank" items="${chsn_list}" varStatus="vs">
							<tr>
								<th>${vs.count}.</th>
								<td>${rank.users_vo.users_name}</td>
								<td>${rank.rpy_cnt}</td>
								<td>${rank.rpy_chosen}</td>
							</tr>
					</c:forEach>
				</table>
			</div>
		</div>
</div>

</body>

<%@ include file="./footer.jsp" %>
</html>