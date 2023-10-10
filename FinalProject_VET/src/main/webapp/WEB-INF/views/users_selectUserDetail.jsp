<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세조회 - 관리자페이지에서 넘어옴</title>
<link rel="stylesheet" href="./css/users_adminDetail.css">
</head>
<body>
<%@ include file="./header.jsp" %>
<div id="container">
<%-- ${loginVo} --%>
<%-- ${usersDetail} --%>
<%-- ${hospDetail} --%>
<h3>회원 상세 조회</h3>
<table id="chartInfo">
	<tbody>
		<c:if test="${usersDetail[0].users_auth eq 'U'}">
			<tr>
					<th>아이디</th>
					<td>${usersDetail[0].users_id}</td>
					</tr>
					<tr>
					<th>이름</th>
					<td>${usersDetail[0].users_name}</td>
					</tr>
					<tr>
					<th>전화번호</th>
					<td>${usersDetail[0].users_tel}</td>
					</tr>
					<tr>
					<th>상태</th>
					<c:choose>
							<c:when test="${usersDetail[0].users_status eq 'N'}"><td>활동중</td></c:when>
							<c:when test="${usersDetail[0].users_status eq 'S'}"><td>활동중지</td></c:when>
							<c:otherwise><td>탈퇴</td></c:otherwise>
					</c:choose>
					</tr>
					<tr>
					<th>가입일</th>
					<td>${usersDetail[0].users_joindate}</td>
					</tr>
					<tr>
					<th>추가연락처</th>
					<td>${usersDetail[0].users_subtel}</td>
					</tr>
					<tr>
					<th>주소</th>
					<td>${usersDetail[0].users_addr}</td>
			</tr>
		</c:if>
		<c:if test="${hospDetail.users_auth eq 'H'}">
			<tr>
				<th>아이디</th>
				<td>${hospDetail.users_id}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${hospDetail.users_name}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${hospDetail.users_tel}</td>
			</tr>
			<tr>
				<th>상태</th>
				<c:choose>
						<c:when test="${hospDetail.users_status eq 'N'}"><td>활동중</td></c:when>
						<c:when test="${hospDetail.users_status eq 'S'}"><td>활동중지</td></c:when>
						<c:otherwise><td>탈퇴</td></c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th>가입일</th>
				<td>${hospDetail.users_joindate}</td>
			</tr>
			<tr>
				<th>추가연락처</th>
				<td>${hospDetail.users_subtel}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${hospDetail.users_addr}</td>
		</tr>
		</c:if>
	</tbody>
</table>
	
	<button class="buttons" id="backButton" onclick="history.back(-1)">뒤로가기</button>
<!-- 	<button class="buttons" onclick="location.href='./modifyUser.do'">정보수정</button> -->

</div>
</body>
<%@ include file="./footer.jsp" %>
</html>