<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp"></jsp:include>

	<div class="container">
		<div class="row mt-3">
			<h2 class="bg-primary text-light text-center">마이페이지</h2>
		</div>

		<form action="${root}/user/modify" method="post" class="row">
			<table class="table">
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="userId" value="${user.userId}"
							readonly /></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" value="${user.name}"
							required /></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text" name="password"
							value="${user.password}" required /></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" name="email" value="${user.email}"
							required /></td>
					</tr>
				</tbody>
			</table>

			<div class="text-center">
				<input type="submit" class="btn btn-primary" value="정보 수정" /> <a
					href="${root}/user/logout" class="btn btn-secondary">로그아웃</a>
			</div>
		</form>
	</div>
</body>
</html>
