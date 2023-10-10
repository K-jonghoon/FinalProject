<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 화면</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="./css/qst_questBoard.css">
<link rel="stylesheet" href="./css/ckeditor.css">
<%@ include file="./header.jsp" %>
</head>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
<div id="detailContainer">
	<form action="./writeModify.do" method="post">
	<!-- 카테고리 선택 -->
	<select id="aCode" name="qst_species" class="selectPart">
		<option disabled="disabled" selected="selected" value="">--동물 종을 선택하세요--</option>
		<option value="A">개</option>
		<option value="B">고양이</option>
		<option value="C">파충류</option>
		<option value="D">조류</option>
		<option value="F">어류</option>
		<option value="Z">기타</option>
	</select>
	<input type="hidden" name="codeInput" required="required">
	<select id="aPart" name="qst_part" class="selectPart">
		<option disabled="disabled" selected="selected">--증상이 있는 부위를 선택하세요--</option>
		<option value="01" >피부</option>		
		<option value="02">눈</option>
		<option value="03">치아</option>
		<option value="04">위,장</option>
		<option value="05">신장,방광</option>
		<option value="06">뼈,관절</option>
		<option value="07">심장</option>
		<option value="08">간</option>
		<option value="09">면역력,호흡기</option>
		<option value="10">기타</option>
	</select>
	<input type="hidden" name="partInput" required="required">
	
	<!-- 빠른문의 게시글 활성화 -->
<%-- 	<input type="hidden" name="qst_fast" value="${dto.qst_fast}">빠른문의 게시글 --%>
<!-- 	 <a style="font-size: 8px; color: grey;">※ 빠른문의 작성 시 500포인트가 차감됩니다.</a> -->

	<!-- 제목 작성 -->
	<input id="questTitle" type="text" name="questTitle" style="width:100%;" value="${dto.qst_title}" required >
	
	<!--  내용 작성 -->
	<textarea id="editor" name="questContent" required>
	</textarea>
	</form>
	
	<!-- 완료 버튼 -->
	<button type="submit" onclick="writeSubmit()">입력</button>
	<input type="submit" value="취소" onclick="location.href='./questBoard.do'">
	
</div>
</body>
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
<script type="text/javascript" src="./js/qst_modifyQuest.js"></script>

<script type="text/javascript">
	editor.setData('${dto.unescapeContent}');
	const selectedQstSpecices = '${dto.qst_species}';
	const selectedQstPart = '${dto.qst_part}';
	document.querySelector('option[value='+ selectedQstSpecices +']').selected = true;
	document.querySelector('option[value="'+ selectedQstPart +'"]').selected = true;
</script>

<%@ include file="./footer.jsp" %>
</html>