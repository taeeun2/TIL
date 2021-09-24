package userInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserInfoDao;
import db.DBAction;
import dto.UserInfo;

/**
 * Servlet implementation class UserInfoListServlet
 */
@WebServlet("/UserInfoListServlet")
public class UserInfoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    private Connection conn;
    @Override
    public void init() throws ServletException{
    	DBAction db = DBAction.getInstance();
    	conn = db.getConnection();
    }
	//init()에서 안해도 된다(singletone이기 때문)
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
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
////			out.println("<td><b>"+user.getId()+"</b></td>");
//		
//			out.println("<td><b><a href='/webapp/UserInfoUpdateServlet?id="+user.getId()+"'>"+user.getId()+"</a></b></td>");
//
//			out.println("<td><b>"+user.getPw()+"</b></td>");
//			out.println("<td><b>"+user.getName()+"</b></td>");
//			
//			out.println("</tr>");
//		}
//		
//		out.println("</table>");
//		out.println("<a href = '/webapp/UserInfoAddServlet'>회원가입</a>");
//		out.println("</body></html>");
//		out.close();
//		
//		Statement stmt = null;
//		ResultSet rs = null;
//		ResultSetMetaData rsmd = null;
//		List<UserInfo> users = new ArrayList<>();
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery("select * from userinfo");
////			TODO:JavaBean
//			rsmd = rs.getMetaData();
//			int cols = rsmd.getColumnCount();

//
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				//순서도 기억!
//				if(rs != null) rs.close();
//				if(stmt!=null) stmt.close();
//	
//			}catch(SQLException e) {}
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
