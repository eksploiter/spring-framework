<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	max-width: 800px;
	margin: 20px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label, input, textarea, button {
	display: block;
	width: 100%;
	margin-top: 10px;
}

input, textarea {
	padding: 8px;
	box-sizing: border-box;
}

button {
	padding: 10px 15px;
	margin-top: 10px;
	background-color: #333;
	color: white;
	border: none;
	cursor: pointer;
}

button.btn-secondary {
	background-color: #777;
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
	<div
		style="max-width: 800px; margin: 20px auto; padding: 20px; background-color: white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
		<h2>상품 정보 등록</h2>
		<form id="registForm" method="post" action="">
			<label for="productCode">고유번호</label> 
			<input type="text" id="productCode" name="code" placeholder="고유번호 입력"> 
			<label for="model">모델명</label> 
			<input type="text" id="model" name="model" placeholder="모델명 입력"> 
			<label for="price">가격</label>
			<input type="number" id="price" name="price" placeholder="가격 입력"> 
			<label for="userId">등록자 ID</label> 
			<input type="text" id="userId" name="userId"> 
			<label for="description">상세설명</label>
			<textarea id="description" name="detail"></textarea>

			<button type="submit" class="btn-primary">등록</button>
			<button type="button" class="btn-secondary"
				onclick="location.href=''">취소</button>
		</form>
	</div>
</body>
</html>
