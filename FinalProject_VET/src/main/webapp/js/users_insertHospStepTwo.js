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


 function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }

//$(".mailCertButton").click(function(){
//	var email = $(".users_id").val();
//	console.log(email);
//	
//	$.ajax({
//		type:"get",
//		url:"./mailCheck.do?email="+email
//	});
//
//});

















  