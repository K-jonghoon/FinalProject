<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<style type="text/css">
	input {
		width: 400px;
		height: 20px;
		margin: 3px;
	}
	.fixButton{
		width: auto;
		height: auto;
		padding-block: 1px;
    	padding-inline: 6px;
	}
	.container{
		text-align: center;
		height: 800px;
	}
</style>
<script type="text/javascript">
	function isSameId(){
	    var users_idOk = document.getElementsByName("users_idOk")[0].value;
	    var users_id = document.getElementsByName("users_id")[0].value;
	    
	    if(users_idOk != users_id){
			document.getElementById("checkId").innerHTML = "아이디가 일치하지 않습니다."
			return false;
		} else if (users_idOk == users_id) {
			document.getElementById("checkId").innerHTML = "아이디가 일치합니다."
			return false;
		}
	}
</script>
</head>
<%@ include file="./header.jsp" %>
<body>

	<div class="container">
	<h2>정말로 탈퇴하시겠습니까?</h2>
	<h3>탈퇴하시려면 아래 정보를 입력해주세요.</h3>
	<div style="display: flex; justify-content: center; align-items: center; margin-top: 50px;">
	<form action="./resignUser.do" method="post">
		<input type="text" name="users_idOk" value="${loginVo.users_id}" readonly="readonly"><br>
		<input type="text" name="users_id" placeholder="위와 동일한 아이디를 입력해주세요" required="required" onchange="isSameId()">
		<br>
		<span id="checkId"></span>
		<br>
		<input type="password" name="users_pw" placeholder="비밀번호를 입력해주세요" required="required"><br><br>
		<input type="submit" class="fixButton" value="회원탈퇴">
		<input type="button" class="fixButton" value="취소" onclick="history.back(-1)">
	</form>
	</div>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>