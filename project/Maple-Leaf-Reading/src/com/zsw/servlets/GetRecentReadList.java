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
 * Servlet implementation class GetRecentReadList
 */
@WebServlet("/GetRecentReadList")
public class GetRecentReadList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRecentReadList() {
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
		String uId = (String)request.getParameter("userId");
		System.out.println("\n"+"最近阅读userId"+uId);
		List<RecentRead> recentReads = new ArrayList<>();
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql = "select * from book LEFT JOIN recent_reading on book.book_name = recent_reading.book_name where user_id ="+uId;
			boolean b = false;
			b = dbUtil.isExist(sql);
			System.out.print("判断结果："+b);
			if(b) {
				ResultSet res=dbUtil.queryDate(sql);	
				while(res.next()) {
					int userId = res.getInt("user_id");
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
					Timestamp timestamp2=res.getTimestamp("last_reading_time");
					Date lastReadingTime = new Date(timestamp2.getTime());
					SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
					String s2 = simpleDateFormat2.format(lastReadingTime);
					RecentRead recentRead = new RecentRead(userId,bookName,readingVolume,numberOfChapters,classification,s,bookPhoto,bookRating,author,numberOfCollections,briefIntroduction,s2);
					recentReads.add(recentRead);
				}
			}else {
				System.out.println("数据库表没有数据");
			}
			System.out.println("\n"+"recentReads数据长度："+recentReads.size());
			//将对象集合转换成
			Gson gson=new Gson();
			String listArray=gson.toJson(recentReads);
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
