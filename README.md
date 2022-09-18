# Spring security config without extending WebSecurityConfigurerAdapter

## 1. Learning objectives
최신 스프링 버전에서는 WebSecurityConfigurerAdapter가 deprecated 됐습니다.<br/>
스프링 버전 정책 상, deprecated된 것은 향후 사라질 수 있습니다. 따라서 WebSecurityConfigurerAdapter를 확장하지 않고, spring security configuration을 적용한 코드를 이 repo에 기록했습니다.
<br/><br/>
참고로 spring은 해당 class를 deprecated된 것은 확장해서 method를 ovrride하는 대신, bean으로 등록해서 사용하는 것을 권장하고 있습니다.<br/>
사실 이 코드 역시 bean을 통해, 접근권한 별 endpoint 및 요청방식을 정의하고 있습니다. 사전에 생생된 user, admin credential을 토대로 user가 접근 할 수 있는 endpoint, 접근 할 수 없는 endpoint를 정의했습니다.
<br/><br/>
이 코드는 실행 가능한 application입니다. 만약 코드가 의도대로 동작되는지 빠르게 확인하고 싶다면, 테스트 코드를 확인하세요.

## 2. Dependencies
- JDK 17(temurin)
- Spring boot starter
  - web
  - security
  - data-jpa
- lombok(not used, 나중에 사용될 수 있으므로 추가함)
- h2 database

## 3. Reference
https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
