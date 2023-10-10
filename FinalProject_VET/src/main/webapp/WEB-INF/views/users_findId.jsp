<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	function findId(){
		var users_name = document.getElementById("users_name").value;
		var users_tel = document.getElementById("users_tel").value;
		var info = document.getElementById("info");

		$.ajax({
			url:"./findId.do",
			type: "post",
			async: true,
			data:{"users_name":users_name, "users_tel":users_tel},
			success:function(data){
				console.log("요청된 결과값 : ", data);
				if(data=="false"){
					info.innerHTML = "해당 정보로 가입된 아이디를 찾을 수 없습니다.";
				} else {
					info.innerHTML = "회원님의 아이디는 ["+data+"] 입니다.";
				}
			},
			error: function (){
				alert("잘못된 요청입니다. 관리자에게 문의해주세요.");
			}
		});
	
	}

</script>
</head>
<body>
	<h3>아이디 찾기</h3>
	사이트에 가입하실 때 입력하신 이름을 입력하세요.<br><br>
	<input type="text" id="users_name"><br><br>
	사이트에 가입하실 때 입력한 전화번호를 입력하세요.<br><br>
	<input type="text" id="users_tel"><br><br>
	<input type="button" onclick="findId()" value="아이디 찾기">
	<input type="button" onclick="self.close()" value="닫기"><br>
	<p id="info"></p>
	
</body>
</html>