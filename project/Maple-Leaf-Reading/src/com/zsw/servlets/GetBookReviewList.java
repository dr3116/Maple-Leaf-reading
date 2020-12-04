package com.zsw.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbu.DBUtil;

/**
 * Servlet implementation class GetBookList
 */
@WebServlet("/GetBookReviewList")
public class GetBookReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookReviewList() {
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
		List<BookReview> bookReviews = new ArrayList<>();
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql = "select * from book_review";
			
			
			
			boolean b = false;
			b = dbUtil.isExist(sql);
			System.out.print("�жϽ����"+b);
			if(b) {
				ResultSet res=dbUtil.queryDate(sql);
				
				while(res.next()) {
					int bookReviewId = res.getInt("book_review_id");
					String bookName = res.getString("book_name");
					int userId = res.getInt("user_id");
					String content = res.getString("content");
					double score = res.getDouble("score");
					
					BookReview bookReview = new BookReview(bookReviewId,bookName,userId,content,score);
					bookReviews.add(bookReview);
				}
			}else {
				System.out.println("���ݿ��û������");
			}
			System.out.println("\n"+"Review���ݳ��ȣ�"+bookReviews.size());
			//�����󼯺�ת����
			Gson gson=new Gson();
			String listArray=gson.toJson(bookReviews);
			System.out.println("\n"+listArray);
			PrintWriter writer=response.getWriter();
			writer.write(listArray);
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
