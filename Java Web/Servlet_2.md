# Servlet 2

## 학습 주제

- 페이지 이동, 페이지 병합, 정보 저장

- forward

- include

- scope(page, request, session, application)

- ex) RequestDispacher rd = request.getRequestDispatcher("페이지 경로");

  ​       HttpSession session = request.getSession();

- range = page < request < session < application



## forward

하나의 서블릿에서 다른 서블릿이나 JSP와 연동하는 방법

### 용도

- 페이지 이동
- 서블릿에서 다른 서블릿이나 JSP로 요청을 전달하는 역할
- 요청을 전달할 때 추가 데이터를 포함시켜서 전달할 수도 있음
- JSP에는 이러한 객체 정의할 필요 X -> 내장 객체
- 실행하면 login페이지가 뜬다.

### 포워드 방법

- redirect방법
  - 형식 : sendRedirect("포워드할 서블릿 또는 JSP");
  - 웹 브라우저에 재요청하는 방식
- Refresh 방법
  - 형식 : response.addHeader("Refresh", 경과시간(초); url = 요청할 서블릿 또는 JSP")
  - 웹 브라우저에 재요청하는 방식

- location 방법
  - 형식 : location .href = '요청할 서블릿 또는 JSP';
  - 자바스크립트에서 재요청하는 방식

- dispatch 방법

  - 일반적으로 포워딩 기능을 지칭
  - 서블릿이 직접 요청하는 방법
  - 형식

  ```java
  RequestDispatcher rd = request.getRequestDispatcher("포워드할 서블릿 또는 JSP");
  rd.forward(request, response);
  ```

## 바인딩

- 서블릿에서 다른 서블릿 또는 JSP로 대량의 데이터를 공유하거나 전달하고 싶을 때 바인딩(binding) 기능을 사용
- 바인딩의 사전적인 의미 : "두개를 하나로 묶는다."
- 주로 HttpServletRequest, HttpSession, ServletContext객체에서 사용
- 저장된 자원은 프로그램 실행 시 서블릿이나 JSP에서 공유하여 사용

##### 서블릿 객체에서 사용되는 바인딩 관련 메소드

|              관련 메서드              |                         기능                         |
| :-----------------------------------: | :--------------------------------------------------: |
| setAttribute(String name, Object obj) |         자원(데이터)을 각 객체에 바인딩한다.         |
|       getAttribute(String name)       | 각 객체에 바인딩된 자원(데이터)을 name으로 가져온다. |
|     removeAttribute(String name)      | 각 객체에 바인딩된 자원(데이터)을 name으로 제거한다. |

## include

- 페이지 병합
- 따로 작업을 할 수 있음
- 형식 : ""

Example

1. 로그인 Servlet

```java
RequestDispatcher rd1 = request.getRequestDispatcher("HeaderServlet");
//헤더 서블릿으로 가는 rd1 디스패처 만듬

UserInfo userinfo = new UserInfo().setId(rs.getString("id"))
   .setName(rs.getString("name")).setPw(rs.getString("pw"));
//UserInfo 객체에 아이디, 이름, 패스워드 저장
HttpSession session = request.getSession();
session.setAttribute("userinfo", userinfo);
//session에 사용자 데이터 바인딩
rd1.include(request, response);
//헤더에 inclue

```

2. 헤더 Servlet

```java
HttpSession session = request.getSession();
UserInfo userinfo = (UserInfo)session.getAttribute("userinfo");
//세션 정보 받아옴, userinfo에 받아온 사용자 정보 저장

if(userinfo != null) {
    out.println(userinfo.getName()+"님 로그인 중입니다.");
}
else {
    out.println("session정보 없음<br>");
}
```

3. 로그아웃 Servlet

```java
HttpSession session = request.getSession();
session.invalidate();//세션 정보 소멸

response.sendRedirect("UserInfoLoginServlet");
//다시 로그인 화면 부르기
```





