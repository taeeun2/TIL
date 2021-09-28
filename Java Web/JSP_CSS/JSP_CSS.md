# JSP

- 인터넷을 사용하는 사용자에게 정보를 제공하기 위해서는 html 태그를 사용하여 웹브라우저 내에 정보를 표시하는데,  JSP에서는 이러한 html 태그를 사용할 수 있어 웹 애플리케이션의 프레젠테이션 역할을 합니다.
- JSP는 JSP에서만 사용 가능한 다양한 태그(예를 들면 스크립트릿과 같은 것)를 이용하여 쉽게 웹 프로그래밍을 할 수 있다.
- 컨텍스트 패스는 여러 개의 웹 애플리케이션이 WAS에서 동작할 경우 이를 구분하기 위해서 사용합니다.
- 이클립스가 자동으로 만들어주는 컨텍스트 패스는 프로젝트 이름과 동일합니다.
- 프로젝트 생성시 입력하는 프로젝트 이름은 개발자 입장에서 개발을 위해서 생성된 파일들을 관리하기 위한 폴더이고, 컨텍스트 패스는 사용자가 해당 웹 애플리케이션을 사용하기 위해서 접근하는 경로가 됩니다.
- 경우에 따라서는 개발자는 컨텍스트 패스 이름을 웹 프로젝트 이름과는 다른 원하는 이름으로 바꿀 수 있습니다.
- JSP는 Java Server Page의 약어로서 HTML태그에 자바로 프로그래밍하여 브라우저에 보여주는 특별한 페이지입니다.
- 아래와 같이 HTML태그 사이에 <% %>를 추가하려면 이는 JSP 파일로 작성해야 합니다.
- 반면 JSP파일은 HTML파일과는 다르게 동작합니다.
- HTML파일은  확장자가 html이고 이는 웹 브라우저 내의 번역기가 돌리는 반면 JSP 파일은 톰캣 서버가 번역하여 그 결과를 HTML 태그로 변환한 후 웹브라우저에 내려 보냅니다.
- 웹 컨테이너는 <% %>부분을 JSP로 인식하여 해석한 후 HTML 형태로 변환
- PrintWriter 객체 생성해 줄 필요 없이 바로 out.print사용 가능(내장 객체)

## JSP의 처리 과정(서블릿과 JSP)

[ 처리과정 ]

- 개발자가 JSP를 작성하면 -> MyJSP.jsp
- 웹 컨테이너가 서블릿으로 변환 -> MyJSP_jsp.java

![image-20210923132532042](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210923132532042.png)

[ 웹 컨테이너가 JSP를 변환하여 만들어낸 서블릿 코드 순서 ]

HttpJspBase의 하위 클래스 생성->요청에 대해 반응하는 _jspService를 만든다

->JSP를 작성할 때 쓰는 out과 request와 같은 내장 객체를 선언한다.

-> 내장 객체를 생성한다. -> jsp에 있는 HTML 코드, 스크립트릿을 출력 스트림 out의 출력 메소드를 출력한다.

![image-20210923132656910](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210923132656910.png)



## JSP 기본 태그

- HTML이나 자바 스크립트에 의해 작성된 페이지들은 그 페이지를 사용하는 사용자와 상관없이 항상 동일한 내용만을 제공해주는 정적 페이지
- 하지만 웹 프로그램들은 사용자에 따라 서로 다른 내용이 제공되어야 하는 데 이러한 동적 페이지들을 구현하기 위해서는 다음과 같은 JSP 태그를 사용해야 한다.

|    종류    |                       사용용도                       |     형식     |
| :--------: | :--------------------------------------------------: | :----------: |
| 스크립트릿 |                  자바 코드를 기술함                  |    <% %>     |
|    선언    |                변수와 메소드를 선언함                |    <%! %>    |
|   표현식   | 계산식이나 함수를 호출한 결과를 문자열 형태로 출력함 |   <%=  %>    |
|    주석    |               JSP 페이지에 설명을 넣음               | <%--    --%> |
|   지시자   |              JSP페이지의 속성을 지정함               |    <%@ %>    |

