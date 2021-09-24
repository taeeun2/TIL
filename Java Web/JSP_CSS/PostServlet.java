package userInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAction;
import dto.AddressVo;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBAction.getInstance().getConnection();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "jspEx/Post.jsp";
		String dong = request.getParameter("dong");
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
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				try {
					if(rs !=null) rs.close();
					if(stmt!=null) stmt.close();
					if(conn != null) conn.close();
				}catch(SQLException e) {}
			}
			request.setAttribute("addressList", list);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.include(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
