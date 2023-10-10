$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"./getContent.do",
		data: "medi_num="+$("#medi_num").val(),
		success:function(data){
			editor.setData(data);
		},
		error:function(){
//			alert("getContent.do 잘못된 요청입니다.");
		}
	});
	
	// 1. 감시 대상
	const target = document.getElementsByClassName("ck-content")[0]
	
	// 2. 옵저버 콜백 생성
	const callback = (mutationList, observer) => {
		const node = mutationList[0].removedNodes[0].childNodes[0]
	  	if(node.localName == "img"){
		$.ajax({
			type:"post",
			url:"./removeImage.do",
			data: "saveName="+node.currentSrc.substring(node.currentSrc.lastIndexOf("/")+1),
			error: function(){
				alert("getContent.do 잘못된 요청입니다.");
			}
		});
		}
	};
	
	// 3. 옵저버 인스턴스 생성
	observer = new MutationObserver(callback); // 타겟에 변화가 일어나면 콜백함수를 실행하게 된다.
	
	// 4. DOM의 어떤 부분을 감시할지를 옵션 설정
	const config = { 
	    childList: true, // 자식노드 추가/제거 감지
	};
	
	// 5. 감지 시작
	observer.observe(target, config);
});

function updateBoard(){
	const data = editor.getData();
	$("#editor").text(data);
	
	// 6. 감지 중지
	observer.disconnect();
	
	$("form").eq(0).submit();
}