package com.zsw.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dbu.DBUtil;

/**
 * Servlet implementation class SetBookReviewList
 */
@WebServlet("/SetBookReviewList")
public class SetBookReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetBookReviewList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int bookReviewId = 0;
		String bkName = request.getParameter("AddBookName");
		String pinglun = request.getParameter("AddPingLun");
		String uId = request.getParameter("uuId");
		int Id = Integer.parseInt(uId);
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql2 = "select * from book_review order by book_review_id desc limit 1";		
			boolean c = false;
			c = dbUtil.isExist(sql2);
			System.out.print("判断结果："+c);
			if(c) {
				ResultSet res=dbUtil.queryDate(sql2);			
				while(res.next()) {
					bookReviewId = res.getInt("book_review_id");
				}
			}
			String sql = "insert into book_review(book_review_id,book_name,user_id,content,score)values("+
					bookReviewId+1+","+"'"+bkName+"'"+","+Id+","+
					"'"+pinglun+"'"+","+5.00+")";
			dbUtil.updateData(sql);
			System.out.println(sql);
			boolean b = false;
			b = dbUtil.isExist("select * from book_review where book_review_id ="+bookReviewId+1);
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
