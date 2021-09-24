package userInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBAction;
import dto.UserInfo;

/**
 * Servlet implementation class UserInfoLoginServlet
 */
@WebServlet("/UserInfoLoginServlet")
public class UserInfoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	/*
	 학습 주제
	  	페이지 이동, 페이지 병합, 정보 저장
	  		- forward
	  		- include
	  		- scope(page, request, session, application)
	  		1. page : 페이지 안에서만 정보 저장
	  		2. request : 객체 안에 정보 저장(파라미터로 던짐)
	  		3. session : 이 객체가 소멸될 때 까지 정보 유지, 시간개념 존재, 서버측 메모리에 저장
	  		3-1)cookie : 클라이언트측 정보 저장
	  		4. application :모든 애플리케이션 정보 저장
	  		
	  		page<request<session<application
	  		
	  		ex)
	  		RequestDispatcher rd = request.getRequestDispatcher("페이지 경로")
	  		HttpSession session = request.getSession();
	 */
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("html/login.html");
		rd.forward(request, response);//자원을 가지고 감
//		response.sendRedirect("/html/login.html"); //오직 페이지 이동

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
//		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RequestDispatcher rd1 = request.getRequestDispatcher("HeaderServlet");
		RequestDispatcher rd2 = request.getRequestDispatcher("/error/LoginFail.html");

		try {
			conn = DBAction.getInstance().getConnection();
			stmt = conn.createStatement();
//			pstmt = conn.prepareStatement("SELECT * FROM USERINFO WHERE ID = ?");
//			pstmt.setString(1, id);
//			pstmt.executeQuery();
			rs = stmt.executeQuery("SELECT * FROM USERINFO WHERE ID = '"+request.getParameter("id")+"'");
			if(rs.next()) {
				if(request.getParameter("pw").equals(rs.getString("pw"))) {
					UserInfo userinfo = new UserInfo().setId(rs.getString("id"))
							.setName(rs.getString("name")).setPw(rs.getString("pw"));
//					ex1)
//					response.setContentType("text/html;charset=UTF-8");
//					PrintWriter out = response.getWriter();
//					out.println("<html><head><title>LoginSuccessFully</title><head>");
//					out.println("<body>로그인 성공!<br>");
//					out.println(userinfo.getName()+"님 로그인 중입니다.");
//					out.println("</body></html>");

					
//					ex2)
					HttpSession session = request.getSession();
					session.setAttribute("userinfo", userinfo);
//					session.setMaxInactiveInterval(5);
//					response.sendRedirect("UserInfoListServlet");
					
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<html><head><title>LoginSuccessFully</title><head>");
					rd1.include(request, response);
					out.println("<b><br>Service Page</b></html>");
					out.println("</body></html>");
					
				}else {
					rd2.forward(request,response);
				}
			}else {
				rd2.forward(request, response);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				//순서도 기억!
				if(rs != null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
	
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
