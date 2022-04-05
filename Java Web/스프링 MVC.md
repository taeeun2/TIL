# 스프링 MVC

| 구성요소          | 설명                                                         |
| ----------------- | ------------------------------------------------------------ |
| DispatcherServlet | 클라이언트의 요청을 전달받아 해당 요청에 대한 컨트롤러를 선택하여 클라이언트의 요청을 전달한다. 또한 컨트롤러가 반환한 값을 View에 전달하여 알맞은 응답을 생성한다. |
| HandlerMapping    | 클라이언트가 요청한 URL을 처리할 컨트롤러를 지정한다.        |
| Controller        | 클라리언트의 요청을 처리한 후 그 결과를 DispatcherServlet에 전달한다. |
| ModelAndView      | 컨트롤러가 처리한 결과 및 뷰 선택에 필요한 정보를 저장한다.  |
| ViewResolver      | 컨트롤러의 처리결과를 전달할 뷰를 지정한다.                  |
| View              | 컨트롤러의 처리 결과 화면을 생성한다.                        |



### 수행과정

1. 브라우저가 DispatcherServlet에 URL로 접근하여 해당 정보를 요청
2. 핸들러 매핑에서 해당 요청에 대해 매핑된 컨트롤러가 있는지 요청
3. 매핑된 컨트롤러에 대해 처리 요청
4. 컨트롤러가 클라이언트의 요청을 처리한 결과와 view이름을 ModelAndView에 저장해서 DispatcherServlet으로 반환
5. DispatcherServlet에서는 컨트롤러에서 보내온 View이름을 ViewResolver로 보내 해당 View를 요청
6. ViewResolver는 요청한 View를 보냄
7. View의 처리 결과를 DispatcherServlet으로 보냄
8. DispatcherServlet은 최종 결과를 브라우저로 전송



### 예제 1)

1. loginForm.jsp에서 "${contextPath}/test/login.do"로 DispatcherServlet요청

   ```jsp
   <form name="frmLogin" method="post"  action="${contextPath}/test/login.do">
   ```

2. 매핑된 컨트롤러가 있는지 확인

   1. web.xml

   ```xml
   <servlet-mapping>
         <servlet-name>action</servlet-name>
         <url-pattern>*.do</url-pattern>
    </servlet-mapping>
   ```

   2. action-servlet.xml

   ```jsp
   <bean id="userUrlMapping"
         class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
       <property name="mappings">
           <props>
               <prop key="/test/*.do">userController</prop>
           </props>
       </property>
   </bean>
   
   <bean id="userController" class="com.spring.ex02.UserController">
   		<property name="methodNameResolver">
   			<ref local="userMethodNameResolver" />
   		</property>
   	</bean>
   	<!-- methodNameResolver프로퍼티에 userMethodNameResolver를 주입해서 지정한 요청명에 대해 직접 메서드 호출 -->
   
   
   	<bean id="userMethodNameResolver"		  class="org.springframework.web.servlet.mvc.multiaction.PropertiesMethodNameResolver">
   		<property name="mappings">
   			<props>
   				<prop key="/test/login.do">login</prop>
   			</props>
   		</property>
   	</bean>
   	<!-- PropertiesMethodNameResolver를 이용해 /test/login.do로 요청하면 userController의 login 메서드를 호출한다. -->
   
   
   ```

3. userController의 login메서드를 호출

   -> 아이디와 비밀번호 정보 바인딩하여 result에 포워딩

   ```java
   public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
   		String userID = "";
   		String passwd = "";
   		ModelAndView mav = new ModelAndView();
   		request.setCharacterEncoding("utf-8");
   		userID = request.getParameter("userID");
   		passwd = request.getParameter("passwd");
   
   		mav.addObject("userID", userID);
   		mav.addObject("passwd", passwd);
   		//ModelAndView에 로그인 정보를 바인딩
   		mav.setViewName("result");
   		//ModelAndView객체에 포워딩할 JSP이름을 설정
   		return mav;
   	}
   	
   ```

4. DispatcherServlet에서는 컨트롤러에서 보내온 View이름을 ViewResolver로 보내 해당 View를 요청

