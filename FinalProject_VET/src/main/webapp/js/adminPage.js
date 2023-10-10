
//관리자페이지 - 권한 별 조회
function selectAuth() {
	var userAuth = document.getElementsByName("userAuth")[0];
	var userAuthIndex = userAuth.selectedIndex;
	var info = document.getElementById("info");

	var choiceAuth = userAuth.options[userAuthIndex];
	var choiceAuthValue = choiceAuth.value;
	console.log(choiceAuth.value);

	$.ajax({
		url: "./adminPageAuth.do",
		type: "POST",
		async: true,
		data: { "auth": choiceAuthValue },
		dataType: "json",
		success: function(authList) {
			console.log("받아온 요청 값 : ", authList);
			console.log(authList[0].users_id);

			var varHtml = "";

			varHtml += "<div id='userList'>      ";
			varHtml += "<table id='chartInfo'>                  ";
			varHtml += "<thead>                  ";
			varHtml += "<tr>                     ";
			varHtml += "<th width='5%'>번호</th>            ";
			varHtml += "<th width='20%'>아이디</th>          ";
			varHtml += "<th width='25%'>이름</th>            ";
			varHtml += "<th width='10%'>전화번호</th>        ";
			varHtml += "<th width='10%'>";
			varHtml += "<select name='userStatus' id='userStatus' onchange='selectStatus()'>";
			varHtml += "<option value=''>상태</option>";
			varHtml += "<option value='N'>활동중</option>";
			varHtml += "<option value='S'>활동중지</option>";
			varHtml += "<option value='Y'>탈퇴</option>";
			varHtml += "</select>";
			varHtml += "</th>";
			varHtml += "<th width='10%'>";
			varHtml += "<select name='userAuth' id='userAuth' onchange='selectAuth()'>";
			varHtml += "	<option value=''>권한</option>                            ";
			varHtml += "	<option value='U'>일반사용자</option>                     ";
			varHtml += "	<option value='H'>병원관계자</option>                     ";
			varHtml += "	<option value='A'>관리자</option>                         ";
			varHtml += "</select>                                                     ";
			varHtml += "</th>            ";
			varHtml += "<th width='10%'>사업자등록번호</th>  ";
			varHtml += "<th width='10%'>가입일</th>          ";
			varHtml += "</tr>                    ";
			varHtml += "</thead>					";

			varHtml += "<tbody>";
			for (let i = 0; i < authList.length; i++) {
				varHtml += "<tr>                                                                               ";
				varHtml += "<td>" + (i + 1) + "</td>                                                               ";
				varHtml += "<td><a href='./selectUserDetail.do?users_id=" + authList[i].users_id + "'>" + authList[i].users_id + "</a></td>";
				varHtml += "<td>" + authList[i].users_name + "</td> ";
				varHtml += "<td>" + authList[i].users_tel + "</td>";
				if (authList[i].users_status == "N") {
					varHtml += "<td>활동중</td>";
				} else if (authList[i].users_status == "Y") {
					varHtml += "<td>탈퇴</td>";
				} else {
					varHtml += "<td>활동중지</td>";
				}
				if (authList[i].users_auth == "U") {
					varHtml += "<td>일반사용자</td> ";
				} else if (authList[i].users_auth == "H") {
					varHtml += "<td>병원관계자</td> ";
				} else {
					varHtml += "<td>관리자</td> ";
				}
				if (authList[i].users_crn > 0) {
					varHtml += "<td>" + authList[i].users_crn + "</td>";
				} else {
					varHtml += "<td></td>";
				}
				varHtml += "<td>" + authList[i].users_joindate + "</td>";
				varHtml += "</tr>                                                                              ";
			}

			varHtml += "</tbody>";

			$("#info").html(varHtml);

		},

		error: function() {
			info.innerHTML = "잘못된 요청입니다.";
		}

	});

}

