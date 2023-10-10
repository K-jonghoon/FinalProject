<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환불진행창</title>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<link rel="stylesheet" href="./css/payment_list.css">
<style type="text/css">
	td{
		text-align: left;
		padding-left: 30px;
	}
</style>
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
		<h2>결제 상세내역</h2>
		<table id="chartInfo">
			<tr>
				<th>결제번호</th>
				<td>${pvo.pay_num}</td>
			</tr>
			<tr>
				<th>결제금액</th>
				<td id="pay_amount">${pvo.pay_amount}</td>
			</tr>
			<tr>
				<th>결제시간</th>
				<td>${pvo.pay_time}</td>
			</tr>
			<tr>
				<th>결제방식</th>
				<td>${pvo.pay_method}</td>
			</tr>
			<tr>
				<th>결제상태</th>
					<c:choose>
						<c:when test="${pvo.pay_status eq 'Y'}">
							<td>결제 완료</td>
						</c:when>
						<c:when test="${pvo.pay_status eq 'C'}">
							<td>환불 완료</td>
						</c:when>
					</c:choose>
			</tr>
		</table>
		<div id="buttonPart">
			<button id="cacelpay" onclick="cancelPay(this.value)" value="${pvo.imp_uid}">환불</button>
			<button id="cacelpay2" onclick="location.href='./selectAllPayment.do'">이전</button>
		</div>
	</div>
</body>
<script type="text/javascript" src="./js/payment_realCancle.js"></script>
<%@ include file="./footer.jsp" %>
</html>