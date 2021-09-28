# EL
## JSP - EL 표현식 문법과 사용 방법

- EL(Expression Language)은 자바 빈의 프로퍼티, 값을 JSP의 표현식 <%= %>이나 액션 태그 <jsp:useBean>를 사용하는것 보다 쉽고 간결하게 꺼낼수 있게 하는 기술이다.

- 또한 static 메소드를 호출할 수도 있는데 JSP에서는 주로 서블릿 보관소(JspContext, ServletRequest, HttpSession, ServletContext)에서 값을 꺼낼 때 사용한다.

## EL 표기법

> **${ }**
>
> \- JSP가 실행될 때 즉시 반영된다. (Immediate evaluation)
> \- 객체 프로퍼티 값을 **꺼낼때** 주로 사용

> **#{ }**
>
> \- 시스템에서 필요하다고 판단될 때 그 값을 사용한다. (Deferred evaluation)
> \- 사용자 입력값을 객체의 프로퍼티에 **담는** 용도로 주로 사용

**${member.no}** 또는 **${member["no"])**

위 EL 표현식의 자바 코드는 다음과 같다.

 ```java
 Member obj = (Member) pageContext.findAttribute("member");
 int value = obj.getNo();
 ```

PageContext의 findAttribute()는 서블릿 보관소를 순서대로 모두 뒤져서 객체를 찾는다.

마지막 보관소에도 없다면 null을 반환한다.

 

**JspContext → ServletRequest → HttpSession → ServletContext** 

## EL에서 보관소 검색 범위 지정

${member.no} 처럼 보관소를 지정하지 않으면 모든 보관소를 검색하지만 특정 보관소에서만 찾도록 지정할 수 있다.

다음은 EL에서 보관소를 참조할때 사용하는 이름이다.

| **이름**             | **보관소**     |
| -------------------- | -------------- |
| **pageScope**        | JspContext     |
| **requestScope**     | ServletRequest |
| **sessionScope**     | HttpSession    |
| **applicationScope** | ServletContext |

따라서 ${requestScope.member.no}와 같이 쓰면 ServletRequest에서만 해당 객체를 찾는다.

참고로 이는 다음 자바 코드와 같다.

```java
Member obj = (Member) request.getAttribute("member");
int value = obj.getNo();
```

## EL 기본 객체

다음은 JSP에서 기본으로 제공하는 EL 객체이다.

 

| **객체**             | **설명**                           | **코드**                             |
| -------------------- | ---------------------------------- | ------------------------------------ |
| **pageContext**      | JSP의 PageContext 객체             |                                      |
| **servletContext**   | ServletContext 객체                | ${pageContext.servletContext.객체명} |
| **session**          | HttpSession 객체                   | ${pageContext.session.객체명}        |
| **request**          | ServletRequest 객체                | ${pageContext.request.객체명}        |
| **response**         | ServletResponse 객체               |                                      |
| **param**            | 요청 매개변수의 값 조회            | ${param.매개변수명}                  |
| **paramValues**      | 요청 매개변수의 값 배열 조회       | ${paramValues.매개변수명}            |
| **header**           | HTTP 헤더의 값 조회                | ${header.헤더명}                     |
| **headerValues**     | HTTP 헤더의 값 배열 조회           | ${headerValues.헤더명}               |
| **cookie**           | 쿠키 값 조회                       | ${cookie.쿠키명}                     |
| **initParam**        | 컨텍스트 초기화 매개변수의 값 조회 | ${initParam.매개변수명}              |
| **pageScope**        | page 보관소의 값 조회              | ${pageScope.객체명}                  |
| **requestScope**     | request 보관소의 값 조회           | ${requestScope.객체명}               |
| **sessionScope**     | session 보관소의 값 조회           | ${sessionScope.객체명}               |
| **applicationScope** | application 보관소의 값 조회       | ${applicationScope.객체명}           |

## EL - 리터럴 표현식

| 데이터 형  |  el 코드  | 실행 결과 |
| :--------: | :-------: | :-------: |
|   문자열   | ${"test"} |   test    |
|   문자열   | ${'test'} |   test    |
|    정수    |   ${20}   |    20     |
| 부동소수점 |  ${3.14}  |   3.14    |
|    불린    |  ${true}  |   true    |
|   널 값    |  ${null}  |           |



## EL - 값 꺼내기

|  대상  |      el 코드      |                       설명                       |
| :----: | :---------------: | :----------------------------------------------: |
|  배열  |   ${myArray[1]}   |       배열에서 해당 인덱스의 값을 꺼낸다.        |
| 리스트 |   ${myList[2]}    | List객체에서 인덱스로 지정된 항목의 값을 꺼낸다. |
|   맵   |  ${myMapkeyName}  |     Map 객체에서 키에 해당하는 값을 꺼낸다.      |
| 자바빈 | ${myObj.propName} |      자바 객체에서 프로퍼티의 값을 꺼낸다.       |
|        |                   |                                                  |

```javascript
1. 배열

[자바 코드]
pageContext.setAttribute("scores",new int[]{90,80,70,100})
[실행 결과]
\${scores[2]} = ${scores[2]}
-> {scores[2]} = 70

2. 리스트

[자바 코드]
List<String> nameList = new LinkedList<String>();
nameList.add("홍길동");
nameList.add("임꺽정");
nameList.add("일지매");
pageContext.setAttribute("nameList",nameList);
[실행 결과]
\${nameList[1]} = ${nameList[1]}
-> {nameList[1]} = 임꺽정

3. 맵

[자바 코드]
Map<String, String> map = new HashMap<String, String>();
map.put("s01","홍길동");
map.put("s02","임꺽정");
map.put("s03","홍길동");
pageContext.setAttribute("map", map);
[실행 결과]
\${map.s02} = ${map.s02}
-> {map.s02} = 임꺽정


4.자바빈

[자바 코드]
pageContext.setAttribute("userinfo", new UserInfo().setId("abc")
				.setPw("123").setName("홍길동"));
[실행 결과]
\${userinfo.name} = ${userinfo.name}
-> {userinfo.name} = 홍길동

```

