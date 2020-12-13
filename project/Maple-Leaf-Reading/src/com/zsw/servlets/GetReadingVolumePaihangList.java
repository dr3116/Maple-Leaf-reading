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
 * Servlet implementation class GetReadingVolumePaihangList
 */
@WebServlet("/GetReadingVolumePaihangList")
public class GetReadingVolumePaihangList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReadingVolumePaihangList() {
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
		List<ReadingVolumePaihang> readingVolumePaihangs = new ArrayList<>();
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql = "select * from book order by reading_volume desc";
			boolean b=false;
			b=dbUtil.isExist(sql);
			System.out.print("判断结果："+b);
			if(b) {
				ResultSet res=dbUtil.queryDate(sql);
				//这里next（）方法一定要写，不然没有指到第一行
				while(res.next()) {
					String bookName = res.getString("book_name");
					int readingVolume = res.getInt("reading_volume");
					String bookPhoto = res.getString("book_photo");
					ReadingVolumePaihang readingVolumePaihang = new ReadingVolumePaihang(bookName, readingVolume,bookPhoto);
					readingVolumePaihangs.add(readingVolumePaihang);
				}
			}else {
				System.out.println("数据库表没有数据");	
			}	
			System.out.println("\n"+"readingVolumePaihangs数据长度："+readingVolumePaihangs.size());
			//将对象集合转换成
			Gson gson=new Gson();
			String listArray=gson.toJson(readingVolumePaihangs);
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
