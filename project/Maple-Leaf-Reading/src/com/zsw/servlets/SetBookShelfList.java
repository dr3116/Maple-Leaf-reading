package com.zsw.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbu.DBUtil;

/**
 * Servlet implementation class SetBookShelfList
 */
@WebServlet("/SetBookShelfList")
public class SetBookShelfList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetBookShelfList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bkName = request.getParameter("bbbookName");
		System.out.print(bkName);
		String uId = request.getParameter("uuuserId");
		System.out.print(uId);
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql = "insert into bookshelf(user_id,book_name)values("+uId+","+"'"+bkName+"'"+")";
			dbUtil.addDataToTable(sql);
			System.out.println(sql);
			boolean b = false;
			b = dbUtil.isExist("select * from bookshelf where user_id = "+uId+" and book_name="+"'"+bkName+"'");
			System.out.print("判断结果："+b);
			if(b) {
				System.out.println("数据库表有数据");
			}else {
				System.out.println("数据库表没有数据");
			}
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
