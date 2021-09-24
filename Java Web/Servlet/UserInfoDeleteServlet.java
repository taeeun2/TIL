package userInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAction;

/**
 * Servlet implementation class UserInfoDeleteServlet
 */
@WebServlet("/UserInfoDeleteServlet")
public class UserInfoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate("delete from userinfo where id = '"+id+"'");
			out.println(result > -1 ? "<a href = '/webapp/UserInfoListServlet'>회원삭제 성공</a>" : "삭제 실패");
			out.close();
		}catch(Exception e) {
			throw new ServletException(e);
		}finally {
			try {
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
