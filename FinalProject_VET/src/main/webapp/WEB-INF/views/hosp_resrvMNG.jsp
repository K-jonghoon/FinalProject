<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 마이페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_Calendar.js'></script> <!-- 작성할 js -->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/calendar.css">
</head>
<%@ include file="./header.jsp" %>
<script type="text/javascript" src='./js/resrv_SideNav.js'></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script> <!-- 차트 만들어주는 js -->
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
		<button class="dropdown-btn" onclick="location.href='./hosp_rpy.do'">
			진료문의 답글
		</button>
		<div class="dropdown-container">
		</div>
	</div>
	
	<div class="navContainer">
		<div id="hosp_infomation">
		<h2 id="hosp_infoMNG">병원정보 관리</h2>
			<table id="hosp_detail">
					<tr>
						<th>병원이름</th>
						<td id="hosp_name">${hosp_info.hospital_vo[0].hosp_name}</td>
					</tr>
					<tr>
						<th>병원주소</th>
						<td id="hosp_addr">${hosp_info.users_addr}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${hosp_info.users_tel}</td>
					</tr>
					<tr>
						<th>운영시간</th>
						<td id="hosp_runtime">${hosp_time.open}시 ~ ${hosp_time.close}시</td>
					</tr>
					<tr>
						<th>휴무일</th>
						<td>${hosp_info.hospital_vo[0].hosp_off}요일</td>
					</tr>
					<tr>
						<th>진료과목</th>
						<td>
							<c:forEach var="medi" items="${medi_lists}">
								<c:forEach var="name" items="${medi.medicode_vo}">
									<b>${name.medi_name}</b>
								</c:forEach>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>진료동물</th>
						<td>
							<c:forEach var="anm" items="${anm_lists}">
								<c:forEach var="species" items="${anm.animalcode_vo}">
									<b>${species.anm_species}</b>
								</c:forEach>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>주차</th>
						<c:choose>
							<c:when test="${hosp_info.hospital_vo[0].hosp_park eq 'Y'}">
								<td>가능</td>
							</c:when>
							<c:otherwise>
								<td>불가</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th>병원 소개</th>
						<td id="hosp_etc">${hosp_info.hospital_vo[0].hosp_etc}</td>
					</tr>
				</table>
		</div>
		<div id="hosp_modifyArea">
			<button id="hosp_modify" onclick="location.href='./updateHosp.do';">정보수정</button>
			<button style="margin-top: 10px" id="hosp_resign" onclick="location.href='./resignUser.do'">회원탈퇴</button>
		</div>
		
		<h2 id="calhead">이번달 예약현황</h2>
		<div id="calendar">
		</div>
		<div id="month_cnt"></div>
		<div id="waitList"></div>
		<canvas id="myChart"></canvas>
		
		<div class="modal fade" id="resrv_detailModal" role="dialog" style="display: none;">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="exampleModalLabel">예약 상세조회</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	                </div>
	                <div class="modal-body">
	                    <div class="form-group">
	                        <label>예약번호</label>
	                        <input type="text" class="form-control" id="resrv_num" name="resrv_num">
	                        <label>예약날짜</label>
	                        <input type="date" class="form-control" id="resrv_visit" name="resrv_visit">
	                        <label>예약시간</label>
	                        <input type="text" class="form-control" id="resrv_time" name="resrv_time">
	                        <label>예약자명</label>
	                        <input type="text" class="form-control" id="resrv_name" name="resrv_name">
	                        <label>예약자 전화번호</label>
	                        <input type="text" class="form-control" id="resrv_tel" name="resrv_tel">
	                        <label>예약 메모</label>
	                        <input type="text" class="form-control" id="resrv_memo" name="resrv_memo">
	                        <input type="hidden" class="form-control" id="resrv_hosp" name="resrv_hosp">
	                    </div>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" id="resrv_modifyBtn" onclick="resrv_modify()">정보수정</button>
	                    <button type="button" id="resrv_saveBtn" onclick="resrv_save()">저장</button>
	                </div>
	            </div>
	        </div>
	    </div>
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