## EL 표현식의 연산자

EL에서 지원하는 주요 연산자는 산술 연산자, 논리 연산자, 관계 연산자, empty 연산자, 조건 연산자가 있다.

### EL 산술 연산자

| **표현식**       | **실행 결과** |
| ---------------- | ------------- |
| **${10+20}**     | 30            |
| **${10-20}**     | -10           |
| **${10\*20}**    | 200           |
| **${10/20}**     | 0.5           |
| **${10 div 20}** | 0.5           |
| **${10 % 20}**   | 10            |
| **${10 mod 20}** | 10            |

### EL 논리 연산자

| **표현식**             | **실행 결과** |
| ---------------------- | ------------- |
| **${true && false}**   | false         |
| **${true and false]**  | false         |
| **${false \|\| true}** | true          |
| **${false or true]**   | true          |
| **${not true}**        | false         |
| **${!true}**           | false         |

 

### EL 관계 연산자

| **표현식**      | **연산자** |
| --------------- | ---------- |
| **${10 == 11}** | false      |
| **${10 eq 11}** | false      |
| **${10 != 11}** | true       |
| **${10 ne 11}** | true       |
| **${10 < 11}**  | true       |
| **${10 lt 11}** | true       |
| **${10 > 11}**  | false      |
| **${10 gt 11}** | false      |
| **${10 <= 11}** | true       |
| **${10 le 11}** | true       |
| **${10 >= 11}** | false      |
| **${10 ge 11}** | false      |

 ```jsp
 <%
     pageContext.setAttribute("title", "EL 연산자");
 %>
 ${empty title}, ${empty title2}
 ```

### 조건 연산자

EL 조건 연산자는 자바 3항 연산자와 문법이 동일하다.

|      | ${10 > 20 ? "크다" : "작다"} |
| ---- | ---------------------------- |
|      |                              |

### empty 연산자

> \- 값이 null이면 true
> \- 문자열, 배열, Map, Collection 객체의 크기가 0이면 true
> \- 그 외에는 false



```jsp
<%
    pageContext.setAttribute("title", "EL 연산자");
%>
${empty title}, ${empty title2}
```

실행 결과 : false, true



## EL 예약 키워드

> **and, or, not, eq, ne, lt, gt, le, ge, true, false, null, instanceof, empty, div, mod**

 

서블릿 보관소에 객체를 저장할 때 Key가 EL 예약 키워드이면 EL 표현식에서 에러가 발생한다.

```jsp
<%
    pageContext.setAttribute("ne", "EL 예약 키워드를 Key로 사용");
%>
${ne}
```



# JSTL

JSTL을 사용할 때에는

`` <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>``

코드를 위에 추가해주어야 한다.

1. c:out 태그

- JSP expression tag와 비슷하지만 expression에서만 사용할 수 있다.
- <% = ... %>과 유사한 표현식

```jsp

<h2>c:out 태그</h2>
1) <c:out value = "안녕하세요!"/></br>
2) <c:out value = "${null}">반갑습니다.</c:out><br>
3) <c:out value = "안녕하세요!">반갑습니다.</c:out><br>
4) <c:out value = "${null}"/><br>
```

2. c:set 태그

- 'scope'에서 평가된 표현식의 결과를 설정하는 데 사용
- 표현식을 평가하고 결과를 사용하여 java.util.Map 또는 JavaBean 값을 설정하므로 유용
- jsp:setProperty action 태그와 유사

```jsp
<h2>c:set 태그</h2>
<h3>값 설정 방식</h3>
<c:set var = "username1" value="홍길동"/>
<c:set var = "username2">임꺽정</c:set>
1) ${username1}<br>
2) ${username2}<br>
<h3>기본보관소 - page</h3>
3) ${pageScope.username1}<br>
4) ${requestScope.username1}<br>
<h3>보관소 지정 - scope 속성</h3>
<c:set var="username3" scope="request">일지매</c:set>
5) ${pageScope.username3}<br>
6) ${requestScope.username3}<br>
<h3>기존의 값 덮어씀</h3>
<% pageContext.setAttribute("username4","똘이장군"); %>
7) 기존 값 = $ {username4}<br>
<c:set var ="username4" value = "주먹대장"/>
8) 덮어쓴 값 = ${username4}<br>
<h3>객체의 프로퍼티 값 변경</h3>
<%!
	public class MyMember{
	private int no;
	private String name;
	
	public int getNo(){
		return no;
	}
	public void setNo(int no){
		this.no = no;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
}

%>
<%
	MyMember member = new MyMember();
	member.setNo(100);
	member.setName("홍길동");
	pageContext.setAttribute("member",member);
	
%>
9) ${member.name}<br>
<c:set target = "${member}" property="name" value ="임꺽정"/>
10) ${member.name}<br>
```

결과

## c:set 태그

### 값 설정 방식

1) 홍길동
2) 임꺽정

### 기본보관소 - page

3) 홍길동
4) 

### 보관소 지정 - scope 속성

5) 
6) 일지매

### 기존의 값 덮어씀

7) 기존 값 = $ {username4}
8) 덮어쓴 값 = 주먹대장

### 객체의 프로퍼티 값 변경

9) 홍길동
10) 임꺽정





