# Spring Boot 강의

## 기본 세팅

1. New -> Spring Start Project
2. Name, Group, Arifact, Description, Package 수정
3. Spring Boot DevTools, Lombook, Spring Data JPA, MySQL Driver, Spring Security, Spring Web 추가
4. pom.xml에 추가

```xml
<!-- 추가 라이브러리 시작 -->
		
		<!-- 시큐리티 태그 라이브러리 -->
		<dependency>
		  <groupId>org.springframework.security</groupId>
		  <artifactId>spring-security-taglibs</artifactId>
		</dependency>
		
		<!-- JSP 템플릿 엔진 -->
		<dependency>
		  <groupId>org.apache.tomcat.embed</groupId>
		  <artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
		
		<!-- JSTL -->
		<dependency>
		  <groupId>javax.servlet</groupId>
		  <artifactId>jstl</artifactId>
		</dependency>
	
	
	<!-- 추가 라이브러리 종료 -->
```

5. my sql 설정

   1. 유저이름, 주소 설정

   ```mysql
   -- 유저이름@아이피주소
   create user 'cos'@'%' identified by 'cos1234';
   -- ON DB이름.테이블명
   -- TO 유저이름@아이피주소
   GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
   CREATE DATABASE blog;
   use blog;
   ```

   2. application.properties -> application.yml

   - 중복되는 코드가 적어짐
   - 가독성이 좋음

   ```json
   spring:
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul
       username: cos
       password: cos1234
   ```

6. Postman 설치

### http 1.1 요청방식

- Get : 데이터를 줘! - select

  ex) http://localhost:8080/http/get?id=1&username=홍길동&password=1234

  -> ? 뒤에 데이터를 보냄(param으로 데이터 보냄)

  -> @RequestParam ~~

  -> 주소에 데이터를 담아서 보냄, key = value 형식

- Post : 데이터를 추가해줘! - insert

  -> body에 담아 데이터 보냄

  -> key=value아닌 데이터 -> @RequestBody 로 받음

  -> json 방식으로 요청

  -> form  태그 : get요청,post요청밖에 못함

  

  => 자바스크립트로 ajax 요청 + 데이터는 json으로 통일!

  

  form:form 태그 : get, post, delete, put요청이 모두 가능

  ```json
  {
      "id" : 1,
      "username" : "honggildong"
      "password" : 1234,
      "email" : "honggildong.naver.com"
  
  ```

- Put : 데이터를 수정해줘! - update

  -> post와 마찬가지로 body로 데이터 보냄

- Delete : 데이터를 삭제해줘! - delete

### Maven

- Maven은 자바용 프로젝트 관리도구로 Apache Ant의 대안으로 만들어졌다.
- Maven은 Ant와 마찬가지로 프로젝트의 전체적인 라이프 사이클을 관리하는 도구 이며, 많은 편리함과 이점이 있어 널리 사용되고 있다.
- Maven은 필요한 라이브러리를 특정 문서(pom.xml)에 정의해 놓으면 내가 사용할 라이브러리 뿐만 아니라 해당 라이브러리가 작동하는데에 필요한 다른 라이브러리들까지 관리하여 네트워크를 통해서 자동으로 다운받아 준다.
- Maven은 중앙 저장소를 통한 자동 의존성 관리를 중앙 저장소(아파치재단에서 운영 관리)는 라이브러리를 공유하는 파일 서버라고 볼 수 있고, 메이븐은 자기 회사만의 중앙 저장소를 구축할수도 있다.
- 간단한 설정을 통한 배포 관리가 가능 하다.
- pom.xml에 작성하면 .m2 -> repository 에 자동으로 다운되어 관리

### yaml 파일

- key와 value 사이 스페이스 한 칸
- 들여쓰기 스페이스 두 칸
- xml, json 보다 가독성 좋음

