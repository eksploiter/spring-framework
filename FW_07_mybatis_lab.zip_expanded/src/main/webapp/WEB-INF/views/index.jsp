<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/header.jsp"%>
    <div class="container">
    <h1>자주 사용되는 링크 모음</h1>
    <h2>Spring 관련 링크</h2>
    <ul>
        <li><a href="https://spring.io">Spring</a></li>
        <li><a href="https://spring.io/projects">Spring Projects</a></li>
        <li><a href="https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties
">built-in property</a></li>
        <li><a href="https://docs.spring.io/spring-boot/appendix/auto-configuration-classes/core.html">auto-configuration</a></li>
        <li><a href="https://docs.spring.io/spring-boot/appendix/test-auto-configuration/slices.html">slice test</a></li>
        
    </ul>
    
    <h2>logback 관련 링크</h2>
    <ul>
        <li><a href="https://logback.qos.ch/manual/configuration.html#automaticConf">기본 설정 파일</a></li>
        <li><a href="https://logback.qos.ch/manual/appenders.html#RollingFileAppender">RollingFileAppender</a></li>
    </ul>
    
    <h2>MyBatis 관련 문서</h2>
        <ul>
            <li>mybatis: <a href="https://mybatis.org/mybatis-3/ko/index.html">http://mybatis.orghttps://mybatis.org/mybatis-3/ko/index.html</a></li>
            <li>mybatis spring: <a href="https://mybatis.org/spring/ko/index.html">https://mybatis.org/spring/ko/index.html</a></li>
            <li></li>
        </ul>
    
    <h2>기타 라이브러리 관련 링크</h2>
    <ul>
        <li><a href="https://mvnrepository.com">mvn repository</a></li>
        <li><a href="https://junit.org/junit5">junit</a></li>
        <li><a href="https://projectlombok.org">lombok</a></li>
    </ul> 
    </div>
    <%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
<script src="/js/common.js"></script>
<script>
    document.querySelector("#jsonproblem").addEventListener("click", async ()=>{
        const result = await getFetch("${root}/simple/jsonproblem");
        console.log(result)
    })
</script>
</html>
