<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 - 새 글 작성</title>
<link rel="stylesheet" href="./css/ckeditor.css">
<link rel="stylesheet" href="./css/index.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<style type="text/css">
	input[type="submit"]{
		background-color: #D7CCC8;
		width: 110px;
		height: 20px;
		border-radius: 5px;
		color: #3E2723;
		border: none;
		box-shadow: 1.5px 1.5px 1.5px 0 #3E2723;
	}
	
	#inputButton{
		text-align: center;
	}
	
	input[type="text"]{
		width: 500px;
		height: 30px;
		border-radius: 1px;
		border-width: 0.5px;
		border-color: #CCCCCC;
	}
</style>
</head>
<%@ include file="./header.jsp" %>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
	<div id="container">
		<br>
		<h3 style="text-align: left;">공지사항 작성</h3>
		<br>
		<form action="./insertNotice.do" method="post">
			<table>
				<tr>
					<th>제목</th>
					<td>&nbsp;&nbsp;<input type="text" name="noti_title" required="required"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>&nbsp;&nbsp;관리자</td>
				</tr>
			</table>
			<br>
			<textarea id="editor" name="noti_content"></textarea>
			<input type="hidden" name="noti_id" value="${loginVo.users_id}">
			<br>
			<div id="inputButton">
			<c:if test="${loginVo.users_auth eq 'A' }">
				<input type="submit" value="작성">
			</c:if>
			</div>
			</form>
		</div>
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
</body>
<%@ include file="./footer.jsp" %>
</html>