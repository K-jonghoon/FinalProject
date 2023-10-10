<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="./css/user_resrvRecord.css">
<body>
	<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
		<h3 id="listname">예약 내역</h3>
		<table class="resrv_record">
			<thead>
				<tr>
					<th>예약번호</th>
					<th>예약병원</th>
					<th>방문일자</th>
					<th>예약시간</th>
					<th>예약상태</th>
					<th id="cancel">예약취소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${hosp_lists}">
					<tr>
						<td>${list.resrv_num}</td>
						<td>${list.resrv_hops}</td>
						<td>${list.resrv_visit}</td>
						<td>${list.resrv_time}:00</td>
						<c:choose>
							<c:when test="${list.resrv_status eq 'Y'}">
								<td>확정</td>
							</c:when>
							<c:when test="${list.resrv_status eq 'N'}">
								<td>취소</td>
							</c:when>
							<c:when test="${list.resrv_status eq 'X'}">
								<td>거절</td>
							</c:when>
							<c:otherwise>
								<td>대기중</td>
							</c:otherwise>
						</c:choose>
						<td>
							<fmt:parseDate var="resrv_visit" value="${list.resrv_visit}" pattern="yyyy-MM-dd"/>
							<c:set var="now" value="<%= new Date()%>"/>
							<c:set var="visitTime" value="${resrv_visit.time}"/>
							<c:if test="${(now.time-visitTime)<0}">
								<c:if test="${list.resrv_status eq 'Y' || list.resrv_status eq 'W'}">
									<button class="resrv_cancel">예약 취소</button>
								</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<ul>
							<c:if test="${page.stagePage > 1}">
								<li><a href="#" onclick="pageFirst('${sessionScope.loginVo.users_id}')"><img src="./img/fast_left.png"></img></a></li>
								<c:if test="${page.stagePage - page.countPage >= 0}">
									<li><a href="#" onclick="pagePrev(${page.stagePage}, ${page.countPage},'${sessionScope.loginVo.users_id}')"><img src="./img/back.png"></img></a></li>
								</c:if>
							</c:if>
							
							<c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1">
								<li ${page.page == i ? 'class=active':''}><a href="javascript:page(${i},'${sessionScope.loginVo.users_id}')">${i}</a></li>
							</c:forEach>
						
							
							<c:if test="${page.page < page.totalPage}">
								<c:if test="${page.stagePage+page.countPage < page.totalCount}">
									<li><a href="#" onclick="pageNext(${page.stagePage}, ${page.countPage},'${sessionScope.loginVo.users_id}')"><img src="./img/forward.png"></img></a></li>
								</c:if>
								<li><a href="#" onclick="pageLast(${page.totalPage},'${sessionScope.loginVo.users_id}')"><img src="./img/fast_right.png"></img></a></li>
							</c:if>
						</ul>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	<script type="text/javascript" src="./js/user_resrvRecord.js"></script>
</body>
<%@ include file="./footer.jsp" %>
</html>