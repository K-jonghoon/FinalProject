$(document).ready(function(){
	document.getElementById("btnUseEmail").style.display="none";
});

function checkEmail(){
	var email = document.getElementById("users_id").value;
	console.log("checkEmail 함수 : ", email);
	
	if(email != ""){
		$.ajax({
			url:"./duplicationAjax.do",
			type:"post",
			data:"checkEmail="+email,
			async: true,
			success: function(data){
				console.log("Ajax 처리된 성공 결과: ", data);
				if(data == "true"){
					document.getElementById("condition").innerHTML = "사용할 수 없는 이메일입니다.";
					document.getElementById("btnUseEmail").style.display = "none";
				} else {
					document.getElementById("condition").innerHTML = "사용가능한 이메일입니다.";
					document.getElementById("btnUseEmail").style.display = "block";
				}
			},
			error: function(){
				alert("잘못된 요청입니다. 관리자에게 문의하세요.");
			}
		});
	}
}

function useEmail(){
	var email = document.getElementById("users_id").value;
	opener.document.getElementById("users_id").value = email;
	window.close();
}

function isSame() {
    var users_pw = document.getElementById("users_pw").value;
    var users_pwOk = document.getElementById("users_pwOk").value;
    
    if(users_pw != users_pwOk){
		document.getElementById("checkPw").innerHTML = "비밀번호가 일치하지 않습니다."
		return false;
	} else if (users_pw == users_pwOk) {
		document.getElementById("checkPw").innerHTML = "비밀번호가 일치합니다."
		return false;
	}
   
    
}