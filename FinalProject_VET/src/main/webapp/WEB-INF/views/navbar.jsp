<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="./header.jsp" %>
<body>
	<div class="sidenav">
		<button id="sideNav_1" class="dropdown-btn" onclick="location.href='./selectAllChartPaging.do'">
			진료기록
		</button>
		<div class="dropdown-container">
		</div>
		
		<button class="dropdown-btn">
			진료문의 내역<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="location.href='./selectUsersBoard.do'">일반문의</a>
			<a onclick="location.href='./selectFastBoard.do'">빠른문의</a>
		</div>
		
		<button class="dropdown-btn" onclick="location.href='./selectAllSchedule.do'">
			일정관리
		</button>
		<div class="dropdown-container">
		</div>
		
		<button class="dropdown-btn" onclick="location.href='./resrv_recordList.do?resrv_userid=${sessionScope.loginVo.users_id}'">
			예약내역
		</button>
		<div class="dropdown-container">
		</div>
		
		<button class="dropdown-btn" >
			결제 및 포인트<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="location.href='./selectPntList.do'">포인트 사용내역</a>
			<a onclick="location.href='./selectAllPayment.do'">포인트 결제내역</a>
		</div>
		
		<button class="dropdown-btn" onclick="location.href='./selectAllBookmark.do?bm_usersid=${sessionScope.loginVo.users_id}'">
			즐겨찾기
		</button>
		<div class="dropdown-container">
		</div>
		
		<button class="dropdown-btn">
			내 정보관리<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="location.href='./selectOneDetail.do'">조회 및 수정</a>
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
</html>