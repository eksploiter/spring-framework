<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<body>

	<jsp:include page="/common/menu.jsp"/>
	<div class="container">
		<div class="row mt-3">
			<h2 class="bg-primary text-light text-center">상품 목록</h2>
		</div>
		<div class="row">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>상품이름</th>
						<th>상품가격</th>
					</tr>			
				</thead>	
				<tbody>
				<c:choose>
					<c:when test="${not empty products}">
						<!--  상품 있는 경우 -->
						<c:forEach items="${products}" var="product">
							<tr>
								<td>${product.productId}</td>
								<td>
<a href="${root}/main?action=product_detail&productId=${product.productId}">${product.name}</a></td>
								<td>${product.price}원</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<!--  상품 없는 경우 -->
						<tr>
							<td colspan="3">등록된 상품이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				
				
				</tbody>		
			</table>
		</div>
	</div>
	
</body>
</html>







