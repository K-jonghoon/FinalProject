<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 상세조회</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_Calendar.js'></script> <!-- 작성할 js -->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/calendar.css">
<style type="text/css">
	#buttonArea{
		display: block;
		text-align: center;
	}
	
</style>
</head>
<body>
<%@ include file="./navbar.jsp" %>
   <div class="navContainer">
<%--    ${lists} --%>
   <div id="hosp_infomation">
      <h2 id="hosp_infoMNG">내 정보</h2>
      <table id="hosp_detail">
         <tr>
            <th>ID</th>
            <td>${lists[0].users_id}</td>
         </tr>
         <tr>
            <th>이름</th>
            <td>${lists[0].users_name}</td>
         </tr>
         <tr>
            <th>전화번호</th>
            <td>${lists[0].users_tel}</td>
         </tr>
         <tr>
            <th>주소</th>
            <td>${lists[0].users_addr}</td>
         </tr>
         <tr>
            <th>가입일</th>
            <td>${lists[0].users_joindate}</td>
         </tr>
         <tr>
            <th>추가 연락처</th>
            <td>${lists[0].users_subtel}</td>
         </tr>
      </table>
      </div>
      
      <br><br>
      <div id="buttonArea">
<!-- 	      <input type="button" value="내 반려동물 보기" onclick="location.href='./selectPetsDetail.do'"> -->
	      <input type="button" value="내 정보 수정" onclick="location.href='./updateUser.do'">
	      <input type="button" value="회원탈퇴" onclick="location.href='./resignUser.do'">
      </div>
   </div>
</body>
<%@ include file="./footer.jsp" %>
</html>