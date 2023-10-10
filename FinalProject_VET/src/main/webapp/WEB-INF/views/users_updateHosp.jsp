<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 정보 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_Calendar.js'></script> <!-- 작성할 js -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/users_updateHosp.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script> <!-- 차트 만들어주는 js -->
</head>
<%@ include file="./header.jsp" %>
<script type="text/javascript" src='./js/resrv_SideNav.js'></script>
<body>
	<div class="sidenav">
		<button id="sideNav_1" class="dropdown-btn" onclick="hosp_info()">
			병원정보
		</button>
		<div class="dropdown-container">
		</div>
		<button class="dropdown-btn">
			예약관리<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="resrv_calendar()">예약현황</a>
			<a onclick="month_count()">월별 예약건수</a>
			<a onclick="resrv_wList()">예약승인 대기명단</a>
		</div>
		<button class="dropdown-btn">
			진료문의 답글
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
	</div>
	
	<div class="navContainer">
			
	<form action="./updateHosp.do" method="post">
		<div class="form-group">
			<br>
			<table id="chartInfo">
				<tr>
				<th>ID</th>
					<td> <input class="form-control" type="text" name="users_id" value="${loginVo.users_id}" readonly="readonly"></td>
				</tr>
				<tr>
				<th>병원이름</th>
					<td> <input class="form-control" type="text" name="users_name" id="users_name" value="${hosp_info.users_name}" required="required"></td>
				</tr>
				<tr>
				<tr>
				<th>사업자등록번호</th>
					<td> <input class="form-control" type="text" name="users_crn" id="users_crn" value="${hosp_info.users_crn}" required="required"></td>
				</tr>
				<tr>
				<th>병원주소</th>
					<td> <input class="form-control" type="text" name="users_addr" value="${hosp_info.users_addr}" required="required"></td>
				</tr>
				<tr>
				<th>전화번호</th>
					<td> <input class="form-control" type="text" name="users_tel" value="${hosp_info.users_tel}" required="required"></td>
				</tr>
				<tr>
				<th>추가전화번호</th>
					<td> <input class="form-control" type="text" name="users_subtel" value="${hosp_info.users_subtel}"></td>
				</tr>
				<tr>
				<th>운영시간</th> <!-- 새로 입력하는 방식으로 selectbox -->
					<td>
						<div class="info">
							<span>변경하고자 하는 시간을 선택해주세요</span>
						 	<select name="hosp_openTime" id="hosp_openTime">
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
					</td>
				</tr>
				<tr>
				<th>휴무일</th>
					<td> <input class="form-control" type="text" name="hosp_off_original" value="현재 선택 : ${hosp_info.hospital_vo[0].hosp_off}요일" readonly="readonly">
						<div class="info">
							<span>변경하고자 하는 요일을 선택해주세요</span>
							<select name="hosp_off">
								<option value="">--요일 선택--</option>
								<option value="일">일요일</option>
								<option value="월">월요일</option>
								<option value="화">화요일</option>
								<option value="수">수요일</option>
								<option value="목">목요일</option>
								<option value="금">금요일</option>
								<option value="토">토요일</option>
						 	</select>
					 	</div>
					</td>
				</tr>
				<tr>
				<th>진료과목</th>
					<td>
						<div class="info">
					 		<span>진료과목을 선택해주세요</span>
					 		<select name="medi_code">
					 			<option value="">--과목 선택--</option>
					 			<option value="00">일반진료</option>
					 			<option value="01">내과</option>
					 			<option value="02">외과</option>
					 			<option value="03">접종</option>
					 		</select>
					 	</div>
					</td>
				</tr>
				<tr>
				<th>진료동물</th>
					<td>
						<div class="info">
					 		<span>진료가능한 동물을 선택해주세요</span><br>
					 		<input type="checkbox" name="anm_code" class="chk" value="A">개<br>
					 		<input type="checkbox" name="anm_code" class="chk" value="B">고양이<br>
					 		<input type="checkbox" name="anm_code" class="chk" value="C">파충류<br>
					 		<input type="checkbox" name="anm_code" class="chk" value="D">조류<br>
					 		<input type="checkbox" name="anm_code" class="chk" value="F">어류<br>
					 		<input type="checkbox" name="anm_code" class="chk" value="Z">기타<br>
					 	</div>
					</td>
				</tr>
				<tr>
				<th>주차 가능 여부</th>
					<td>
						<c:choose>
							<c:when test="${hosp_info.hospital_vo[0].hosp_park eq 'Y'}">
								현재 선택 : 가능							
							</c:when>
							<c:otherwise>
								현재 선택 : 불가
							</c:otherwise>
						</c:choose>
						<div class="info">
							<span>주차 가능 여부를 선택해주세요</span>
							<select name="hosp_park" id="hosp_park">
						 		<option value="">--주차 여부--</option>
						 		<option value="Y">주차 가능</option>
						 		<option value="N">주차 불가능</option>
						 	</select>
					 	</div>
					</td>
				</tr>
				<tr>
				<th>병원 소개</th>
					<td><input class="form-control" type="text" name="hosp_etc" value="${hosp_info.hospital_vo[0].hosp_etc}"></td>
				</tr>
			</table>
		</div>
		<br>
		<div class="buttonPart">
			<input type="submit" value="수정">
			<input type="button" value="취소" onclick="location.href='./resrv_Select.do'">
		</div>
	</form>
		
		
		
	</div>
</body>
<script type="text/javascript">
	var dropdown = document.getElementsByClassName("dropdown-btn");
	var i;
	
	for (i = 0; i < dropdown.length; i++) {
	  dropdown[i].addEventListener("click", function() {
	    this.classList.toggle("active");
	    var dropdownContent = this.nextElementSibling;
	    if (dropdownContent.style.display === "block") {
	      dropdownContent.style.display = "none";
	    } else {
	      dropdownContent.style.display = "block";
	    }
	  });
	}
</script>
<%@ include file="./footer.jsp" %>
</html>