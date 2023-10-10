<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료기록 상세조회</title>
<link rel="stylesheet" href="./css/chart_detail.css">
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
			<table id="chartInfo">
				<tr>
					<th>반려동물</th>
					<td>${pvo.pet_name}</td>
				</tr>
				<tr>
					<th>진료일자</th>
					<td>${pvo.medichart_vo[0].medi_visit}</td>
				</tr>
				<tr>
					<th>진료과목</th>
					<td>${pvo.medichart_vo[0].medi_lname}</td>
				</tr>
				<tr>
					<th>세부과목</th>
					<td>${pvo.medichart_vo[0].medi_sname}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${pvo.medichart_vo[0].medi_title}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="content" class="ck-content">
							${medi_content}
						</div>
					</td>
				</tr>
			</table>
			
			<div class="buttons_right">
				<input type="hidden" value="${pvo.medichart_vo[0].medi_num}" name="medi_num">
				<button onclick="location.href='./modifyChartForm.do?medi_num=${pvo.medichart_vo[0].medi_num}'">수정</button>
				<button value="${pvo.medichart_vo[0].medi_num}" onclick="delChart(this.value)">삭제</button>
			</div>	
			<div class="buttons_left">
				<button onclick="location.href='./pdfDownload.do?medi_num=${pvo.medichart_vo[0].medi_num}'">PDF 다운로드</button>
				<button id="allMenuButton" onclick="location.href='./selectAllChartPaging.do'">전체목록보기</button>
			</div>
	</div>	
</body>
<script type="text/javascript">
	function delChart(val) {
		if(confirm('삭제하시겠습니까?')==true){
			console.log(val);
			location.href='./deleteChart.do?medi_num='+val
		}else{
			return false;
		}
	}
</script>
<%@ include file="./footer.jsp" %>
</html>