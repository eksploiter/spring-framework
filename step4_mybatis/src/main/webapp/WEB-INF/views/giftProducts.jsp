<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사은품 목록</title>
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
			<h2 class="bg-success text-light text-center">사은품 목록</h2>
		</div>
		<div class="row">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>사은품 이름</th>
						<th>연결된 상품 ID</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty giftProducts}">
							<!-- 사은품 있는 경우 -->
							<c:forEach items="${giftProducts}" var="gift">
								<tr>
									<td>${gift.giftId}</td>
									<td><a href="${root}/gift/detail/${gift.giftId}">${gift.name}</a></td>
									<td>${gift.productId}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<!-- 사은품 없는 경우 -->
							<tr>
								<td colspan="3">등록된 사은품이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		<div class="row">
			<div class="col text-end">
				<a href="${root}/gift/register" class="btn btn-success">사은품 등록</a>
			</div>
		</div>
	</div>
</body>
</html>
