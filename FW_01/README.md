# FW_01 - SLF4J & Logback Logging 프로젝트

Java 프로젝트에서 **SLF4J**와 **logback**을 이용해 로깅 기능을 연습한 예제입니다.  
Maven과 함께 구성되었으며, 파일 기반 로그 저장 및 **RollingFileAppender** 설정까지 적용되어 있습니다.

---

## 🛠 사용 기술

- Java 24 (OpenJDK 24.0.1)
- Maven
- SLF4J
- Logback Classic 1.5.18

---

## 📦 의존성 (`pom.xml`)

[Maven Repository - logback-classic 1.5.18](https://mvnrepository.com/artifact/ch.qos.logback/logback-classic/1.5.18)

```xml
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
  <version>1.5.18</version>
  <scope>compile</scope>
</dependency>
```

## ⚙️ 로그 설정 (src/main/resources/logback.xml)
[logback 공식 문서: RollingFileAppender](https://logback.qos.ch/manual/appenders.html#RollingFileAppender)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration>

<configuration>
  <import class="ch.qos.logback.classic.encoder.PatternLayoutEncoder"/>
  <import class="ch.qos.logback.core.rolling.RollingFileAppender"/>
  <import class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy"/>

  <appender name="FILE" class="RollingFileAppender">
    <file>logFile.log</file>
    <rollingPolicy class="TimeBasedRollingPolicy">
      <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>
      <maxHistory>30</maxHistory>
      <totalSizeCap>3GB</totalSizeCap>
    </rollingPolicy>
    <encoder class="PatternLayoutEncoder">
      <pattern>%-4relative [%thread] %-5level %logger{35} -%kvp- %msg%n</pattern>
    </encoder>
  </appender>

  <root level="DEBUG">
    <appender-ref ref="FILE"/>
  </root>
</configuration>
```
하루 단위로 로그가 나눠지며, 최대 30일 또는 3GB까지 보관됩니다.

## 🧪 예제 코드

CalculatorTest.java 또는 기타 테스트 클래스에서 다음과 같은 로그 레벨을 테스트할 수 있습니다:

```java
log.trace("trace log");
log.debug("debug log");
log.info("info log");
log.warn("warn log");
log.error("error log");
```
