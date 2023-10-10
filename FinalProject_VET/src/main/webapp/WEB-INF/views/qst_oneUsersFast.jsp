<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>빠른문의리스트</title>
<link rel="stylesheet" href="./css/chart_detail.css">
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div id="navContainer">
		<div class="navContainer">
			<h3 id="listname">빠른문의</h3>
			<c:choose>
				<c:when test="${empty lists}">
					<p>진료문의 내역이 없습니다.</p>
				</c:when>
				<c:otherwise>
						<table id="chartInfo">
							<tr>
								<th>제목</th>
								<th>작성일</th>
							</tr>
							<c:forEach var="list" items="${lists}">
								<tr>
									<td>${list.qst_title}</td>
									<td>${list.qst_regdate}</td>
								</tr>
							</c:forEach>
						</table>
				</c:otherwise>
			</c:choose>
		</div>
	<hr>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>