```yaml
server:
  port: 8000
  servlet:
    context-path: /blog
    encoding:
      charset: UTF-8
      enabled: true
      force: true
    
spring:
# jsp 파일은 정적 파일이 아니므로, static에서 사용할 수 없어서 
# main/webapp/WEB-INF/views 폴더를 만들어 사용
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul
    username: cos
    password: cos1234
    
  jpa:
    open-in-view: true
    hibernate:
    #	 최초에만 create 나중에는 update로 바꿔야함
      ddl-auto: create
      naming:
      # 기본 변수이름을 그대로 이용한다. 
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      # JPA의 기본 넘버링 전략을 따라하지 않겠다는 뜻
      use-new-id-generator-mappings: false
    # SQL을 Console창에 보여줌
    show-sql: true
    # SQL을 정렬해줌
    properties:
      hibernate.format_sql: true

  jackson:
    serialization:
      fail-on-empty-beans: false

```

### JSP

- 테이블 만들기

```java
package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


@Entity// User클래스가 MY SQL에 테이블이 생성된다.
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; //아이디
	
	@Column(nullable = false, length = 100)//123456 -> 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; // Enum을 쓰는게 좋다. // admin, user, manager => 도메인 설정해줌
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
}

```

- 생성된 테이블

```mysql
create table User (
    id integer not null auto_increment,
    createDate datetime(6),
    email varchar(50) not null,
    password varchar(100) not null,
    role varchar(255) default 'user',
    username varchar(30) not null,
    primary key (id)
) engine=InnoDB
```

- 연관관계 주인 = FK를 가진 오브젝트  
- @ManyToOne , @OneToMany, @ManyToMany
- @ManyToOne, @OneToOne은 보통 FetchType.EAGER (즉시 로딩 : 연관된 엔티티를 즉시 조회한다. SQL조인을 사용해서 한번에 조회한다.)
- @OneToMany, @ManyToMany는 보통 FetchType.LAZY(지연 로딩 : 연관된 엔티티를 프록시로 조회한다. 프록시를 실제 사용할 때 초기화하면서 데이터베이스를 조회한다.)

팁

1. ctrl+shift+w : 전체 열린 창 지우기

2. ctrl + space : 자동완성

2. sysout + ctrl + space : System.out.println() 자동 완성

2. ctrl+shift+f : html 정렬

   

### JSON

- 요청받을 때 : JSON -> JAVA으로 바꿈
- 응답할 때 : JAVA ->JSON 으로 바꿈
- 공용어

### My SQL

- application.yml 에 jpa : ddi-auto : create로 하고 실행하면 테이블에 들어있는 데이터들을 초기화할 수 있다.



### Paging

```java
@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort ="id",direction = Sort.Direction.DESC)Pageable pageable){
		Page<User> users =  userRepository.findAll(pageable);
		return users;
	}
```

-  http://localhost:8000/blog/dummy/user?page=0   -> 첫번째 페이지

- page = 1, page = 2 ...  => 두번째, 세번째 페이지

  

### Ajax

- 회원가입 시 Ajax를 사용하는 2가지 이유

  1. 요청에 대한 응답을 html이 아닌 Data(Json)를 받기 위해!

     -> 앱과 웹 둘다 사용 가능한 서버를 만들기 위해서

  2. 비동기 통신을 하기 위해서 -> 순서에 상관없이 일 처리

     

### Spring Security

- local에 접근할려고 하면 먼제 http://localhost:8000/login 페이지를 보여주고 로그인을 하라고 함.
- id : user / pw : console 창에 있는 비밀번호 입력
- http://localhost:8000으로 접근
- 세션 자동으로 생김

### 공격

1. XSS(자바 스크립트 공격)

   - 태그 막기
   - naver lucy 사용

2. CSRF 공격

   - post 방식으로 변경

   - 같은 도메인 상의 요청이 들어오지 않는다면 차단

   - CSRF 토큰 사용 -> 사용자의 세션에 저장하여 사용자의 모든 요청에 대하여 서버단에서 검증  

   - ajax는 자바스크립트로 요청하기 때문에 csrf 토큰이 없다 ->

     테스트 시에는 csrf().disable() 코드를 통해 csfr 토큰을 비활성화해주어야 한다.

     ex) 회원가입 시 ajax로 요청
