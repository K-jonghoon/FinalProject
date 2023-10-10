<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정관리 게시판</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> 
<script type="text/javascript" src="./js/schedule_calendar.js"></script>
<link rel="stylesheet" href="./css/calendar.css">
</head>
<%@ include file="./navbar.jsp" %>
<body>
	<div class="navContainer">
		<div id="calendar"></div>
		
		 <div class="modal fade" id="calendarModal" role="dialog" style="display: none;">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="exampleModalLabel">새 일정 등록</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	                </div>
	                <div class="modal-body">
	                    <div class="form-group">
	                        <label>일정 제목</label>
	                        <input type="text" class="form-control" id="sche_title1" name="sche_title">
	                        <label>날짜</label>
	                        <input type="date" class="form-control" id="sche_date1" name="sche_date">
	                        <label>시간</label>
	                        <input type="time" class="form-control" id="sche_time1" name="sche_time">
	                        <label>상세 일정</label>
	                        <input type="text" class="form-control" id="sche_content1" name="sche_content">
	                    	<input type="hidden" id="sche_num1" name="sche_num">
	                    </div>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" id="addCalendar">추가</button>
	                    <button type="button" data-bs-dismiss="modal">취소</button>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	     <div class="modal fade" id="detailModal" role="dialog" style="display: none;">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="exampleModalLabel">일정상세</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	                </div>
	                <div class="modal-body">
		                    <div class="form-group" id="modal_modify">
		                        <label>일정명</label>
		                        <input type="text" class="form-control" id="sche_title" name="sche_title">
		                        <label>날짜</label>
		                        <input type="date" class="form-control" id="sche_date" name="sche_date">
		                        <label>시간</label>
		                        <input type="time" class="form-control" id="sche_time" name="sche_time">
		                        <label>상세 일정</label>
		                        <input type="text" class="form-control" id="sche_content" name="sche_content">
		                    	<input type="hidden" id="sche_num" name="sche_num">
		                    </div>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" id="modifyCalendar" onclick="modifySchedule()">수정</button>
	                    <button type="button" id="delCalendar" onclick="deleteSchedule()">삭제</button>
	                    <button type="button" id="writeChart" style="display: none;" onclick="insertChart()">진료기록 작성</button>
	                    <button type="button" data-bs-dismiss="modal" onclick="closeModal()">닫기</button>
	                </div>
	            </div>
	        </div>
	    </div>
	
	</div>
	
</body>
<%@ include file="./footer.jsp" %>
</html>