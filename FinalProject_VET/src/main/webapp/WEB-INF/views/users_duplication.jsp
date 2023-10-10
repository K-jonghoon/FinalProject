<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복검사</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="./js/users_insertUsersStepTwo.js"></script>
<script type="text/javascript" src="./js/users_insertHospStepTwo.js"></script>
</head>
<body>
	<div class="container">
		<h4>이메일 중복 확인</h4>
		<h5>이메일을 입력해 주세요</h5>
		<input type="text" id="users_id">
		<input type="button" value="확인" onclick="checkEmail()">
		<input type="button" value="사용하기" id="btnUseEmail" onclick="useEmail()">
	</div>
	<div id="condition"></div>
</body>
</html>