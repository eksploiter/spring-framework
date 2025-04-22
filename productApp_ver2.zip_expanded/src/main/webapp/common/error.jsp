<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/common/menu.jsp"/>
	<div class="container">
		<h3>처리 중 문제가 발생하였습니다</h3>
		<h4>${errorMsg}</h4>
	</div>	
</body>
</html>