function selectStatus() {
	var userStatus = document.getElementsByName("userStatus")[0];
	var userStatusIndex = userStatus.selectedIndex;
	var info = document.getElementById("info");

	var choiceStatus = userStatus.options[userStatusIndex];
	var choiceStatusValue = choiceStatus.value;
	console.log(choiceStatus.value);

	$.ajax({
		url: "./adminPageStatus.do",
		type: "POST",
		async: true,
		data: { "status": choiceStatusValue },
		dataType: "json",
		success: function(statusList) {
			console.log("받아온 요청 값 : ", statusList);
			console.log(statusList.length);

			if (statusList.length == 0) {
				
				info.innerHTML = "선택하신 상태에 해당하는 회원이 존재하지 않습니다.";

				var varHtml = "";

				varHtml += "<div id='userList'>      ";
				varHtml += "<table id='chartInfo'>";
				varHtml += "<thead>                  ";
				varHtml += "<tr>                     ";
				varHtml += "<th width='5%'>번호</th>            ";
				varHtml += "<th width='20%'>아이디</th>          ";
				varHtml += "<th width='25%'>이름</th>            ";
				varHtml += "<th width='10%'>전화번호</th>        ";
				varHtml += "<th width='10%'>";
				varHtml += "<select name='userStatus' id='userStatus' onchange='selectStatus()'>";
				varHtml += "<option value=''>상태</option>";
				varHtml += "<option value='N'>활동중</option>";
				varHtml += "<option value='S'>활동중지</option>";
				varHtml += "<option value='Y'>탈퇴</option>";
				varHtml += "</select>";
				varHtml += "</th>";
				varHtml += "<th width='10%'>";
				varHtml += "<select name='userAuth' id='userAuth' onchange='selectAuth()'>";
				varHtml += "	<option value=''>권한</option>                            ";
				varHtml += "	<option value='U'>일반사용자</option>                     ";
				varHtml += "	<option value='H'>병원관계자</option>                     ";
				varHtml += "	<option value='A'>관리자</option>                         ";
				varHtml += "</select>                                                     ";
				varHtml += "</th>            ";
				varHtml += "<th width='10%'>사업자등록번호</th>  ";
				varHtml += "<th width='10%'>가입일</th>          ";
				varHtml += "</tr>                    ";
				varHtml += "</thead>					";
				varHtml += "<tbody>";
				
				varHtml += "<td colspan='8'>선택하신 상태에 해당하는 회원이 존재하지 않습니다.</td>";
				
				varHtml += "</tbody>";
				
				$("#info").html(varHtml);
				
				
				} else {

				info.innerHTML = "";
				console.log(statusList[0].users_id);

				var varHtml = "";

				varHtml += "<div id='userList'>      ";
				varHtml += "<table id='chartInfo'>";
				varHtml += "<thead>                  ";
				varHtml += "<tr>                     ";
				varHtml += "<th width='5%'>번호</th>            ";
				varHtml += "<th width='20%'>아이디</th>          ";
				varHtml += "<th width='25%'>이름</th>            ";
				varHtml += "<th width='10%'>전화번호</th>        ";
				varHtml += "<th width='10%'>";
				varHtml += "<select name='userStatus' id='userStatus' onchange='selectStatus()'>";
				varHtml += "<option value=''>상태</option>";
				varHtml += "<option value='N'>활동중</option>";
				varHtml += "<option value='S'>활동중지</option>";
				varHtml += "<option value='Y'>탈퇴</option>";
				varHtml += "</select>";
				varHtml += "</th>";
				varHtml += "<th width='10%'>";
				varHtml += "<select name='userAuth' id='userAuth' onchange='selectAuth()'>";
				varHtml += "	<option value=''>권한</option>                            ";
				varHtml += "	<option value='U'>일반사용자</option>                     ";
				varHtml += "	<option value='H'>병원관계자</option>                     ";
				varHtml += "	<option value='A'>관리자</option>                         ";
				varHtml += "</select>                                                     ";
				varHtml += "</th>            ";
				varHtml += "<th width='10%'>사업자등록번호</th>  ";
				varHtml += "<th width='10%'>가입일</th>          ";
				varHtml += "</tr>                    ";
				varHtml += "</thead>					";

				varHtml += "<tbody>";
				for (let i = 0; i < statusList.length; i++) {
					varHtml += "<tr>                                                                               ";
					varHtml += "<td>" + (i + 1) + "</td>                                                               ";
					varHtml += "<td><a href='./selectUserDetail.do?users_id=" + statusList[i].users_id + "'>" + statusList[i].users_id + "</a></td>";
					varHtml += "<td>" + statusList[i].users_name + "</td> ";
					varHtml += "<td>" + statusList[i].users_tel + "</td>";
					if (statusList[i].users_status == "N") {
						varHtml += "<td>활동중</td>";
					} else if (statusList[i].users_status == "Y") {
						varHtml += "<td>탈퇴</td>";
					} else {
						varHtml += "<td>활동중지</td>";
					}
					if (statusList[i].users_auth == "U") {
						varHtml += "<td>일반사용자</td> ";
					} else if (statusList[i].users_auth == "H") {
						varHtml += "<td>병원관계자</td> ";
					} else {
						varHtml += "<td>관리자</td> ";
					}
					if (statusList[i].users_crn > 0) {
						varHtml += "<td>" + statusList[i].users_crn + "</td>";
					} else {
						varHtml += "<td></td>";
					}
					varHtml += "<td>" + statusList[i].users_joindate + "</td>";
					varHtml += "</tr>                                                                              ";
				}

				varHtml += "</tbody>";
				
			}

			$("#info").html(varHtml);

		},

		error: function() {
			info.innerHTML = "잘못된 요청입니다.";
		}

	});

}

