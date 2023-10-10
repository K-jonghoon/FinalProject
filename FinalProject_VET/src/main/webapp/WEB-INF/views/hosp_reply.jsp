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
		${cvo}
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