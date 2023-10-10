<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./js/chart_allChart.js"></script>
<link rel="stylesheet" href="./css/chart_main.css">
<title>전체 진료기록 리스트</title>
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
			<h3 id="listname">진료기록 전체목록</h3>
			<div id="petsPart">
				<c:choose>
				<c:when test="${empty allPets}">
					<p class="noneChart">등록된 반려동물 정보가 없습니다</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="pet" items="${allPets}" varStatus="vs">
						<button class="selectPet" value="${pet.pet_seq}" onclick="choice(this)">${pet.pet_name}</button>
					</c:forEach>
				</c:otherwise>
				</c:choose>
				<button class="selectPet" onclick="location.href='./selectAllChartPaging.do'">전체</button>
				<button onclick="location.href='./insertNewChartForm.do'" id="newChart">진료기록 작성</button>
			</div>
			<br>
			<div id="selectPart">
				<select id="codeL" onchange="codeLChange()">
					<option>--진료과목--</option>
					<c:forEach var="codelist" items="${codelists}" varStatus="vs">
						<option value="${codelist.medi_code}">${codelist.medi_name}</option>			
					</c:forEach>
				</select>
				
				<select id="codeS">
					<option>--세부과목--</option>
				</select>
				<button id="selectMediCode" onclick="selectCode()">조회</button>
			</div>
			
			<div id="chartPart">
				<c:choose>
					<c:when test="${empty chart_lists}">
						<p class="noneChart">등록된 진료기록이 없습니다</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="list" items="${chart_lists}" varStatus="vs">
							<c:forEach var="mlist" items="${list.medichart_vo}">
									<div class="detail"> 
										<table class="detailTable">
												<tr>
													<td>
														<input type="hidden" value="${mlist.medi_num}" class="medi_num">
													</td>
												</tr>
												<tr>
													<th>반려동물</th>
													<td>${list.pet_name}</td>
												</tr>
												<tr>
													<th>진료기록</th>
													<td>${mlist.medi_title}</td>
												</tr>
												<tr>
													<th>진료날짜</th>
													<td>${mlist.medi_visit}</td>
												</tr>
										</table>
									</div>
							</c:forEach>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<br>
			<div id="pageBtnPart">
				<a href="#" id="prevBtn" onclick="pagePrev(${page.stagePage})"><img src="./img/back.png"></a>
				<a href="#" id="nextBtn" onclick="pageNext(${page.stagePage},${page.totalPage})"><img src="./img/forward.png"></a>
			</div>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>