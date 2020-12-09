package com.zsw.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbu.DBUtil;

/**
 * Servlet implementation class GetBookCategory
 */
@WebServlet("/GetBookCategory")
public class GetBookCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<BookCategory> cateorys=new ArrayList<>();
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql = "select * from classifications";
				boolean b=false;
	
				b=dbUtil.isExist(sql);
				System.out.print("判断结果："+b);
				if(b) {
					ResultSet res=dbUtil.queryDate(sql);
					
					//这里next（）方法一定要写，不然没有指到第一行
					while(res.next()) {
						
						String category=res.getString("category");
						String bookName=res.getString("book_name");
						
						//从book表中去查找图书阅读数
						String sql3="select reading_volume from book where book_name='"+ bookName+ "'";
						ResultSet res3=dbUtil.queryDate(sql3);
						res3.next();
						int readingVolume=res3.getInt("reading_volume");
						
						//从book表中去查找图书章节数
						String sql5="select number_of_chapters from book where book_name='"+ bookName+ "'";
						ResultSet res5=dbUtil.queryDate(sql5);
						res5.next();
						int chapterNum=res5.getInt("number_of_chapters");
						//从book表中去查找图书图片介绍
						String sql6="select brief_introduction from book where book_name='"+ bookName+ "'";
						ResultSet res6=dbUtil.queryDate(sql6);
						res6.next();
						String bookIntroduction=res6.getString("brief_introduction");
						
						//从book表中去查找图书图片名称
						String sql4="select book_photo from book where book_name='"+ bookName+ "'";
						ResultSet res4=dbUtil.queryDate(sql4);
						res4.next();
						String bookImg=res4.getString("book_photo");
						
						//实例化对象
						BookCategory bookcategory=new BookCategory(category,bookName,bookImg,readingVolume,chapterNum,bookIntroduction);	
						cateorys.add(bookcategory);
					
					}
						
				}else {
					System.out.println("数据库表没有数据");
				}
				System.out.println("\n"+"bookCategory数据长度："+cateorys.size());
				//将对象集合转换成
				Gson gson=new Gson();
				String listArray=gson.toJson(cateorys);
				System.out.println("\n"+listArray);
				PrintWriter writer=response.getWriter();
				writer.write(listArray);
					
			} catch (ClassNotFoundException | SQLException e) {
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
