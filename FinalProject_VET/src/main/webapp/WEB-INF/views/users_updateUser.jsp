<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정(일반사용자)</title>
<link rel="stylesheet" href="./css/chart_detail.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
	#buttonArea{
		display: block;
		text-align: center;
	}
</style>
</head>
<body>
<%@ include file="./navbar.jsp" %>
<div class="navContainer">
	<h3>내 정보 수정</h3>
	<form action="./updateUser.do" method="post">
			<table id="chartInfo">
				<tr>
				<th>ID</th>
					<td> &nbsp;&nbsp; <input class="form-control" type="text" name="users_id" value="${lists[0].users_id}" readonly="readonly"></td>
				</tr>
				<tr>
				<th>이름</th>
					<td> &nbsp;&nbsp; <input class="form-control" type="text" name="users_name" id="users_name" value="${lists[0].users_name}" required="required"></td>
				</tr>
				<tr>
				<th>전화번호</th>
					<td> &nbsp;&nbsp; <input class="form-control" type="text" name="users_tel" value="${lists[0].users_tel}" required="required"></td>
				</tr>
				<tr>
				<th>주소</th>
					<td> &nbsp;&nbsp; <input class="form-control" type="text" name="users_addr" value="${lists[0].users_addr}"></td>
				</tr>
				<tr>
				<th>가입일</th>
					<td> &nbsp;&nbsp; <input class="form-control" type="text" name="users_joindate" value="${lists[0].users_joindate}" readonly="readonly"></td>
				</tr>
				<tr>
				<th>추가 연락처</th>
					<td> &nbsp;&nbsp; <input class="form-control" type="text" name="users_subtel" value="${lists[0].users_subtel}"></td>
				</tr>
			</table>
		<br>
		<div id="buttonArea">
			<input type="submit" value="수정">
			<input type="button" value="취소" onclick="location.href='./selectOneDetail.do'">
		</div>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var users_name = document.getElementById("users_name");
		users_name.focus();
	});
</script>

</body>
<%@ include file="./footer.jsp" %>
</html>