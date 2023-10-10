<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step03(병원)</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="./js/users_insertUsersStepThree.js"></script>
<script>
   
</script>
</head>
<%@ include file="./header.jsp" %>

<style type="text/css">
	h2{
		text-align: center;
	}
	h3 {
		text-align: center;
	}
	button {
		text-align: center;
	}
</style>
<body>

<div id="container">

<h2>가입을 축하드립니다!</h2>
<h3>로그인 후 사이트를 이용해보세요</h3>
<button onclick="location.href='./loginForm.do'" id="goToQuestBoard">로그인 하기</button>

</div>

</body>
<%@ include file="./footer.jsp" %>
</html>