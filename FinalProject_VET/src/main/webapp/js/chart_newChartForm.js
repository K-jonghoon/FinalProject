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

//선택된 대분류에 따라 onchange이벤트로 소분류 select option 변경
function codeLChange(){
      console.log("codeLChange 실행");
      var codeL = document.getElementById("codeL");
      var selectedValue = codeL.options[codeL.selectedIndex].value;
      console.log("선택된 medi_lname : ", selectedValue);
      $("#medi_lnameInput").val(selectedValue);
      
      $.ajax({
         url:'./listByCodeS.do',
         method:'post',
         data: "medicodeL="+selectedValue,
         success:function(data){
            var obj = $("#codeS");
            var html = "";
            html +="<option>--선택--</option>";
            for(let i=0; i<data.list.length ;i++ ){
                html += "<option value='"+data.list[i].medi_code+"' id='medi_code'>"+data.list[i].medi_name+"</option>";
               obj.append(html);
            }
            obj.empty().append(html);
         },
         error:function(){
            console.log("값 전달 오류");
         }
      });
      
   }

//선택된 반려동물 고유번호(pet_seq) 확인 및 화면 전송(<input type="hidden"> 처리)
var selectedPet = function(value){
   console.log("선택된 pet_seq : "+ value);
   $("#selectedPetInput").val(value);
}

//선택된 진료코드 소분류(medi_lname) 확인 및 화면 전송(<input type="hidden"> 처리)
var selectedSCode = function(value){
   console.log("선택된 medi_sname :"+ value);
   $("#medi_snameInput").val(value);
}

//작성된 진료기록 submit 처리
function writeChart(){
   const data = editor.getData();
   $("#editor").text(data);

   let codeL = $('#codeL').val();
   let codeS = $('#codeS').val();
   let petName = $('#petName').val();
   let medi_title = $('#medi_title').val();
   let medi_content = $('#editor').val();
   
   console.log(codeL,codeS,petName);
   
   if(codeL == "--진료 과목--"){
      alert("진료과목을 선택해주세요");
      document.getElementById("codeL").focus();
      return false;
   }else if(codeS =="--선택--"){
      alert("세부과목을 선택해주세요");
      document.getElementById("codeS").focus();
      return false;
   }else if(petName=="--선택--"){
      alert("반려동물을 선택해주세요");
      document.getElementById("petName").focus();
      return false;
   }else if(medi_title==""){
      alert("제목을 작성해주세요");
      document.getElementById("medi_title").focus();
      return false;
   }else if(medi_content==""){
	  alert("내용을 작성해주세요")
	  document.getElementById("editor").focus();
	  return false;
}
   
   observer.disconnect();
   $("form").eq(0).submit();
}