<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="/common/menu.jsp"/>
	<div class="container">
		<div class="row mt-3">
			<h2 class="bg-primary text-light text-center">상품 등록</h2>
		</div>
	<form method="post" action="${root}/main">
        <table class="table">
            <tbody>
                <tr>
                    <th><label for="name">상품 이름</label></th>
                    <td><input type="text" name="name" id="name"/></td>
                </tr>
                <tr>
                    <th><label for="category">상품 카테고리</label></th>
                    <td><input type="text" name="category" id="category"/></td>
                </tr>
                <tr>
                    <th><label for="brand">상품 브랜드</label></th>
                    <td><input type="text" name="brand" id="brand"/></td>
                </tr>
                <tr>
                    <th><label for="price">상품 가격</label></th>
                    <td><input type="number" name="price" id="price"/></td>
                </tr>
                <tr>
                    <th><label for="stockQuantity">상품 수량</label></th>
                    <td><input type="number" name="stockQuantity" id="stockQuantity"/></td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2">
						<input type="hidden" name="action" value="product_insert">                    
                        <input type="submit" value="등록"/>
                        <input type="reset" value="취소"/>
                    </td>
                </tr>
            </tfoot>
        </table>
    </form>
    </div>
</body>
</html>
    