window.onload = function() {
	$("#searchUserId").click(function() {
		$("#userList").css("display", "none");
	})

	$("#userAuth").change(function() {
		$("#userList").css("display", "none");
	})

	$("#userStatus").change(function() {
		$("#userList").css("display", "none");
	})

}

function searchUserId() {

	var keyword = document.getElementById("keyword").value;
	var info = document.getElementById("info");

	console.log(keyword);

	if (keyword.length >= 2) {
		$.ajax({
			url: "./adminPage.do",
			type: "POST",
			async: true,
			data: { "keyword": keyword },
			success: function(msg) {
				console.log("요청된 결과값: ", msg);
				if (msg == "") {
					
					info.innerHTML = "";
	
					var varHtml = "";
	
					varHtml += "<div id='userList'>      ";
					varHtml += "<table id='chartInfo'>";
					varHtml += "<thead>                  ";
					varHtml += "<tr>                     ";
					varHtml += "<th width='5%'>번호</th>            ";
					varHtml += "<th width='20%'>아이디</th>          ";
					varHtml += "<th width='25%'>이름</th>            ";
					varHtml += "<th width='10%'>전화번호</th>        ";
					varHtml += "<th width='10%'>";
					varHtml += "<select name='userStatus' id='userStatus' onchange='selectStatus()'>";
					varHtml += "<option value=''>상태</option>";
					varHtml += "<option value='N'>활동중</option>";
					varHtml += "<option value='S'>활동중지</option>";
					varHtml += "<option value='Y'>탈퇴</option>";
					varHtml += "</select>";
					varHtml += "</th>";
					varHtml += "<th width='10%'>";
					varHtml += "<select name='userAuth' id='userAuth' onchange='selectAuth()'>";
					varHtml += "	<option value=''>권한</option>                            ";
					varHtml += "	<option value='U'>일반사용자</option>                     ";
					varHtml += "	<option value='H'>병원관계자</option>                     ";
					varHtml += "	<option value='A'>관리자</option>                         ";
					varHtml += "</select>                                                     ";
					varHtml += "</th>            ";
					varHtml += "<th width='10%'>사업자등록번호</th>  ";
					varHtml += "<th width='10%'>가입일</th>          ";
					varHtml += "</tr>                    ";
					varHtml += "</thead>					";
					varHtml += "<tbody>";
					
					varHtml += "<td colspan='8'>회원이 존재하지 않습니다.</td>";
					
					varHtml += "</tbody>";
					varHtml += "</div>";
					
					$("#info").html(varHtml);
						
				} else {

					info.innerHTML = "";
					var msg = JSON.parse(msg);
					console.log(msg[0], typeof msg[0]);
					var varHtml = "";

					varHtml += "<div id='navContainer'>      ";
					varHtml += "<div id='userList'>      ";
					varHtml += "<table id='chartInfo'>";
					varHtml += "<thead>                  ";
					varHtml += "<tr>                     ";
					varHtml += "<th width='5%'>번호</th>            ";
					varHtml += "<th width='20%'>아이디</th>          ";
					varHtml += "<th width='25%'>이름</th>            ";
					varHtml += "<th width='10%'>전화번호</th>        ";
					varHtml += "<th width='10%'>";
					varHtml += "<select name='userStatus' id='userStatus' onchange='selectStatus()'>";
					varHtml += "<option value=''>상태</option>";
					varHtml += "<option value='N'>활동중</option>";
					varHtml += "<option value='S'>활동중지</option>";
					varHtml += "<option value='Y'>탈퇴</option>";
					varHtml += "</select>";
					varHtml += "</th>";
					varHtml += "<th width='10%'>";
					varHtml += "<select name='userAuth' id='userAuth' onchange='selectAuth()'>";
					varHtml += "	<option value=''>권한</option>                            ";
					varHtml += "	<option value='U'>일반사용자</option>                     ";
					varHtml += "	<option value='H'>병원관계자</option>                     ";
					varHtml += "	<option value='A'>관리자</option>                         ";
					varHtml += "</select>                                                     ";
					varHtml += "</th>            ";
					varHtml += "<th width='10%'>사업자등록번호</th>  ";
					varHtml += "<th width='10%'>가입일</th>          ";
					varHtml += "</tr>                    ";
					varHtml += "</thead>					";

					varHtml += "<tbody>";
					for (let i = 0; i < msg.length; i++) {
						varHtml += "<tr>                                                                               ";
						varHtml += "<td>" + (i + 1) + "</td>                                                               ";
						varHtml += "<td><a href='./selectUserDetail.do?users_id=" + msg[i].users_id + "'>" + msg[i].users_id + "</a></td>";
						varHtml += "<td>" + msg[i].users_name + "</td> ";
						varHtml += "<td>" + msg[i].users_tel + "</td>";
						if (msg[i].users_status == "N") {
								varHtml += "<td>활동중</td>";
							} else if (msg[i].users_status == "Y") {
								varHtml += "<td>탈퇴</td>";
							} else {
								varHtml += "<td>활동중지</td>";
							}
							if (msg[i].users_auth == "U") {
								varHtml += "<td>일반사용자</td> ";
							} else if (msg[i].users_auth == "H") {
								varHtml += "<td>병원관계자</td> ";
							} else {
								varHtml += "<td>관리자</td> ";
							}
							if (msg[i].users_crn > 0) {
								varHtml += "<td>" + msg[i].users_crn + "</td>";
							} else {
								varHtml += "<td></td>";
							}
						varHtml += "<td>" + msg[i].users_joindate + "</td>";
						varHtml += "</tr>                                                                              ";
					}
					varHtml += "</tbody>";
				}

				$("#info").html(varHtml);
			},

			error: function() {
				info.innerHTML = "잘못된 요청입니다.";
			}
		});

	} else {
		
					info.innerHTML = "검색어를 2자 이상 입력해주세요.";
					$("#info").html(varHtml);
		}
}


function chkBox() {
	var chks = document.getElementsByName("chkId");
	var cnt = 0;
	for (let c of chks) {
		if (c.checked) {
			cnt++;
		}
	}

	if (cnt == 0) {
		alert("한 명 이상의 회원을 반드시 선택해주세요.");
		return false;
	}
}

function allChk(bool) {
	var chks = document.getElementsByName("chkId");
	for (let c of chks) {
		c.checked = bool;
	}
}
