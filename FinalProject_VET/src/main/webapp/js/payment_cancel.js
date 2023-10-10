$(document).ready(function(){
	$(".selecteBtn").click(function(){
		console.log($(this));
		var pay_num = this.parentNode.parentNode.children[0].textContent;
		
		console.log(pay_num);
		
		$.ajax({
			url:"./selectOnePayment.do",
			method:"post",
			data:{"pay_num":pay_num},
			success:function(data){
				console.log(data);
				console.log(data.pay_num);
				location.href='./goCancel.do?pay_num='+data.pay_num;
			},
			error:function(){
				console.log("값전달 실패");
			}
		});
		
	});	
});


