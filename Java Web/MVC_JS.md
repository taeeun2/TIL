# MVC
![image-20210924144630185](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210924144630185.png)

- 데이터에 관련된 코드 부분 : 모델이 담당
- 정적 페이지 관리 : 컨트롤러, 뷰가 담당
- 컨트롤러 : scop이용(Servlet)
- 뷰 : JSP
- 모델 : 자바 클래스

![image-20210924150933382](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210924150933382.png)

- 프런트 컨트롤러 : 하나의 Servlet

# DAO, DTO, VO 개념 차이

1. DAO(Data Access Object)

- 데이터베이스의 data에 접근하기 위한 객체
- 데이터베이스 접근을 하기 위한 로직과 비즈니스 로직을 분리하기 위해 사용
- 사용자는 자신이 필요한 interface를 DAO에게 던지고 DAO는 이 interface를 구현한 객체를 사용자에게 편리하게 사용할 수 있도록 반환한다.
- DAO는 데이터베이스와 연결할 Connection까지 설정되어 있는 경구가 많다.

- DAO 예시

  : 사용자 정보 리스트 이름을 오름차순으로 출력

```JAVA
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBAction;
import dto.UserInfo;

public class UserInfoDao {

    public List<UserInfo> selectList() throws Exception{
        ArrayList<UserInfo> userInfos = null;
        Connection conn = 	DBAction.getInstance().getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USERINFO ORDER BY NAME ASC");
            userInfos = new ArrayList<UserInfo>();
            while(rs.next()) {
                UserInfo userInfo = 
                    new UserInfo().setId(rs.getString("id"))
                    .setName(rs.getString("name")).setPw("pw");
                userInfos.add(userInfo);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null) rs.close();
                if(stmt !=null)stmt.close();
                if(conn !=null)conn.close();
            }catch(Exception e) {}
        }
        return userInfos;
    }
}

```

- DAO를 이용하여 리스트 출력

```JAVA
UserInfoDao userinfoDao = new UserInfoDao();
List<UserInfo> list = null;
try{
    list = userinfoDao.selectList();
}catch(Exception e) {e.printStackTrace();}

out.println("<html><body>");
out.println("<table border = '1'>");
Iterator<UserInfo> data = list.iterator();
while(data.hasNext()) {
    UserInfo user = data.next();
    out.println("</tr>");
    out.println("<td><b><a href='/webapp/UserInfoUpdateServlet?id="+user.getId()+"'>"+user.getId()+"</a></b></td>");
    out.println("<td><b>"+user.getPw()+"</b></td>");
    out.println("<td><b>"+user.getName()+"</b></td>");
    out.println("</tr>");
}

out.println("</table>");
out.println("<a href = '/webapp/UserInfoAddServlet'>회원가입</a>");
out.println("</body></html>");
out.close();
```



2. DTO(Data Transfer Object)

- VO라고도 표현하며 계층 간 데이터 교환을 위한 자바 빈즈이다.
-  데이터베이스 레코드의 데이터를 매핑하기 위한 데이터 객체를 말한다.
- DTO는 보통 로직을 가지고 있지 않고 data와 그 data에 접근을 위한 getter, setter만 가지고 있다.
- 정리하면 DTO는 Database에 Data를 얻어 Service나 Controller등으로 보낼 때 사용하는 객체를 말한다.

- DTO 예시

```JAVA
package dto;

public class UserInfo {
	private String id;
	private String pw;
	private String name;
	
	
	public String getId() {
		return id;
	}
	public UserInfo setId(String id) {
		this.id = id;
		return this;
	}
	public String getPw() {
		return pw;
	}
	public UserInfo setPw(String pw) {
		this.pw = pw;
		return this;
	}
	public String getName() {
		return name;
	}
	public UserInfo setName(String name) {
		this.name = name;
		return this;
	}
}

```

3. VO(Value Object)

- VO는 DTO와 혼용해서 쓰이긴 하지만 미묘한 차이가 있다.
- VO는 값 오브젝트로써 값을 위해 쓰인다.
- 자바는 값 타입을 표현하기 위해 불변 클래스를 만들어 사용하는데, 불변이라는 것은 read only특징을 가진다.
- DTO와 VO의 공통점은 넣어진 데이터를 getter를 통해 사용하므로 주 목적은 같으나 DAO는 가변적인 성격을 가진 클래스이며(setter 활용) 그에 비해 VO는 불변의 성격을 가졌기에 차이점이 있다.



# JavaScript

- 웹 브라우저에서 많이 사용하는 프로그래밍 언어

- 서버의 간섭이 없이 클라이언트에 반응하는 도구.
- Java Script는 Live Script로서 다이나믹한 홈페이지를 만들기 위한 도구이다.
- 웹이 발전할수록 더욱 중요한 가치를 지님.

### 특징

1. Script언어이다.

2. 간단한 Application이다.

3. 사용자 event처리가능

4. Object-Based Programming(객체기반) 언어이다.

   (자바는 OOP객체 지향 언어)

### 장점

1. 신속한 개발
2. 배우기 쉽다
3. 플랫폼에 독립적이다.
4. 시스템 부하가 적다.

### 단점

1. 내장 Method의 한계 : 늘릴 수가 없다. 상속이 안된다. 즉, 기능추가를 더 할 수 없다.
2. Code의 보안성 : 이를 위해서 .js로 밖에 꺼내놓는다.
3. debugging 및 개발 툴의 부재

### 함수

함수에는 항상 괄호가 있다.

(ex : F(x) = ___ 처럼 x에 값을 넣으면 f가 기능을 하여 어떤 값을 출력하게 한다.)

1. built in function : 메뉴얼에 이미 만들어져 있는 함수
2. user-defined function : 사용자가 필요에 의해 만드는 함수

- JS Ex 1)

```javascript
<head>
<title> Insert title here</title>
	<script>
		//변수 선언
		var str = 'This is "string"';
		var str2 = "This is 'string'";
		var a = true;
		alert(str);
		alert(str2);
		alert(a);
		alert("This is \"string\"");
		alert('This is \'string\'');
	</script>
</head>
```

- JS Ex 2)

```javascript
<head>
<title>Insert title here</title>
	<script>
		alert('동해물과 백두산이\n 마르고 닳도록\n');
		document.write('<b>body에 출력됨</b>');
	</script>
</head>
```



