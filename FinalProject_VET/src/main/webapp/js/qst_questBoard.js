//searchSubmit.addEventListener("click", e => {
////function search(){
//	var code = aCode.value;
//	var part = aPart.value
//	var text = searchBar.value;
//	console.log(code, part, text);
//	
//	$.ajax({
//		url:"./selectPartQuest.do",
//		method:"get",
//		data:"qst_species=" +code+ "&qst_part=" +part+ "&qst_content=" +text,
//		success:function(res){
//			document.querySelector('html').innerHTML = res;
//		},
//		error:function(){
//			alert("잘못된 요청");
//		}
//	})
//	
//})
	
//}

function selected(seq){
console.log(seq);
var modal = document.getElementById("modalWindow");
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
	var result_btn = document.getElementById("choiceModal");
	result_btn.value = seq;
	console.log("결과",result_btn.value);
	var modal = document.getElementById("modalWindow");
	modal.style.display = "block";
	var close = document.getElementById("closeModal");
	close.onclick = function() {
	  modal.style.display = "none";
	}
}

function result(){
	var result_btn = document.getElementById("choiceModal").value;
	var modal = document.getElementById("modalWindow");
	var button = document.getElementById("openModal");
	window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

	console.log(result_btn)
	$.ajax({
		url:"./chooseReply.do",
		method:"get",
		data:"seq="+result_btn,
		success:function(msg){
			modal.style.display="none";
			button.style.display="none";
			location.href="";
			
		},
		error:function(){
			alert("잘못된 요청");
			modal.style.display="none";
		}
	});
}

function writeReplyForm(){
	writeReplyArea.style.display = "block";
	
}

function writeModifyForm(){
	writeReplyArea.style.display = "block";
	
}

//  submit 버튼 동작 
function replySubmit(){
	const data = editor.getData();
	$("#editor").text(data);	
	
	
   observer.disconnect();
   $("form").eq(0).submit();
	
}











