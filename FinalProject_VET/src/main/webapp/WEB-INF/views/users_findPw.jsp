<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	function findPw(){
		console.log("비밀번호 찾기 함수 실행")
		
		var users_id = document.getElementById("users_id").value;
		
		$.ajax({
			url:"./mailSend.do",
			type: "post",
			data: {"users_id":users_id},
			success: function(data){
				receiveData = data;
			},
			error: function(){
				alert("잘못된 요청입니다. 관리자에게 문의하세요.");
			}
			
		});
			
	}
		var receiveData = "";
		
		function isSameEmail(){
			var certNum = document.getElementById("certNum").value;
			console.log(certNum);
			if(receiveData != certNum){
				document.getElementById("checkCertNum").innerHTML = "인증번호가 일치하지 않습니다.";
			} else if (receiveData == certNum){
				document.getElementById("checkCertNum").innerHTML = "인증번호가 일치합니다.";
				alert("비밀번호 수정 페이지로 이동합니다.");
				window.location.href = './modifyPw.do';
				
			}
		}
		

</script>
</head>
<body>
	<h3>비밀번호 찾기</h3>
		사이트에 가입하실 때 입력하신 이메일을 입력하세요.<br><br>
			<input type="text" id="users_id">
			<input type="button" onclick="findPw()" value="인증번호 발송">
			<br>
		 	<input style="margin-top: 10px;" name="certNum" id="certNum" placeholder="인증번호를 입력해주세요" onchange="isSameEmail()" required="required">&nbsp;&nbsp;<span id="checkCertNum"></span>
		 	<br><br>
			<input type="button" onclick="self.close()" value="닫기"><br>
			
</body>
</html>