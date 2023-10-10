<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="./css/users_loginForm.css">
</head>
<%@ include file="./header.jsp" %>
<body>
	<div id="container">
		<div style="display: flex; justify-content: center; align-items: center; margin-top: 50px;">
		<form action="./login.do" method="post">
			<div id="title">
				<img src="./img/circle_bottom.png">
				<h2>로그인</h2>
			</div>
			<table id="loginForm">
				<tr>
					<td>
						<input type="text" name="users_id" required="required" placeholder="username">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="users_pw" required="required" placeholder="password">
					</td>
				</tr>	
				<tr>
					<td colspan="2">
					<input type="submit" value="Login" id="Login"><br>
					<div id="">
						<a href="./insertUsers.do">회원가입</a>
						<a href="#" onclick="findId()">아이디 찾기</a>
						<a href="#" onclick="findPw()">비밀번호 찾기</a>
					</div>
<!-- 					<div id="naver_id_login" style="text-align: right; margin-top: 3px;"></div> -->
					</td>
				</tr>
			</table>
		</form>
		</div>
		
    <!-- //네이버 로그인 버튼 노출 영역 -->
    
    <script type="text/javascript">
<!--         var naver_id_login = new naver_id_login("VULq_T6rjXHVJh5qCkut", "http://localhost:8080/FinalProject_VET/main.do"); -->
<!--         var state = naver_id_login.getUniqState(); -->
        
<!--         naver_id_login.setButton("green", 3 , 40); -->
<!--         naver_id_login.setState(state); -->
        
<!--         naver_id_login.setPopup(); -->
<!--         naver_id_login.init_naver_id_login(); -->
        
<!--         var naver_id_login = new naver_id_login("YOUR_CLIENT_ID", "YOUR_CALLBACK_URL"); -->
<!--         // 접근 토큰 값 출력 -->
<!--         alert(naver_id_login.oauthParams.access_token); -->
<!--         // 네이버 사용자 프로필 조회 -->
<!--         naver_id_login.get_naver_userprofile("naverSignInCallback()"); -->
<!--         // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function -->
<!--         function naverSignInCallback() { -->
<!--           alert(naver_id_login.getProfileData('email')); -->
<!--           alert(naver_id_login.getProfileData('nickname')); -->
<!--           alert(naver_id_login.getProfileData('age')); -->
<!--         } -->
        
        
        function findId(){
        	window.open("./findIdWindow.do","_blank", "width:200px", "height:200px")
        }
        
        function findPw(){
        	window.open("./findPwWindow.do","_blank", "width:200px", "height:200px")
        }
        
	 </script>
    
    
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>