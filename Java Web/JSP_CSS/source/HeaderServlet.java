package userInfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserInfo;


@WebServlet("/HeaderServlet")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HeaderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(3*60);//세션이 만료되면 정보 소멸(3분)
		UserInfo userinfo = (UserInfo)session.getAttribute("userinfo");
		//로그인을 했을 때, userinfo는 null이 아니다.
		if(userinfo != null) {
			out.println(userinfo.getName()+"님 로그인 중입니다.");
			out.println("<input type = 'button' value = '로그아웃' "
					+ " onclick = 'location.href=\"/webapp/UserInfoLogOutServlet\"'>");
		}
		else {
			out.println("session정보 없음<br>");
			out.println("<input type = 'button' value = '로그인'"
					+" onclick = 'location.href = \"/webapp/html/login.html\"'>");
		}
			
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
