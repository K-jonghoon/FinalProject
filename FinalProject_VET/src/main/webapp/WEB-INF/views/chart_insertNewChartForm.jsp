<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 진료기록 작성</title>
<link rel="stylesheet" href="./css/ckeditor.css">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/chart_insert.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<%@ include file="./header.jsp" %>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
	<div id="container">
		<form action="./insertNewChart.do" method="post">
			<c:choose>
				<c:when test="${not empty medi_visit}">
					진료날짜 : <input type="date" id="medi_visit_resv" name="medi_visit" class="selectPart" value="${medi_visit}"><br>
				</c:when>
				<c:otherwise>
					진료날짜 : <input type="date" id="medi_visit" name="medi_visit" class="selectPart"><br>
				</c:otherwise>
			</c:choose>
			
			진료과목 : <select id="codeL" onchange="codeLChange()" name="codeL" class="selectPart">
						<option id="medi_l">--진료 과목--</option>
						<option value="00">일반진료</option>
						<option value="01">내과</option>
						<option value="02">외과</option>
						<option value="03">접종</option>
					</select>
					<input type="hidden" id="medi_lnameInput">
					<select id="codeS" onchange="selectedSCode(this.value);" name="codeS" class="selectPart">
						<option>--세부 과목--</option>
					</select>
					<input type="hidden" id="medi_snameInput">
			<br>
			
			반려동물 : <select id="petName" name="petName" onclick="selectedPet(this.value);" class="selectPart">
						<option>--선택--</option>
						<c:forEach var="pets" items="${petList}" varStatus="vs">
							<option value="${pets.pet_seq}" id="mpet_seq">${pets.pet_name}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="selectedPetInput">
					<br>
					
				<c:choose>
					<c:when test="${not empty medi_title}">
						진료제목 : <input type="text" id="medi_title" name="medi_title" maxlength="25" required="required" value="${medi_title} 진료">
					</c:when>
					<c:otherwise>
						진료제목 : <input type="text" id="medi_title" name="medi_title" maxlength="25" required="required">
					</c:otherwise>
				</c:choose>
				
				<textarea id="editor" name="medi_content"></textarea>
		</form>
		<div class="buttonPart">
				<button onclick="writeChart()" id="writeBoard">글쓰기</button>
				<button onclick="location.href='./selectAllChart.do'" id="backToList">전체목록</button>
		</div>
	</div>
	
	
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
<script type="text/javascript" src="./js/chart_newChartForm.js"></script>
<!-- 오늘 날짜로 초기값 설정 -->
<script type="text/javascript">
document.getElementById('medi_visit').value = new Date().toISOString().substring(0, 10);
</script>
</body>

<%@ include file="./footer.jsp" %>
</html>