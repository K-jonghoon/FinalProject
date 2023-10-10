<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step02</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="./js/users_insertUsersStepTwo.js"></script>
<script type="text/javascript">
	function duplicateId(){
		console.log("아이디 중복검사 함수");
		window.open("./duplication.do","중복검사", "width=300px", "height=300px");
	}
	
	function mailCheck(){
		console.log("인증용 이메일 보내기 함수");
		var users_id = document.getElementById("users_id").value;
		
		$.ajax({
			url:"./mailSend.do",
			type: "post",
			data: {"users_id":users_id},
			success: function(data){
				//받아온 값을 확인하기 위한 콘솔로그
// 				console.log(data);
				receiveData=data;
			},
			error: function(){
				alert("잘못된 요청입니다. 관리자에게 문의하세요.");
			}
		});
		
	}
	
	//ajax에서 반환받은 값을 저장하는 변수
	var receiveData = "";
// 	console.log(receiveData);
	
	//반환 받은 난수와 입력한 값을 비교하여 두 값이 일치하는지 확인하는 함수
	function isSameEmail(){
		var certNum = document.getElementById("certNum").value;
		console.log(certNum);
		if(receiveData != certNum){
			document.getElementById("checkCertNum").innerHTML = "인증번호가 일치하지 않습니다.";
		} else if (receiveData == certNum){
			document.getElementById("checkCertNum").innerHTML = "인증번호가 일치합니다.";
		}
	}

</script>

<style type="text/css">
	input {
		width: 400px;
		height: 20px;
		margin: 3px;
	}
	#fixButton{
		width: auto;
		height: auto;
		padding-block: 1px;
    	padding-inline: 6px;
	}
	#fixButton2{
		width: auto;
		height: auto;
		padding-block: 1px;
    	padding-inline: 6px;
	}
	#mailCertButton{
		width: auto;
		height: auto;
		padding-block: 1px;
    	padding-inline: 6px;
	}

</style>
</head>
<%@ include file="./header.jsp" %>
<body>

<div id="container">
 <form action="./signUp.do" method="post">
	<h2>회원가입</h2>
	<div> 	
	 	<input name="users_id" id="users_id" placeholder="메일을 입력해주세요" onclick="return duplicateId()" required="required">
 		<input type="button" id="mailCertButton" value="이메일 보내기" onclick="return mailCheck()">
 		<br>
	 	<input name="certNum" id="certNum" placeholder="인증번호를 입력해주세요" onchange="isSameEmail()" required="required">&nbsp;&nbsp;<span id="checkCertNum"></span>
	 	<br>
	 	<input type="password" name="users_pw" id="users_pw" placeholder="비밀번호를 입력해주세요" onchange="isSame()" required="required">
	 	<br>
	 	<input type="password" id="users_pwOk" placeholder="비밀번호를 한 번 더 입력해주세요" onchange="isSame()" required="required">&nbsp;&nbsp;<span id="checkPw"></span>
	 	<br>
	 	<input name="users_name" id="users_name" placeholder="이름을 작성해주세요" required="required">
	 	<br>
	 	<input name="users_tel" id="users_tel" placeholder="전화번호를 입력해주세요 ex)01012345678" required="required">
 	</div>
	 <br>
	 	<div>
		 	<input type="submit" id="fixButton" value="회원가입">
		 	<input type="button" id="fixButton2" value="가입 취소" onclick="javascipt:location.href='./main.do'">
	 	</div>
 </form>

</div>

</body>
<%@ include file="./footer.jsp" %>
</html>