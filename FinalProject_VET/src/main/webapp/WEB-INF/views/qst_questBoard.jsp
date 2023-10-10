<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료문의 게시판</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script type="text/javascript" src="./js/paging.js"></script>
<link rel="stylesheet" href="./css/qst_questBoard.css">
</head>
<%@ include file="./header.jsp" %>
<body>
<div id="container">

<div id="searchArea">
<form id="formQuest" action="./questBoard.do">
	<input type="hidden" name="page" value="${page.page}"/>
	<select id="aCode" name="qst_species">
		<option disabled="disabled" selected="selected" value=" ">--어떤 동물인가요?--</option>
		<option value="A">개</option>
		<option value="B">고양이</option>
		<option value="C">파충류</option>
		<option value="D">조류</option>
		<option value="F">어류</option>
		<option value="Z">기타</option>
	</select>
	<select id="aPart" name="qst_part">
		<option disabled="disabled" selected="selected" value=" ">--증상 부위--</option>
		<option value="01">피부</option>
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
	<input id="searchBar" type="text" name="qst_content" placeholder="검색어를 입력하세요">
	<input class="basicBtn" id="searchSubmit" type="submit" value="검색" >
</form>
	<c:if test="${loginVo.users_auth eq 'U'}">
		<button class="basicBtn" style="float:right;" onclick="location.href='./writeQuestForm.do'">새 글 작성</button>
	</c:if>
</div>

<div id="contentArea">
		<c:set var="loop_flag" value="false" />
		<c:forEach var="dto" items="${questList}">
		<c:choose>
			<c:when test="${dto.qst_fast eq 'Y'}">
			<c:set var="loop_flag" value="true" />
				<div class="card">
					<table class="questTop">	
						<tr>
							<td class="thumbnail"></td>
							<td class="questId">${dto.users_name}</td>
							<td class="questCategory">${dto.anm_species}</td>
							<td class="questPart">${dto.part_name}</td>
							<td class="questTitle">❓${dto.qst_title}</td>
							<td class="questDate">
								<fmt:parseDate var="questDate" value="${dto.qst_regdate}" pattern="yyyy-MM-dd HH:mm"/>
								<fmt:formatDate value="${questDate}" pattern="yyyy-MM-dd HH:mm"/>
							</td>
						</tr>
					</table>
					<div class="questBottom">
						<a href="./questDetail.do?seq=${dto.qst_seq}">${dto.unescapeContent}</a>
					</div>
				</div>
				<div class="blank"></div>
			</c:when>
			<c:otherwise>
				<div class="card">
					<table class="questTop">
						<tr>
							<td class="thumbnail"></td>
							<td class="questId">${dto.users_name}</td>
							<td class="questCategory">${dto.anm_species}</td>
							<td class="questPart">${dto.part_name}</td>
							<td class="questTitle">❔${dto.qst_title}</td>
							<td class="questDate">
								<fmt:parseDate var="questDate" value="${dto.qst_regdate}" pattern="yyyy-MM-dd HH:mm"/>
								<fmt:formatDate value="${questDate}" pattern="yyyy-MM-dd HH:mm"/>
							</td>
						</tr>
					</table>
					<div class="questBottom">
						<a href="./questDetail.do?seq=${dto.qst_seq}">${dto.unescapeContent}</a>
					</div>
				</div>
				<div class="blank"></div>
			</c:otherwise>
		</c:choose>
		</c:forEach>
</div>

<!-- 페이징 -->
<div id="pagingArea">
	<ul class="pages">
		<c:if test="${page.stagePage > 1}">
			<li ><a href="#" onclick="pageFirst()"><span>《</span></a></li>
			<c:if test="${page.stagePage - page.countPage >= 0 }">
				<li><a href="#" onclick="pagePrev(${page.stagePage},${page.countPage})"><span>〈</span></a></li>
			</c:if>
		</c:if>
		
		<c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1">
			<li ${page.page == i ? 'class = active':'' }><a href="javascript:page(${i}, '${queryString}')">${i}</a></li>
		</c:forEach>
		
		<c:if test="${page.page < page.totalPage }">
			<c:if test="${page.stagePage+page.countPage < page.totalCount }">
				<li><a href="#" onclick="pageNext(${page.stagePage},${page.countPage})"><span>〉</span></a></li>
			</c:if>
			<li><a href="#" onclick="pageLast(${page.totalPage})"><span>》</span></a></li>
		</c:if>
	</ul>
</div>

</div>
</body>
<%@ include file="./footer.jsp" %>
<script type="text/javascript" src="./js/qst_questBoard.js"></script>
</html>