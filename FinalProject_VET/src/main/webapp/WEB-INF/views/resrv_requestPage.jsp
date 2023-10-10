<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 신청 페이지 (일반사용자)</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_reqCal.js'></script> <!-- 작성할 js -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/resrv_reqCalendar.css">
</head>
<body>
	<div class="container">
		<h1 id="resrv_reqH1">${hosp_name} 예약</h1>
		<div id="cal_area">
			<div id="calendar"></div>
		</div>
		<div id="time_area">
			<h2>예약가능 시간</h2>
			<c:if test="${hosp_runTime ne null}">
				<button id="hosp_run">${hosp_runTime}</button>
			</c:if>
			
			<div id="resrv_availableTime"></div>
			<p><b>현재 보유 포인트 : ${point}</b></p>	
			
		</div>
		<hr>
		<div id="resrv_info">
			<h2>예약자 정보</h2>
			<form action="./resrv_insert.do" method="post">
			<input type="hidden" name="resrv_userid" value="${user_vo.users_id}">
			<input type="hidden" name="resrv_hops" value="${sessionScope.resrv_hops}">	
			<table>
				<tr>
					<th>선택 날짜 </th>
					<td><input type="date" id="select_date" name="resrv_visit" readonly></td>
				</tr>
				<tr>
					<th>선택 시간</th>
					<td><input type="text" id="select_time" name="resrv_time" value="-- : --" readonly></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="resrv_name" placeholder="예약이름을 작성해주세요" value="${user_vo.users_name}" required></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="resrv_tel" placeholder="ex)01012345678" required></td>
				</tr>
				<tr>
					<th>메모</th>
					<td colspan="2"><input type="text" name="resrv_memo" placeholder="간략한 메모를 남겨주세요"></td>
				</tr>
				<tr>
					<th colspan="1"><input type="submit" id="submit" value="예약 신청"></th>
					<td><button class="btn btn-default" id="cancel" onclick="self.close()">취소</button></td>				
				</tr>
			</table>
			<p>예약시 예약금 3000포인트가 차감됩니다.</p>
			</form>
		</div>
	</div>
</body>
</html>