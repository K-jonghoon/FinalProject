$(document).on("click",".resrv_cancel",function(){
	console.log("취소버튼 작동");
	var resrv_num = this.parentNode.parentNode.children[0].innerText;
	var resrv_visit = this.parentNode.parentNode.children[2].innerText;
	var resrv_time = this.parentNode.parentNode.children[3].innerText.substr(0,2);
	console.log(resrv_visit, resrv_time);
	alert("예약이 취소됩니다. 지불된 포인트는 반환됩니다.");
	this.parentNode.parentNode.children[4].innerText="취소";
	this.parentNode.style.display="none";
	$.ajax({
		url:"./resrv_userCancel.do",
		method:"post",
		data:{
			resrv_num:resrv_num,
			resrv_visit:resrv_visit,
			resrv_time:resrv_time
			},
		success:function(result){
			console.log(result);
		},
		error:function(){
			alert("예약 취소 실패");
		}
	});
});

function pageFirst(resrv_userid){
	location.href = "./resrv_recordList.do?page=1&resrv_userid="+resrv_userid;
}

function pagePrev(stagePage, countPage,resrv_userid){
	var page = (stagePage - countPage) < 0 ? 1 : (stagePage-countPage);
	location.href = "./resrv_recordList.do?page="+page+"&resrv_userid="+resrv_userid;
}

function page(page,resrv_userid){
	console.log("페이지 선택",page);
	location.href="./resrv_recordList.do?page="+page+"&resrv_userid="+resrv_userid;
}

function pageNext(stagePage, countPage,resrv_userid){
	location.href="./resrv_recordList.do?page="+(stagePage+countPage)+"&resrv_userid="+resrv_userid;
}

function pageLast(totalPage,resrv_userid){
	location.href="./resrv_recordList.do?page="+totalPage+"&resrv_userid="+resrv_userid;
}