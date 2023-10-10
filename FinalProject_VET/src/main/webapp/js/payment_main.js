
	var IMP = window.IMP;
	IMP.init("imp40440345");
	const random = Math.random();
	var merchant_uid = ﻿'LFT'+random;
	
	﻿function requestPay(val) {
		alert("결제를 진행합니다.")
		
		var point = val;
		var buyer_email = $("#buyer_email").val();
		var buyer_tel = $("#buyer_tel").val();
		var buyer_addr = $("#buyer_addr").val();
		var buyer_name = $("#buyer_name").val();
		console.log(buyer_email, buyer_tel,buyer_addr,buyer_name);
		console.log(point);
        IMP.request_pay(
            {
            	pg: "kcp", 
                pay_method: "card", 
                merchant_uid: merchant_uid,
                name: "마일리지"+point+"원 충전",
                amount: point,
                buyer_email: buyer_email,
                buyer_name: buyer_name,
                buyer_tel: buyer_tel,
                buyer_addr: buyer_addr,
                buyer_postcode: "123-456"
            },
            function (rsp) {
            	console.log(rsp);
            	
            	if(rsp.success){
            		alert("결제가 완료되었습니다");
            		
            		$.ajax({
            			url:"./insertNewPayment.do",
            			method:"post",
            			data : {
							pay_id : buyer_email,
							pay_amount : point,
							pay_code : "kcp",
							merchant_uid : merchant_uid,
							imp_uid:rsp.imp_uid
						},
						success:function(data){
							console.log("결제성공");
							if(data == "true"){
								location.href='./paymentMail.do?pay_id='+buyer_email+'&imp_uid='+rsp.imp_uid;
							}
						},
						error:function(){
							console.log("결제실패");
						}
            		});
            	}else{
            		var msg = "결제에 실패하였습니다";
            		msg += '에러내용 : ' + rsp.error_msg;
            		console.log('결제실패');
            		alert(msg);
            	}
		});
	}
