<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step02(병원)</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="./js/users_insertHospStepTwo.js"></script>
<script type="text/javascript">
	function duplicateId(){
		console.log("아이디 중복검사 함수");
		window.open("./duplication.do","중복검사", "width=300px, height=300px");
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
	.chk{
		width: auto;
		height: auto;
	}
	#mailCertButton{
		width: auto;
		height: auto;
		padding-block: 1px;
    	padding-inline: 6px;
	}
	div.info{
		display: inline-block;
		margin: 10px 20px 0px 0px;		
	}
	#chkBoxes{
		display: inline-flex;
	}
</style>
</head>
<%@ include file="./header.jsp" %>
<body>

<div id="container">
 <form action="./signUpHosp.do" method="post">
	<h2>회원가입</h2>
 	<input name="users_id" id="users_id" placeholder="메일을 입력해주세요" onclick="return duplicateId()" required="required">
 	<input type="button" id="mailCertButton" value="이메일 보내기" onclick="return mailCheck()">
 	<br>
	<input name="certNum" id="certNum" placeholder="인증번호를 입력해주세요" onchange="isSameEmail()" required="required">&nbsp;&nbsp;<span id="checkCertNum"></span>
	<br>
 	<input type="password" name="users_pw" id="users_pw" placeholder="비밀번호를 입력해주세요" onchange="isSame()" required="required">
 	<br>
 	<input type="password" id="users_pwOk" placeholder="비밀번호를 한 번 더 입력해주세요" onchange="isSame()" required="required">&nbsp;&nbsp;<span id="checkPw"></span>
 	<br>
 	<input name="users_name" id="users_name" placeholder="병원 이름을 입력해주세요" required="required">
 	<br>
 	<input name="users_tel" id="users_tel" placeholder="전화번호를 입력해주세요 ex) 01011112222" onchange="checkTel()" required="required">
 	<br>
 	<input name="users_crn" id="users_crn" placeholder="사업자등록번호를 입력해주세요" onchange="checkCrn()" required="required">
 	<br>
 	<input type="text" id="sample6_postcode" placeholder="우편번호 찾기를 통해 우편번호를 입력해주세요">
	<input type="button" id="fixButton" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
	<input type="text" name="addr" id="sample6_address" placeholder="주소는 자동으로 입력됩니다"><br>
	<input type="text" name="addrDetail" id="sample6_detailAddress" placeholder="상세주소를 입력해주세요">
	<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
 	<br>
 	
 	<div class="info">
 	<span>운영시간을 선택해주세요</span><br>
 	<select name="hosp_openTime" id="hosp_openTime" style="margin-top: 10px">
 		<option value="">--여는 시간--</option>
 		<option value="00">오전 0시</option>
 		<option value="01">오전 1시</option>
 		<option value="02">오전 2시</option>
 		<option value="03">오전 3시</option>
 		<option value="04">오전 4시</option>
 		<option value="05">오전 5시</option>
 		<option value="06">오전 6시</option>
 		<option value="07">오전 7시</option>
 		<option value="08">오전 8시</option>
 		<option value="09">오전 9시</option>
 		<option value="10">오전 10시</option>
 		<option value="11">오전 11시</option>
 		<option value="12">오후 12시</option>
 		<option value="13">오후 1시</option>
 		<option value="14">오후 2시</option>
 		<option value="15">오후 3시</option>
 		<option value="16">오후 4시</option>
 		<option value="17">오후 5시</option>
 		<option value="18">오후 6시</option>
 		<option value="19">오후 7시</option>
 		<option value="20">오후 8시</option>
 		<option value="21">오후 9시</option>
 		<option value="22">오후 10시</option>
 		<option value="23">오후 11시</option>
 	</select>
 	<select name="hosp_closeTime" id="hosp_closeTime" style="margin-top: 10px">
 		<option value="">--닫는 시간--</option>
 		<option value="24">오전 0시</option>
 		<option value="01">오전 1시</option>
 		<option value="02">오전 2시</option>
 		<option value="03">오전 3시</option>
 		<option value="04">오전 4시</option>
 		<option value="05">오전 5시</option>
 		<option value="06">오전 6시</option>
 		<option value="07">오전 7시</option>
 		<option value="08">오전 8시</option>
 		<option value="09">오전 9시</option>
 		<option value="10">오전 10시</option>
 		<option value="11">오전 11시</option>
 		<option value="12">오후 12시</option>
 		<option value="13">오후 1시</option>
 		<option value="14">오후 2시</option>
 		<option value="15">오후 3시</option>
 		<option value="16">오후 4시</option>
 		<option value="17">오후 5시</option>
 		<option value="18">오후 6시</option>
 		<option value="19">오후 7시</option>
 		<option value="20">오후 8시</option>
 		<option value="21">오후 9시</option>
 		<option value="22">오후 10시</option>
 		<option value="23">오후 11시</option>
 	</select>
 	</div>
 	<br>
 	<div id="chkBoxes">
	 	<div class="info">
		 	<span>휴일을 선택해주세요</span><br>
		 	<input type="checkbox" name="hosp_off" class="chk" value="일">일요일<br>
		 	<input type="checkbox" name="hosp_off" class="chk" value="월">월요일<br>
		 	<input type="checkbox" name="hosp_off" class="chk" value="화">화요일<br>
		 	<input type="checkbox" name="hosp_off" class="chk" value="수">수요일<br>
		 	<input type="checkbox" name="hosp_off" class="chk" value="목">목요일<br>
		 	<input type="checkbox" name="hosp_off" class="chk" value="금">금요일<br>
		 	<input type="checkbox" name="hosp_off" class="chk" value="토">토요일<br>
	 	</div>
	 	
	 	<div class="info">
	 		<span>진료가능한 동물을 선택해주세요</span><br>
	 		<input type="checkbox" name="anm_code" class="chk" value="A">개<br>
	 		<input type="checkbox" name="anm_code" class="chk" value="B">고양이<br>
	 		<input type="checkbox" name="anm_code" class="chk" value="C">파충류<br>
	 		<input type="checkbox" name="anm_code" class="chk" value="D">조류<br>
	 		<input type="checkbox" name="anm_code" class="chk" value="F">어류<br>
	 		<input type="checkbox" name="anm_code" class="chk" value="Z">기타<br>
	 	</div>
	 	
	 	<div class="info">
	 		<span>진료과목을 선택해주세요</span><br>
	 		<input type="checkbox" name="medi_code" class="chk" value="00">일반진료<br>
	 		<input type="checkbox" name="medi_code" class="chk" value="01">내과<br>
	 		<input type="checkbox" name="medi_code" class="chk" value="02">외과<br>
	 		<input type="checkbox" name="medi_code" class="chk" value="03">접종<br>
	 	</div>
 	</div>
 	<br>
 	<div class="info">
 	<span>주차 가능 여부를 선택해주세요</span>
	<select name="hosp_park" id="hosp_park">
 		<option value="">--주차 여부--</option>
 		<option value="Y">주차 가능</option>
 		<option value="N">주차 불가능</option>
 	</select>
 	</div>
 	<br>
 	<div class="info">
 	<span>운영 형태를 선택해주세요</span>
 		<select name="hosp_mng" id="hosp_mng">
 		<option value="">--운영 형태--</option>
 		<option value="G">일반 병원</option>
 		<option value="E">24시 병원</option>
 		<option value="S">2차 병원</option>
 	</select>
 	</div>
 	<br>
 	<div class="info">
 	<input type="text" name="hosp_etc" id="hosp_etc" placeholder="기타 소개 사항을 작성해주세요">
 	</div> 	 
 	<br>
 	<div>
	 	<input type="submit" id="fixButton" value="회원가입">
	 	<input type="button" id="fixButton" value="가입 취소" onclick="javascipt:location.href='./main.do'">
 	</div>
  </form>

</div>

</body>
<%@ include file="./footer.jsp" %>
</html>