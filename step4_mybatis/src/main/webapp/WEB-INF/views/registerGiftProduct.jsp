<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사은품 등록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script defer
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<div class="container">
		<div class="row mt-3">
			<h2 class="bg-success text-light text-center">사은품 등록</h2>
		</div>
		<form method="post" action="${root}/gift/insert">
			<table class="table">
				<tbody>
					<tr>
						<th><label for="name">사은품 이름</label></th>
						<td><input type="text" name="name" id="name" required /></td>
					</tr>
					<tr>
						<th><label for="productId">연결된 상품 ID</label></th>
						<td><input type="number" name="productId" id="productId" required /></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="submit" value="등록" class="btn btn-primary" />
							<input type="reset" value="취소" class="btn btn-secondary" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>
