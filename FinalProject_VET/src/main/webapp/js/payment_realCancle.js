

function cancelPay(val){
	
	console.log("환불시작");
	var pay_amount = $("#pay_amount").text();
	var imp_uid = val;
	console.log(pay_amount,val);
	
	$.ajax({
		url:"./cancelPayment.do",
		type:"post",
		data:{
			cancel_request_amount:pay_amount,
			reason:"테스트 결제 환불",
			imp_uid:imp_uid
		},
		success:function(data){
				console.log("결제취소", data);
				if(data == "true"){
					alert("결제가 취소되었습니다. 환불내역을 확인해주세요.");
					location.href="./cancelPayMail.do?imp_uid="+imp_uid;
				}else if(data == "false"){
					alert("포인트가 부족합니다. 결제 취소 요청이 거절되었습니다.");
					location.href="./selectAllPayment.do";
				}
		},
		error:function(){
			location.href="./selectAllPayment.do";
		}
	});
}	