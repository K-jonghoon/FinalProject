<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 북마크 리스트</title>
<link rel="stylesheet" href="./css/chart_detail.css">
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
		<h3 id="listname">즐겨찾기 병원</h3>
		<table id="chartInfo">
			<c:choose>
				<c:when test="${empty lists[0].hospital_vo}">
					<p>등록된 즐겨찾기가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="list" items="${lists}" varStatus="vs">
						<c:forEach var="hlist" items="${list.hospital_vo}">
							<tr>
								<th>&emsp;&emsp;${vs.count}<th>
								<td>${hlist.hosp_name}</td>
								<td>
									<button onclick="location.href='./select_HospDetail.do?hosp_id=${hlist.hosp_id}'">예약하기</button>
								</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>