- [스트립트릿] : 자바 코드 구현

- [선언] : 변수 선언 및 메소드를 정의

- [표현식] : 문자열 출력

  ```JAVAS
  <%= su1 + "+" + su2 +  "=" + sum %>
  ```

- 스크립트릿, 선언, 표현식이 제일 중요
- 주석을 사용할 때는 JSP주석을 사용해야 한다.(HTML 주석(<!-- --!>) 는 사용할 수 없음)

- [지시자] 예시

```javascript
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
include, forward시에도 사용
<%@ page import = "java.util.*" %>
```

```javascript
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>안녕하세요, JSP 페이지입니다.</p>
	[스크립트릿(scriptlet)] : 자바코드 구현합니다.
	<br>
	<%
		int su1 = 20;
		int su2 = 10;
		int sum = su1+su2;
		out.println(su1+"+"+su2+"="+sum);
	%><br>
	[선언문(declaration)] : 변수 선언 및 메소드를 정의합니다.
	<br>
	<%!
		String str = "Hello JSP";
		int su1 = 5, su2 = 7;
		
		public int method(){
			return su1+su2;
		}
	%>
	[표현식(expression)] : 문자열 출력합니다.<% out.println("msg"); %>
	<br>
	<%--
		<%= 변수%>
		<%= 수식%>
		<%= 메소드 호출%>
	 --%>
	<%--
		주의)
		<%= 변수;%> 개발자가 작성한 표현식을 (;사용하지 않음)
		웹 컨테이너가 out.print()으로 변환시킴
		out.print(변수;); <- 오류
	  --%>
	  <b><%= method() %></b>
</body>
</html>
```

## 내장 객체의 영역

- 내장 객체의 영역은 객체의 유효기간이라고도 불립니다.
- 즉 해당 객체가 얼마동안이나 살아있는가를 지정해주는 것을 영역이라고 합니다.
- 영역은 총 4개로 page, request, session, application이 있다.(scope)
- page : 하나의 jsp 페이지를 처리할 때 사용되는 영역
- request : 하나의 요청을 처리할 때 사용되는 영역
- session : 하나의 브라우저와 관련된 영역
- application : 하나의 웹 애플리케이션과 관련된 영역

## 액션 태그

- 액션 태그는 XML문법을 따릅ㄴ디ㅏ. 즉 시작 태그와 함께 반드시 종료 태그를 포함해야 합니다.
- 다음은 액션 태그의 기본 형식입니다.

```html
<jsp: ... 속성 = "값"> 내용 </jsp:>
```

- 액션 태그에 속성값만 지정하고 내용이 없을 경우에는 xml 규칙에 의해 끝나는 태그를 따로 하지 않고 시작 태그의 마지막 부분을 "/>"로 마무리합니다.
- 내용이 없는 액션 태그의 형식입니다.

```html
<jsp : ... 속성="값"/>
```

1. forward 액션태그

```html
<jsp:forward> 액션 태그
<jsp:forward> 태그는 현재 JSP 페이지에서 URL로 지정한 특정 페이지로 넘어갈 때 사용하는 태그입니다.
형식은 다음과 같습니다.
    
[표현]
<jsp:forward page = "url"/>
```

2. include 액션 태그

```html
<jsp :include>액션 태그</jsp>
- 내용을 기술하는 페이지에서 페이지 상단을 작성하면서 <jsp:include>태그를 사용하여 header.jsp페이지를 포함시키고 페이지 하단을 작성하는 <jsp:include>태그를 사용하여 footer.jsp페이지를 포함시켜서 웹 사이트를 구축
    
- 하나의 결과 화면을 모듈별로 개별적인 여러 개의 페이지로 나눠서 작성하는 것을 모듈화라고 한다.
ex)
    페이지 상단
    <jsp:include page="header.jsp"/>
    
    페이지 하단
    <jsp:include page="footer.jsp"/>

```

