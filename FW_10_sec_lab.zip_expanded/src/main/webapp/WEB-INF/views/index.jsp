<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/header.jsp"%>
    <div class="container">
        <h1>Spring Security</h1>
        <ul>
            <li><a href="${root }/secured/user" class="mx-3">User의 공간</a></li>
            <li><a href="${root }/secured/admin" class="mx-3">Admin의 공간</a></li>
            <li><a href="${root }/member/logout" class="mx-3">로그아웃</a></li>

        </ul>
    </div>
    <%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
<script src="/js/common.js"></script>
</html>
