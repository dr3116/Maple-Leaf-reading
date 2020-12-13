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
 * Servlet implementation class GetCollection
 */
@WebServlet("/GetCollection")
public class GetCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCollection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n"+"开始获取收藏");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userId=(String)request.getParameter("userId");
		List<Collection> collections=new ArrayList<>();
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql = "select * from collect where user_id='"+userId+"'";
				boolean b=false;
				b=dbUtil.isExist(sql);
				System.out.print("判断结果："+b);
				if(b) {
					ResultSet res=dbUtil.queryDate(sql);
					//这里next（）方法一定要写，不然没有指到第一行
					while(res.next()) {
						int postId=res.getInt("post_id");
						int collectId=res.getInt("collect_id");
						//实例化collection
						Collection collection=new Collection(collectId,Integer.valueOf(userId),postId);
						collections.add(collection);	
					}
						
				}else {
					System.out.println("数据库表没有数据");
				}
				System.out.println("\n"+"collection数据长度:"+collections.size());
				
				//将对象集合转换成
				Gson gson=new Gson();
				String listArray=gson.toJson(collections);
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
