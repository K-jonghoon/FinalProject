<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제화면</title>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript" src="./js/payment_main.js"></script>
<link rel="stylesheet" href="./css/payment_main.css">
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
		<div id="payment_info">
				<h2>포인트를 구매하여 다양한 기능을 이용해보세요!</h2><br>
				<h3>포인트 사용법</h3><br>
				<b>첫째</b>, 진료 문의글 작성 시 빠른문의글을 등록할 수 있어요.<br>
				&emsp;&emsp;&emsp;전문가의 답변을 보다 빠르고 정확하게 받아보세요.<br>
				<b>둘째</b>, 동물병원 진료 예약시 예약금을 포인트로 결제할 수 있어요.<br>
				&emsp;&emsp;&emsp;홈페이지에서 쉽게 예약을 진행하고 확정받으세요.
		</div>
		<input type="hidden" value="${buyer_info.buyer_tel}" id="buyer_tel">
		<input type="hidden" value="${buyer_info.buyer_addr}" id="buyer_addr">
		<input type="hidden" value="${buyer_info.buyer_name}" id="buyer_name">
		<input type="hidden" value="${buyer_info.buyer_email}" id="buyer_email">
		<button id="mileage_10000" class="mileage" onclick="requestPay(this.value)" value="10000">
			<h2 class="mileageFont">포인트 1만원 충전</h2>
		</button>
		<button id="mileage_5000" class="mileage" onclick="requestPay(this.value)" value="5000">
			<h2 class="mileageFont">포인트 5천원 충전</h2>
		</button>
		
	</div>
	
</body>
<%@ include file="./footer.jsp" %>
</html>