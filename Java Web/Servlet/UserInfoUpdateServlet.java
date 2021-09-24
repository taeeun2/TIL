package userInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAction;

/**
 * Servlet implementation class UserInfoUpdateServlet
 */
@WebServlet("/UserInfoUpdateServlet")
public class UserInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
	    Connection conn = DBAction.getInstance().getConnection();
	    PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	

	    request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");			 
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>회원정보</title></head>");
		out.println("<body><h1>회원정보</h1>");
		
		try {
			pstmt = conn.prepareStatement("select id,pw,name from userinfo where id = ?");
			pstmt.setString(1, request.getParameter("id"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				out.println("<form action = '/webapp/UserInfoUpdateServlet' method = 'post'>");
				out.println("아이디 : <input type = 'text' name = 'id' value ='"+rs.getString("ID")+"' readonly><br>");//disable : 데이터 전송이 안됨
				out.println("암호 : <input type = 'text' name = 'pw' value = '"+rs.getString("PW")+"'><br>");
				out.println("이름 : <input type = 'text' name = 'name' value = '"+rs.getString("NAME")+"'><br>");
				out.println("<input type = 'submit' value = '저장'>");
				out.println("<input type = 'button' value= '취소' onclick = 'location.href=\"/webapp/UserInfoListServlet\"'>");
				out.println("<input type = 'button' value= '삭제' onclick = 'location.href=\"/webapp/UserInfoDeleteServlet?id="+rs.getString("ID")+"\"'>");
				out.println("</form>");
			}
			else {
				out.println("<b>회원정보 없음</b>");
			}
			out.println("</body></html>");
			out.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(rs!=null) rs.close();
			if(pstmt !=null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}

	 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	Connection conn = DBAction.getInstance().getConnection();
		    PreparedStatement pstmt = null;	
		    
		    request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			try {
				pstmt = conn.prepareStatement("update userinfo set pw = ?, name = ? where id = ?");
				pstmt.setString(1, request.getParameter("pw"));
				pstmt.setString(2, request.getParameter("name"));
				pstmt.setString(3, request.getParameter("id"));

				int result = pstmt.executeUpdate();
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>회원정보</title><head><body>");
				out.println(result > -1 ?"<a href = '/webapp/UserInfoListServlet'>회원변경 성공</a>" : "회원변경 실패");
				out.println("</body><html>");
				out.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
				if(pstmt !=null) pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
		}
	}
}