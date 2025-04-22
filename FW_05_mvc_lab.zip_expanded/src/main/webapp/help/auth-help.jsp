<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/fragments/header.jsp"%>
    <div class="container">
       <h1>Auth 도움말</h1>
       <h3>Auth 페이지는 로그인한 사용자만 접근 가능합니다.</h3>
       <ul>
           <li>회원 상세 보기</li>
           <li>회원 수정 폼 제공</li>
           <li>회원 수정</li>
           <li>회원 삭제</li>
           <li>주소 추가</li>
           <li>주소 삭제</li>
       </ul>
    </div>
    <%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
</html>
