$(document).ready(function(){
   const target = document.getElementsByClassName("ck-content")[0]
   
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
   
   observer = new MutationObserver(callback); 
   
   const config = { 
       childList: true, 
   };
   
   observer.observe(target, config);
   
});

// 대분류 선택
var codeInput = function(){
	console.log("동물 종은 : "+ value);
	$("#codeInput").val(value);
}

// 소분류 선택
var partInput = function(){
	console.log("증상 부위는: "+ value);
	$("#partInput").val(value);
}

// submit 버튼 동작 
function writeSubmit(){
	const data = editor.getData();
	$("#editor").text(data);
	
	let aCode = $('#aCode').val();
	let aPart = $('#aPart').val();
	let questTitle = $('#questTitle').val();
	console.log(aCode, aPart, questTitle);
	
	if(aCode == "--동물 종을 선택하세요--"){
		alert("동물 종을 선택하세요");
		document.getElementById("aCode").focus();
		return false;
	}else if(aPart == "--증상이 있는 부위를 선택하세요--"){
		alert("증상이 있는 부위를 선택하세요");
		document.getElementById("aPart").focus();
		return false;
	}else if(questTitle == ""){
		alert("제목을 입력하세요");
		document.getElementById("questTitle").focus();
		return false;
	}
	
   observer.disconnect();
   $("form").eq(0).submit();
	
}






