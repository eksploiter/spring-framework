<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 관리 사이트</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.navbar {
	background-color: #333;
	color: white;
	padding: 10px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.navbar a {
	color: white;
	text-decoration: none;
	padding: 10px 15px;
}

.container {
	max-width: 1200px;
	margin: 20px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	margin-top: 20px;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

form {
	margin-bottom: 20px;
}

input[type="date"], input[type="submit"] {
	padding: 8px;
}
</style>
</head>
<body>
	<div class="navbar">
		<div>
			<a href="">상품 관리</a>
		</div>
		<div>
			<c:if test="">
				<a href="">로그인</a>
			</c:if>
			<c:if test="">
        	님, 로그인 하였습니다.
        	<a href="">로그아웃</a>
			</c:if>
			<a href="">상품 목록</a> <a
				href="">상품 정보 등록</a>
		</div>
	</div>

	<div class="container">
		<h1>상품 목록</h1>
		<form method="get">
			<label for="searchDate">날짜 검색:</label> <input type="date"
				id="searchDate" name="searchDate"> <input
				type="submit" value="검색">
		</form>
		<table>
			<tr>
				<th>Code</th>
				<th>Model</th>
				<th>Price</th>
				<th>ID</th>
				<th>Regist Date</th>
			</tr>
			<c:forEach var="" items="">
				<tr>
					<td><a href=""></a></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
