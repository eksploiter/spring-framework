<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/fragments/header.jsp"%>
    <div class="container">
      <h1>도움말 페이지</h1>
      <ul>
        <li><a href="${root }/auth/help">Auth 도움말</a></li>
      </ul>
      <h1>에러 페이지</h1>
      <ul>
        <li><a href="${root }/simple/some">404</a></li>
        <li><a href="${root }/member/login">405</a></li>
        <li><a href="${root }/simple/problem">500(normal)</a></li>
        <li id="jsonproblem">500(json)</li>
      </ul>
      <hr />
      <h1>파일 업로드</h1>
      <!-- TODO: 14-1. 파일 업로드를 위한 form을 확인하세요. -->
      <form action="${root }/simple/fileupload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="text" name="desc" />
        <button>전송</button>
      </form>
      <hr />
      <!-- TODO: 14-4. 파일 다운로드를 위한 태그를 확인하세요. -->
      <div>
        <ul>
          <li>file: <a href="${root }/upload/${file }" download="${file }">${file }</a></li>
          <li>desc: ${desc }</li>
          <li>alertMsg: ${alertMsg }</li>
        </ul>
      </div>
    </div>
    <%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
  </body>
  <script src="/js/common.js"></script>
  <script>
    document.querySelector("#jsonproblem").addEventListener("click", async () => {
      const result = await getFetch("${root}/simple/jsonproblem");
      console.log(result);
    });
  </script>
</html>
