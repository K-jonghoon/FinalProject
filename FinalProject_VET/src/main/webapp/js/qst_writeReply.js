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

function writeReplyForm(){
	writeReplyArea.style.display = "block";
	
}


//  submit 버튼 동작 
function writeSubmit(){
	const data = editor.getData();
	$("editor").text(data);
	
   observer.disconnect();
   $("form").eq(0).submit();
	
}






