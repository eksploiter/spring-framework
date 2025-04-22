<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/header.jsp"%>
    <div class="container">
        <h1>rest client(ajax)</h1>
        <!-- TODO: 02-5. ajax를 이용해 REST API를 호출하는 모습을 살펴보자. -->
        <form>
            <input type="text" id="email" value="ajax@ssafy.com" /> <input
                type="text" id="name" value="ajax" /> <input type="text"
                id="password" value="1234" />
            <button id="btnRegist" type="button">등록</button>
        </form>
        <hr>
        <button id="btnSearchSido1">sido 조회(direct)</button>
        <button id="btnSearchSido2">sido 조회(restTemplate)</button>
        <hr>
        <div>
            <p>호출 결과:</p>
            <div id="result"></div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
<script src="/js/common.js"></script>
<script>

    document.querySelector("#btnRegist").addEventListener("click", async ()=>{
        // const response = await fetch("${root}/api/v1/members", {
        const response = await fetch("http://127.0.0.1:8080${root}/api/v1/members", {
            method:"post",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                email:document.querySelector("#email").value,
                name:document.querySelector("#name").value,
                password:document.querySelector("#password").value
            })
        });
        const json = await response.json();
        console.log(json)
        document.querySelector("#result").innerHTML = JSON.stringify(json);
    })//
</script>
<!-- TODO: 09-1. 다음의 ajax 요청이 CORS 오류를 발생시키는 것을 확인하세요. -->
<script>    
const key_vworld = `${key_vworld}`;
    document.querySelector("#btnSearchSido1").addEventListener("click", async ()=>{
    try{
        const url = "https://api.vworld.kr/ned/data/admCodeList?format=json&domain=localhost&key="+key_vworld;
        console.log(url)
        const response = await fetch(url);
        const json = await response.json();
        document.querySelector("#result").innerHTML = JSON.stringify(json);
    }catch(e){
        document.querySelector("#result").innerHTML = JSON.stringify(e);
    }
    });//
</script>
<!-- TODO: 09-04. 다음의 ajax 요청이 sido 정보를 가져오는 것을 확인하세요. -->
<script>    
    document.querySelector("#btnSearchSido2").addEventListener("click", async ()=>{
        try{
            const url = "/api/v1/etc/sidos";
            const response = await fetch(url);
            const json = await response.json();
            document.querySelector("#result").innerHTML = JSON.stringify(json);
        }catch(e){
            document.querySelector("#result").innerHTML = JSON.stringify(e);
        }
    });
    </script>
</html>