5. ViewResolver는 요청한 View(result.jsp)를 보냄

   ```xml
   <bean id="viewResolver"
         class="org.springframework.web.servlet.view.InternalResourceViewResolver">
       <property name="viewClass"
                 value="org.springframework.web.servlet.view.JstlView" />
       <property name="prefix" value="/test/" />
       <property name="suffix" value=".jsp" />
   </bean>
   	<!-- 컨트롤러에서 ModelAndView인자로 뷰이름이 반환되면 InternalResourceViewResolver의 프로퍼티 prefix속성에 지정된
   		/test/폴더에서 ModelAndView인자로 넘어온 뷰 이름에 해당되는 jsp를 선택해 DispatcherServlet으로 보냄 -->
   ```

6. View의 처리 결과를 DispatcherServlet으로 보냄

   - result.jsp

   ```jsp
   <table border="1" width="50%" align="center" >
   
      <tr align="center">
         <td>아이디</td>
         <td>비밀번호</td>
      </tr>
      <tr align="center">
         <td>${userID}</td>
         <td>${passwd}</td>
      </tr>
   </table>
   ```

7. DispatcherServlet은 최종 결과를 브라우저로 전송

### 실행 결과

![image-20211005135541682](%EC%8A%A4%ED%94%84%EB%A7%81%20MVC.assets/image-20211005135541682.png)

-> 로그인 선택

![image-20211005135604669](%EC%8A%A4%ED%94%84%EB%A7%81%20MVC.assets/image-20211005135604669.png)



### 예제 2) MultiActionController 이용해 회원정보 표시하기



1. memberForm.jsp에서 회원정보를 입력한 후 ${contextPath}/test/memberInfo.do로 Dispatcher요청
2. /test/*.do로 매핑된 userController를 요청
3. /test/memberInfo.do로 요청했기 때문에 memberInfo메서드 호출
4. memberInfo메서드에서 회원정보 받아서 memberInfo뷰에 정보 보냄
5. memberInfo.jsp에서 회원정보 출력



##### 예제 3) 요청명과 동일한 jsp 출력

- getViewName

```java
private  String getViewName(HttpServletRequest request) throws Exception {
    String contextPath = request.getContextPath();
    String uri = (String)request.getAttribute("javax.servlet.include.request_uri");


    if(uri == null || uri.trim().equals("")) {
        uri = request.getRequestURI();
    }

    //http://localhost:8090/member/listMember.do로 요청시
    int begin = 0;  //
    if(!((contextPath==null)||("".equals(contextPath)))){
        begin = contextPath.length();  // 전체 요청명 의 길이를 구함
    }

    int end;
    if(uri.indexOf(";")!=-1){
        end=uri.indexOf(";");  //요청 uri에 ';'가 있을 경우 ';'문자 위치를 구함
    }else if(uri.indexOf("?")!=-1){
        end=uri.indexOf("?");   //요청 uri에 '?'가 있을 경우 '?' 문자 위치를 구함
    }else{
        end=uri.length();
    }

    //http://localhost:8090/member/listMember.do로 요청시 먼저 '.do'를 제거한 http://localhost:8090/member/listMember를 구한 후,
    //다시 http://localhost:8090/member/listMember에서 역순으로 첫번째 '/' 위치를 구한 후, 그 뒤의 listMember를 구한다.
    String fileName=uri.substring(begin,end);
    if(fileName.indexOf(".")!=-1){
        fileName=fileName.substring(0,fileName.lastIndexOf("."));  //요청명에서 역순으로 최초 '.'의 위치를 구한후, '.do' 앞에까지의 문자열을 구함
    }
    if(fileName.lastIndexOf("/")!=-1){
        fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length()); //요청명에서 역순으로 최초 '/'의 위치를 구한후, '/' 다음부터의 문자열을 구함  
    }
    return fileName;
}

```

동작 : /login.do로 요청하면 getViewName() 메소드를 이용하여 login 문자열을 빼서 반환해준다-> viewResolver를 통해 login.jsp를 호출해준다.

