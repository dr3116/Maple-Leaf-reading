package welcome;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbu.DBUtil;


@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int num1;//图书数
	private int num2;//用户
	private int num3;//评论
	private int num4;//发现
	private int num5;//管理元
	private int num6;//书的种类
	private DBUtil dbUtil;
	private int list[]= {0,0,0,0,0,0,0,0,0};
	private String userName;     

    public WelcomeServlet() {
        super();
    }

   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userName=request.getParameter("username");
		//
		dbUtil = new DBUtil();
		try {
			//查询所有图书
			ResultSet rs = dbUtil.queryDate("select count(*) from book");
			while(rs.next()) {				
				num1 = rs.getInt(1);
				list[1]=num1;
			}
			rs = dbUtil.queryDate("select count(*) from user");
			while(rs.next()) {				
				num2 = rs.getInt(1);
				list[2]=num2;
			}
			rs = dbUtil.queryDate("select count(*) from comments");
			while(rs.next()) {				
				num3 = rs.getInt(1);
				list[3]=num3;
			}
			rs = dbUtil.queryDate("select count(*) from post");
			while(rs.next()) {				
				num4 = rs.getInt(1);
				list[4]=num4;
			}
			rs = dbUtil.queryDate("select count(*) from master");
			while(rs.next()) {				
				num5 = rs.getInt(1);
				list[5]=num5;
			}
			rs = dbUtil.queryDate("select count(DISTINCT category) from classifications");
			while(rs.next()) {				
				num6 = rs.getInt(1);
				list[6]=num6;
			}
			System.out.println(num1+"--"+num2+"--"+num3+"--"+num4+"--"+num5+"--"+num6+"--"+userName);

			//传递数据到welcome.jsp			
			request.setAttribute("username",userName);
			request.setAttribute("welcomelist", list);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
			
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
