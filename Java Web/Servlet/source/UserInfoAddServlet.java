package userInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserInfo;

/**
 * Servlet implementation class UserInfoAddServlet
 */
@WebServlet("/UserInfoAddServlet")
public class UserInfoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Connection conn;
    @Override
    public void init() throws ServletException{
    	
    	String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/app";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"root","java");
		}catch(Exception e) {e.printStackTrace();}	
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>회원 등록</title></head>");
		out.println("<body><h1>회원 등록</h1>");
		out.println("<form action = '/webapp/UserInfoAddServlet' method = 'post'>");
		
		//post를 쓸 때에는 form태그를 활용하여 여러 데이터를 한번에 넘길 수 있어야 한다.
		out.println("아이디 : <input type = 'text' name = 'id'><br>");
		out.println("비밀번호 : <input type = 'password' name = 'pw'><br>");
		out.println("이름 : <input type = 'text' name = 'name'><br>");
		out.println("이메일 : <input type = 'text' name = 'name'><br>");
		
		out.println("<input type = 'submit' value = '추가'>");
		out.println("<input type = 'reset' value = '취소'>");
		out.println("</form");
		out.println("</body></html>");
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		//null, 공백 체크해주기
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		UserInfo user = new UserInfo().setId(id).setPw(pw).setName(name);
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			stmt = conn.createStatement();
//			pstmt = conn.prepareStatement("insert into bank_member values(?,?,?)");
//			pstmt.setString(1,user.getId());
//			
			
			int result = stmt.executeUpdate("insert into userinfo values('"+user.getId()+"','"+user.getPw()+"','"+user.getName()+"')");
			out.println(result > -1 ? "<a href='/webapp/UserInfoListServlet'>회원가입 성공</a>" : "가입 실패");
			out.println();
			out.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				//순서도 기억!
			
				if(stmt!=null) stmt.close();
	
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		

	}
	@Override
	public void destroy() {
		try {
			if(conn!=null) 
			{conn.close();}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
