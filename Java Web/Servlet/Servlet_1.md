# JAVA Web

## 웹 기반 프로그램 동작 방식

- 클라이언트 : 사용자 컴퓨터의 웹 브라우저를 통해 화면에 해당되는 HTML문서를 서버에 요청
- 서버 : 요청 받은 HTML문서를 브라우저에 전송하여 해당 기능을 담당하는 화면 보여줌
- 사용자가 사용하는 프로그램의 기능이나 화면이 바뀌면 서버에서 모두 처리한다.

## 정적 웹 프로그래밍

- 웹 서버에 미리 보여줄 HTML페이지, CSS, 이미지, 자바 스크립트 파일을 저장해놓고, 브라우저에서 요청할 경우 그대로 전달하는 방식

- 사용자는 페이지가 변경되지 않는 한 고정된 웹 페이지를 보게 됨
- **구성 요소**
  - 웹 서버 : 각 클라이언트에게 서비스를 제공하는 컴퓨터
  - 클라이언트 : 네트워크로 서버에 접속한 후 서버로부터 서비스를 제공받는 컴퓨터
  - HTTP 프로토콜 : www서비스를 제공하는 통신 규약
  - HTML : www서비스를 제공하기 위한 표준 언어
  - 자바 스크립트 : HTML웹 페이지의 여러 가지 동적인 기능을 제공하는 스크립트
  - CSS : HTML문서에서 서체나 색상, 정렬 등 세부적인 HTML페이지의 디자인에 관련된 여러 가지 기능을 제공

## 동적 웹 프로그래밍

- 동적 프로그래밍 기술 : JSP, ASP, PHP

- JSP는 기본적으로 **스레드** 방식으로 실행
- JSP 동작 방식
  - 프로세스 방식이 아닌 스레드 방식
  - 클라이언트의 요구를 처리하는 기능은 최초 한 번만 메모리에 로드
  - 클라이언트가 동일한 기능을 요구하면 기존에 사용한 기능을 재사용

## 웹 애플리케이션

- 웹 컨테이너에서 실행되는 JSP, 서블릿, 자바 클래스들을 사용해 정적 웹 프로그래밍 방식의 단점을 보안하여 서비스를 제공하는 서버 프로그램
- 정적 웹 애플리케이션 기능인 HTML, 자바 스크립트, CSS등도 웹 애플리케이션에서 그대로 사용 가능

## 서블릿

- 서블릿은 서버 쪽에서 클라이언트의 요청에 따라 동적으로 서비스를 제공하는 자바 클래스

- 특징

  - 서버쪽에서 실행되면서 기능을 수행
  - 동적인 여러가지 기능 제공
  - 스레드 방식으로 실행
  - 자바의 특징 가짐
  - 컨테이너에서 실행
  - 컨테이너의 종류에 상관없이 실행
  - 보안 기능 적용하기 쉬움
  - 웹 브라우저에서 요청 시 기능 수행

- 서블릿 생명주기 메서드

  ```java
  init();//초기화
  doGet();//작업 수행
  doPost();//  ''
  destory();//종료
  ```

- 동일한 작업의 경우 서블릿은 메모리에 존재하는 서블릿을 재사용함으로써 훨씬 빠르고 효율적으로 동작
- doGet(), doPost() 차이
  1. Get 방식 
     - 주소창을 타고 데이터가 넘어가기 때문에 데이터를 사용자가 그대로 볼 수 있으므로 보안에 취약
     - 글자수 제한이 있어 대량의 데이터가 get방식으로 요청했을 경우 데이터가 정상적으로 넘어가지 않음
  2. Post 방식 
     - 데이터가 주소창에 보여지지 않으므로 보안에 강함

- login.html
  - action : <form>태그의 이름 지정
  - method : 데이터 전송방법 지정(get 또는 post)
  - action : 데이터를 전송할 서블릿이나 JSP 지정
  - type : 전송할 데이터의 encoding 타입을 지정

```html
<form action="/webapp/ServletEx7" method="post">
    ID : <input type = "text" name = "id" size = "10"><br>
    PW : <input type = "password" name = "pw" size = "10"><br>
    <input type = "submit" value = "로그인">
    <input type = "reset" value ="취소">
</form>
```