3. useBean 액션 태그
4. setProperty 액션 태그
5. getProperty 액션 태그

- include 차이

# CSS

종속형 시트 또는 캐스케이팅 스타일 시트(Cascading Style Sheet, CSS)는 마크업 언어가 실제 표시되는 방법을 기술

- 비유
  - CSS는 HTML을 아름답게 꾸며주는 디자이너의 언어
  - 화가가 붓을 만드는 법을 알 필요는 없지만, 붓을 만드는 사람보다 붓 칠은 훨씬 잘해야 합니다.
  - 붓 칠을 잘하는 법을 모른다면 표현하고자 하는 것을 제대로 표현하는 것이 어려울 것이니까요.
  - CSS는 디지털 컨텐츠를 생산하는 사람들에게는 붓 질과 같은 것이 아닐까 싶습니다.

- 사용방법
  1. 외부파일 사용
  2. 내부파일 사용
  3. 태그로 사용

- 표현

```CSS
1. 외부파일 사용
<link href = "파일경로" rel = "stylesheet" type = "text/css">
2. 내부파일 사용
<style type = "text/css">
스타일정의
<style>
3. 태그로 사용
<p style="color : blue;"><p>
```

Example

1. 구구단.css

```javascript
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	table{
	width : 100%;
	border-collapse : collapse;
	text-align : center;
	border : 5px solid #444444;
	}
	th, td{
		padding: 10px;
		border-bottom : 2px solid #444444;
	}
	.title{
		 align : center;
		 background-color : slateblue;
		 color : white;
		 border-bottom : double black;
		 
	}
	th{
		background-color : ivory;
	}
	th:first-child, td:first-child {
		border : none;
	}
	
</style>
</head>
<body>
	<%!
		int sum;
		Calendar cal;
	%>
	<%
		cal = Calendar.getInstance();
		String[] yoil = {"일","월","화","수","목","금","토"};
	%>
	
	<table border='1'>
	<tr><td colspan = '9' class = "title" >구구단</td></tr>
	<tr>
	<%	
	
	for(int i=2;i<10;i++){
		out.println("<th>"+i+"단"+"</th>");
	}
	
	for(int i=1;i<10;i++) {
		if(i%2==0){
		%><tr><% 
		}else{
	%>
			<tr style = "background-color : mistyrose">
			<%}
			for(int j=2;j<10;j++) {
				out.println("<td>" + j + " * "+i+"="+(j*i)+"</td>");
			}			
	}%>
	</table>
	<%
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int day = cal.get(Calendar.DAY_OF_WEEK);
	 %>
	 <%= year + "년 " + month + "월 " + date + "일 "+hour+"시 "+minute+"분 "+second
	 	+"초 입니다. (오늘은 \"" + yoil[day-1]+"\"요일 입니다.)"%>	 
</body>
</html>
```

2. header, nav, section, footer 영역.css

```javascript
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>HTML Layouts</title>
	
	<style>
	
			header, footer{
			width:100%;
			padding:15px;
			text-align : center;
			font-weight : bolder;
			}
			
			header{
			background:lightgray;
			}
	
			footer{
			background:GOLD;
			clear:both;
			}
			
			nav{
			background: turquoise;
			width : 200px;
			height : 300px;
			color : white;
			font-weight : bolder;
			text-align : center;
			float:left;
			
<%--		line-height : 150px;--%>
			
			}
			
			
			section{
			width : 200px;
			padding : 10px;
			float : left;
			text-align : left;
			}
			
	</style>
</head>

<header>
HEADER 영역
</header>
<nav>
NAV 영역
</nav>
<section>
SECTION 영역
</section>
<body>

</body>

<footer>
FOOTER 영역
</footer>
</html>
```



