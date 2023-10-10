<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약신청 페이지</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type="text/javascript">
	$(document).on("click","#close_btn",function(){
		console.log(this.value);
		window.opener.location.href="./resrv_recordList.do?resrv_userid="+this.value;
		window.close();
	});
</script>
<style type="text/css">
	body {
		margin: 0px auto;
		padding: 0px;
	}
	h2{
		text-align: center;
	}
	table {
		margin: 0px auto;
	}
	tr{
		height: 40px;
		text-align: left;
	}
	th{
		text-align: right;
	}
	#btn_area{
		width: 100px;
		 margin: 10px auto;
	}
	button {
	  background-color: #EFEBE9;
	  border: 1px solid #3E2723;
	  width: 100px;
	  height:30px;
	  border-radius: 5px;
	  font-size: 17px;
	  text-align: center;
	}
</style>
</head>
<body>
	<div id="container">
		<h2>예약신청 완료</h2>
		<hr>
		<table>
			<tr>
				<th>예약번호 : </th>
				<td>${resrv_detail.resrv_num}</td>
			</tr>
			<tr>
				<th>예약 동물병원 : </th>
				<td>${resrv_detail.resrv_hops}</td>
			</tr>
			<tr>
				<th>예약날짜 : </th>
				<td>${resrv_detail.resrv_visit}</td>
			</tr>
			<tr>
				<th>예약시간 : </th>
				<td>${resrv_detail.resrv_time}시 00분</td>
			</tr>
			<tr>
				<th>예약자명 : </th>
				<td>${resrv_detail.resrv_name}</td>
			</tr>
			<tr>
				<th>예약자 전화번호 : </th>
				<td>${resrv_detail.resrv_tel}</td>
			</tr>
			<tr>
				<th>예약 메모 : </th>
				<td>${resrv_detail.resrv_memo}</td>
			</tr>
		</table>
		<hr>
		<div id="btn_area">
			<button id="close_btn" value="${sessionScope.loginVo.users_id}">창닫기</button>
		</div>
	</div>
</body>
</html>