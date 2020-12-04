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
@WebServlet("/GetSearchList")
public class GetSearchList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSearchList() {
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
		String bName=(String)request.getParameter("search");
		List<Book> books = new ArrayList<>();
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql = "select * from book where book_name like"+"'%"+bName+"%'";	
			System.out.print("\n"+sql);
			boolean b = false;
			b = dbUtil.isExist(sql);
			System.out.print("�жϽ����"+b);
			if(b) {
				ResultSet res=dbUtil.queryDate(sql);
				
				while(res.next()) {
					String bookName = res.getString("book_name");
					String classification = res.getString("classification");
					int readingVolume = res.getInt("reading_volume");
					int numberOfChapters = res.getInt("number_of_chapters");
					Timestamp timestamp=res.getTimestamp("release_time");
					Date releaseTime = new Date(timestamp.getTime());
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
					String s = simpleDateFormat.format(releaseTime);
					String bookPhoto = res.getString("book_photo");
					double bookRating = res.getDouble("book_rating");
					String author = res.getString("author");
					int numberOfCollections = res.getInt("number_of_collections");
					String briefIntroduction = res.getString("brief_introduction");
					Book book = new Book(bookName,classification,readingVolume,numberOfChapters,s,bookPhoto,bookRating,author,numberOfCollections,briefIntroduction);
					books.add(book);
				}
			}else {
				System.out.println("���ݿ��û������");
			}
			System.out.println("\n"+"posts���ݳ��ȣ�"+books.size());
			//�����󼯺�ת����
			Gson gson=new Gson();
			String listArray=gson.toJson(books);
			System.out.println("\n"+listArray);
			System.out.println("search:"+bName);
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
