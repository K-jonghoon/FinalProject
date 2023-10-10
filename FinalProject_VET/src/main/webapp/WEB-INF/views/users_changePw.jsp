<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<script type="text/javascript">
	function isSame() {
	    var users_pw = document.getElementById("users_pw").value;
	    var users_pwOk = document.getElementById("users_pwOk").value;
	    
	    if(users_pw != users_pwOk){
			document.getElementById("checkPw").innerHTML = "비밀번호가 일치하지 않습니다."
			return false;
		} else if (users_pw == users_pwOk) {
			document.getElementById("checkPw").innerHTML = "비밀번호가 일치합니다."
			return false;
		}
	}
</script>
<style type="text/css">
	input[type = "password"] {
		width: 250px;
		height: 20px;
		margin-top: 10px;
	}
	#users_id{
		width: 250px;
		height: 20px;
		margin-top: 10px;
	}
</style>
</head>
<body>
	<h3>비밀번호 변경</h3>
		<h4>가입하신 아이디와 변경할 비밀번호를 입력해주세요.</h4>
		<form action="./modifyPw.do" method="post">
			<input name="users_id" id="users_id" placeholder="메일을 입력해주세요" required="required">
		<br>
	 		<input type="password" name="users_pw" id="users_pw" placeholder="변경하실 비밀번호를 입력해주세요" onchange="isSame()" required="required">
	 	<br>
	 		<input type="password" id="users_pwOk" placeholder="비밀번호를 한 번 더 입력해주세요" onchange="isSame()" required="required">&nbsp;&nbsp;<span id="checkPw"></span>
	 	<br><br>
	 		<input type="submit" value="비밀번호 변경하기">
	 		<input type="button" onclick="self.close()" value="취소">
	 	</form>
</body>
</html>