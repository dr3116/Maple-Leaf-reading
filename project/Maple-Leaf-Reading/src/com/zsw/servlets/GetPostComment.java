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
 * Servlet implementation class GetPostComment
 */
@WebServlet("/GetPostComment")
public class GetPostComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String postId=(String)request.getParameter("postId");
		List<PostComment> postcomments=new ArrayList<>();
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql = "select * from comments where post_id='" +Integer.valueOf(postId) +"'";
				
				boolean b=false;
				b=dbUtil.isExist(sql);
				System.out.print("判断结果："+b);
				if(b) {
					ResultSet res=dbUtil.queryDate(sql);
					
					
					//这里next（）方法一定要写，不然没有指到第一行
					while(res.next()) {
						
						int commenterId=res.getInt("commenter_id");
						String commentContent=res.getString("content");
						//从user表中获取CommenterName
						String sql2="select user_name from user where user_id='"+ commenterId+ "'";
						ResultSet res2=dbUtil.queryDate(sql2);
						res2.next();
						String userName=res2.getString("user_name");
						
						//实例化post
						PostComment postComment=new PostComment(commenterId,userName,commentContent);
						postcomments.add(postComment);		
					}
						
				}else {
					System.out.println("数据库表没有数据");
				}
				System.out.println("\n"+"postcomments数据长度："+postcomments.size());
				
				//将对象集合转换成
				Gson gson=new Gson();
				String listArray=gson.toJson(postcomments);
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