- login.java

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
    //전송, 반응할 데이터 인코딩(한글로 입력 및 출력)
  
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
    //getParameter()를 이용해 <input>태그의 name 속성 값으로 전송된 value를 받아옴
   
		PrintWriter out = response.getWriter();
		
		Statement stmt = null;
		ResultSet rs = null;
		UserInfo user = null;
		
		out.println("<html>");
		out.println("<body>");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from userinfo where id = '"+id+"'");
            //데이터베이스에서 입력받은 아이디로 select
			if(rs.next()) {
				if(pw.equals(rs.getString("PW"))) {
					user = new UserInfo().setId(rs.getString("ID"))
					.setPw(rs.getString("PW")).setName(rs.getString("Name"));
					
					out.println("ID : "+user.getId() + ", NAME : "+user.getName());
					out.println("<br><b>인증되었습니다.</b>");
      //아이디와 비밀번호가 일치하면 "인증되었습니다"를 출력     
               
				}
				else {
					out.println("PW X");
				}				
			}else {
				out.println("ID X");
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				//순서도 기억!
				if(rs != null) rs.close();
				if(stmt!=null) stmt.close();
	
			}catch(SQLException e) {
				e.printStackTrace();
			}
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}
```



- chekbox, radio, image 이용한 html

```html
<form action="/webapp/Join" method="post">
		<fieldset>
		  <legend>회원가입</legend>
		  <p>아이디 : <input type="text" size="12" maxlength="8" name="id"/></p>
		  <p>비밀번호 : <input type="password" size="12" maxlength="8" name="pw"/></p>
		  <p>메일 수신 여부 :
		  <input type="radio" value="y" name="receive" />예        
		  <input type="radio" value="n" name="receive" />아니요
		  </p> 
		  <p>관심 분야:
		  <input type="checkbox" value="HTML" name="chk1" />HTML
		  <input type="checkbox" value="CSS" name="chk1" />CSS
		  <input type="checkbox" value="Javascript" name="chk1" />Javascript
		  </p>
		  <p>		
		  <input type="submit" value="전송"/>		
		  <input type="reset" value="취소"/>
		  <input type="button" value="확인"  />
		  <input type="image" src="img/imgtest.jpg" alt="검색" />
		  </p>
		  <p>파일 올리기 : <input type="file" /></p>
		  <p><input type="hidden" /></p>
		</fieldset>
	</form>
```

- java

```java
request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");
PrintWriter out = response.getWriter();

String id = request.getParameter("id");
String pw = request.getParameter("pw");

out.println("<html>");
out.println("<body>");
out.println("아이디 : "+id);
out.println("비밀번호 : "+pw);
out.println("<br>");//엔터


String receive = request.getParameter("receive");
if(receive=="y")
    out.println("메일 수신 : O");
else
    out.println("메일 수신 : X");

out.println("<br>");
String[] chk1 = request.getParameterValues("chk1");
out.print("관심 분야 : ");
if(chk1!=null) {
    for(String chk : chk1) {
        out.print(chk+" ");
    }
}

out.println("</body>");
out.println("</html>");
```

- 테이블 만들기

  - 구성요소

    ```html
    <table> : 테이블을 만드는 태그
    <th> : 테이블의 헤더부분을 만드는 태그
    <tr> : 태이블의 행을 만드는 태그
    <td> : 테이블의 열을 만드는 태그
    ```

  - ex)

    ```html
    <html>
    <head>
    <meta charset="EUC-KR">
    <title>초간단 테이블</title>
    </head>
    <body>
        <table border="1">
    	<th>테이블</th>
    	<th>만들기</th>
    	<tr><!-- 첫번째 줄 시작 -->
    	    <td>첫번째 칸</td>
    	    <td>두번째 칸</td>
    	</tr><!-- 첫번째 줄 끝 -->
    	<tr><!-- 두번째 줄 시작 -->
    	    <td>첫번째 칸</td>
    	    <td>두번째 칸</td>
    	</tr><!-- 두번째 줄 끝 -->
        </table>
    </body>
    </html>
    ```

  - 테이블 디자인 변경

    |    속성     |         비고         |
    | :---------: | :------------------: |
    |   border    |   테이블의 테두리    |
    | bordercolor | 테이블의 테두리 색상 |
    |    width    |   테이블 가로 크기   |
    |   height    |   테이블 세로 크기   |
    |    align    |         정렬         |
    |   bgcolor   |        배경색        |
    |   colspan   |  가로 합병(열 합병)  |
    |   rowspan   |  세로 합병(행 합병)  |

*자바 웹을 다루는 기술 : 1~220*
