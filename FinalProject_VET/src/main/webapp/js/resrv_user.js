
function pageFirst(){
	console.log("pageFirst 처음페이지로 이동");
	spa_ajax(1);
}

function pagePrev(stagePage, countPage){
	console.log("pagePrev 이전 페이지 묶음으로 이동");
	var page = (stagePage - countPage) < 0 ? 1 : (stagePage-countPage);
	spa_ajax(page);
}

function page(page){
	console.log("page 현재 선택된 페이지로 이동");
	spa_ajax(page);
}

function pageNext(stagePage, countPage){
	console.log("pageNext 다음 페이지 묶음으로 이동");
	spa_ajax(stagePage+countPage);
}

function pageLast(totalPage){
	console.log("pageLast 끝 페이지로 이동");
	spa_ajax(totalPage);
}

//TODO 19_04 SPA로 처리할 jQuery Ajax 익명함수



var spa_ajax = function(...args){
	var choice_page = args[0];
	console.log("choice_page : ",choice_page);
	
//	$.ajax({
//		url:"./resrv_recordList.do",
//		type:"post",
//		async:true,
//		data: {"page": choice_page},
//		dataType:"json",
//		success: function(data){
//			console.log(data);
//		},
//		error: function(e){
//			console.log("잘못된 요청처리 ",e);
//		}
//	});
}
//-----------------------------------------

