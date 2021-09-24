# JSP - Example

1. 아이디 중복 체크

   - memberForm.html

   1.  회원가입창 만들기

   ```html
   <form name = "frmMember">
       <table>
           <th> 회원 가입창</th>
           <tr>
               <td>아이디</td>
               <td><input type = "text" name = "id">
               </td>
           </tr>
           <tr>
               <td>비밀번호</td>
               <td><input type = "password" name = "pwd"></td>
           </tr>
           <tr>
               <td>이름</td>
               <td><input type = "text" name = "name"></td>
           </tr>
           <tr>
               <td>이메일</td>
               <td><input type = "text" name = "email"></td>
           </tr>
       </table>
   	<input type = "button" value = "가입하기" onclick = "fn_sendMember()">
   	<input type = "reset" value = "다시입력">
   	<input type = "hidden" name = "command" value = "addMember">
   </form>
   ```

   2. 아이디 중복체크 버튼 만들기

   ```html
   <input type = "button" value = "아이디 중복체크" onclick = "location.href = '/webapp/CheckIdServlet?id=abc'"></td>
   ```

   - CheckIdServlet()

   1. memberForm.html에서 넘겨받은 id 파라메타 저장

   ```java
   String id = request.getParameter("id");
   ```

   2. DB에 저장된 아이디와 중복인지 검색

   ```java
   String sql = "SELECT ID FROM USERINFO WHERE ID=?";
   ```

   3. DB에 중복된 아이디가 있으면 userinfo에 저장,

      setAttribute이용해 데이터를 바인딩(다른 서블릿으로 공유하기 위해)

   ```java
   if(rs.next()){
       userinfo = new UserInfo().
           setId(rs.getString("ID"));
       request.setAttribute("userinfo", userinfo);
   }
   ```

   4. jsp파일로 포워드, include로 바인딩된 자원 가져감

   ```java
   request.getRequestDispatcher("jspEx/CheckId.jsp").include(request, response);
   
   또는
   RequestDispatcher rd = request.getRequestDispatcher("jspEx/CheckId.jsp")
   rd.forward(request, response);
   ```

   - CheckId.jsp

   1.  getAttribute로 userinfo자원 가져옴

   ```jsp
   <%@ page import = "db.DBAction, java.sql.*, dto.UserInfo"%> 
   
   <% UserInfo userinfo = (UserInfo)request.getAttribute("userinfo"); %>
   ```

   2. userinfo가 null이 아니면 사용불가 아이디, null이면 사용가능 아이디

   ```jsp
   <% if(userinfo != null){ %>
   	<%= userinfo.getId() + "는 사용할 수 없는 아이디입니다." %>
   	<input type = 'button' value='닫기' onclick = 'window.close()'/>
   <%} else{ %>
   	<%= request.getParameter("id")+"는 사용가능한 아이디 입니다." %>
   		<input type = 'button' value='닫기' onclick = 'window.close()'/>
   <% } %>
   ```

- 회원 가입 창
      ![회원 가입 창](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210924201531888.png)



- 아이디 중복 체크 클릭했을 때
![image-20210924201545509](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210924201545509.png)

2. 우편번호 검색

   - PostServlet.java

   1. Post.jsp에서 dong 값을 파라메타로 받아옴, url에 Post.jsp저장

   ```java
   String url = "jspEx/Post.jsp";
   String dong = request.getParameter("dong");
   ```

   2. 값이 null이 아닐때 DB에 동과 관련된 주소값들을 불러와 AddressVo객체에 저장

   ```JAVA
   if(dong != null) {
   ArrayList<AddressVo> list = new ArrayList<>();
   String sql = "SELECT * FROM ZIPCODE WHERE DONG LIKE '%" + dong.trim()+"%'";
   Statement stmt = null;
   ResultSet rs = null;
   try{
       stmt = conn.createStatement();
       rs = stmt.executeQuery(sql);
       while(rs.next()) {
           AddressVo addressVo = new AddressVo();
           addressVo.setSido(rs.getString("sido"));
           addressVo.setGugun(rs.getString("gugun"));
           addressVo.setDong(rs.getString("dong"));
           addressVo.setBunji(rs.getString("bunji"));
          addressVo.setZipCode(rs.getString("zipcode"));
           list.add(addressVo);
   
       }
   }
   ```

   3. setAttribute이용해 list데이터를 바인딩(Post.jsp로 공유하기 위해)

   ```java
   request.setAttribute("addressList", list);
   ```

   4. dispatcher이용해 Post.jsp로 이동, 바인딩한 자원 가져감

   ```java
   RequestDispatcher dispatcher = request.getRequestDispatcher(url);
   dispatcher.include(request, response);
   ```

   - Post.jsp

   1. 우편번호 검색 html 작성, submit했을 때, dong 값을 가지고 PostServlet으로 이동

   ```html
   <h1>우편번호 검색</h1>
   <form action = "/webapp/PostServlet" method = "post">
       동 이름 : <input type = "text" name = "dong"/>
       <input type = "submit" value = "찾기"/>
   </form>
   ```

   2. getAttribute로 주소값을 가지고와 저장,  list 출력

   ```jsp
   <table id = "zipcode">
   		<tr>
   			<th>우편번호</th>
   			<th>주소</th>
   		</tr>
       <% 
       ArrayList<AddressVo> list = (ArrayList<AddressVo>)request.getAttribute("addressList");
       if(list == null) return;
       %>
       <% for(AddressVo  data : list){ %>
       <tr>
           <td><%= data.getZipCode() %></td>
           <td><a href = "#" ><%= data.getZipCode() %></a>
               <%= data.getSido() %>', '<%= data.getGugun() %>', '<%= data.getDong() %>
   				</td>	
   			</tr>	
   		<%} %>	
   	</table>
   ```

- 검색 화면

![image-20210924203808726](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210924203808726.png)

- 찾기 클릭 시

![image-20210924203914704](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210924203914704.png)

