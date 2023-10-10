<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step03</title>
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
	h5{
		margin: 2px;
	}
	h4{
		margin: 10px 0px;
	}
	#pet_name, #pet_species, #pet_gender{
		border-color: red;
	}
	span {
		margin: 10px 10px;
	}
</style>
<body>

<div id="container">
<h2>가입을 축하드립니다!</h2>
<h3>추가 정보를 입력하시면 더 많은 서비스를 경험하실 수 있습니다.</h3>
<button onclick="location.href='./main.do'" id="nextTime">다음에 할게요</button>
<form action="./addUserInfo.do" method="post">
	<h4>추가정보 입력</h4>
	<div>
	<input type="hidden" name="users_id" value="${signUpVo.users_id}">
	<input type="text" id="sample6_postcode" placeholder="우편번호 찾기 버튼으로 우편번호를 입력해주세요">
	<input type="button" id="fixButton" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
	<input type="text" name="addr" id="sample6_address" placeholder="주소는 자동으로 입력됩니다"><br>
	<input type="text" name="addrDetail" id="sample6_detailAddress" placeholder="상세주소를 입력해주세요">
	<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
	<br>
	<input name="users_subtel" id="users_subtel" placeholder="추가 연락처를 입력해주세요 ex) 01011112222">
	</div>
	<br>
	<h4>반려동물 정보</h4>
	<h5> 붉은 테두리는 필수입력사항입니다 </h5>
	<div>
		<input type="hidden" name="pet_owner" id="pet_owner" value="${signUpVo.users_id}"><br>
		<input name="pet_name" id="pet_name" required="required" placeholder="반려동물의 이름을 입력해주세요"><br>
		<input name="pet_bday" id="pet_bday" placeholder="반려동물의 생일을 입력해주세요 ex) 20220505"><br>
		<span>종류를 선택해주세요</span>
		<select name="pet_species" id="pet_species" required="required">
			<option value="">--선택--</option>
			<option value="A">개</option>
			<option value="B">고양이</option>
			<option value="C">파충류</option>
			<option value="D">조류</option>
			<option value="F">어류</option>
			<option value="Z">기타</option>
		</select><br>
		<span>성별을 선택해주세요</span>
		<select name="pet_gender" id="pet_gender" required="required">
			<option value="">--선택--</option>
			<option value="M">수</option>
			<option value="F">암</option>
		</select> <br>
		<span>중성화 여부를 선택해주세요</span>
		<select name="pet_neut" id="pet_neut">
			<option value="">--선택--</option>
			<option value="Y">완료</option>
			<option value="N">미완료</option>
		</select><br>
		<input name="pet_report" id="pet_report" placeholder="추가적인 정보를 작성해주세요"><br>
		<input type="submit" value="추가정보 등록" id="fixButton" onclick="return addinfo()">
	</div>
</form>
</div>

</body>
<%@ include file="./footer.jsp" %>
</html>