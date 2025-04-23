<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/menu.jsp"></jsp:include>
    <div class="container">
        <div class="row mt-3">
            <h2 class="bg-primary text-light text-center">회원가입</h2>
        </div>
        <c:if test="${joinError != null}">
            <div class="row mt-3">
                <h4 class="bg-warning text-light text-center">${joinError}</h4>
            </div>
        </c:if>
        <form action="${root}/user/register" method="post" class="row">
            <table class="table">
                <tbody>
                    <tr>
                        <th><label for="userid">아이디</label></th>
                        <td><input type="text" name="userid" id="userid" required /></td>
                    </tr>
                    <tr>
                        <th><label for="password">비밀번호</label></th>
                        <td><input type="password" name="password" id="password"
                            required /></td>
                    </tr>
                    <tr>
                        <th><label for="confirmPassword">비밀번호 확인</label></th>
                        <td><input type="password" name="confirmPassword"
                            id="confirmPassword" required /></td>
                    </tr>
                    <tr>
                        <th><label for="name">이름</label></th>
                        <td><input type="text" name="name" id="name" required /></td>
                    </tr>
                    <tr>
                        <th><label for="email">이메일</label></th>
                        <td><input type="email" name="email" id="email" required /></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2" class="text-center"><input type="submit"
                            value="회원가입" /> <input type="reset" value="초기화" /></td>
                    </tr>
                </tfoot>
            </table>
        </form>
    </div>
</body>
</html>

