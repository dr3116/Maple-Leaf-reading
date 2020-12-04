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
 * Servlet implementation class GetCommentList
 */
@WebServlet("/GetCommentList")
public class GetCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetCommentList() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Post> posts=new ArrayList<>();
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql = "select * from post";
				
				
				boolean b=false;
				b=dbUtil.isExist(sql);
				System.out.print("�жϽ����"+b);
				if(b) {
					ResultSet res=dbUtil.queryDate(sql);
					
					
					//����next��������һ��Ҫд����Ȼû��ָ����һ��
					while(res.next()) {
						int postId=res.getInt("post_id");
						String title=res.getString("title");
						String content=res.getString("content");
						String photo=res.getString("photo");
						Timestamp timestamp=res.getTimestamp("post_time");
						Date postTime=new Date(timestamp.getTime());
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
						String s = simpleDateFormat.format(postTime);
					
						String bookName= res.getString("book_name");
						//��book����ȥ����ͼ������
						String sql3="select author from book where book_name='"+ bookName+ "'";
						ResultSet res3=dbUtil.queryDate(sql3);
						res3.next();
						String bookAuthor=res3.getString("author");
						////��book����ȥ����ͼ��ͼƬ����
						String sql4="select book_photo from book where book_name='"+ bookName+ "'";
						ResultSet res4=dbUtil.queryDate(sql4);
						res4.next();
						String bookImg=res4.getString("book_photo");
						//
						int numberOFLikes=res.getInt("number_of_likes");
						int comments=res.getInt("comments");
						int userId=res.getInt("user_id");
						
						//��user���л�ȡuserName
						String sql2="select user_name from user where user_id='"+ userId+ "'";
						ResultSet res2=dbUtil.queryDate(sql2);
						res2.next();
						String userName=res2.getString("user_name");
						
						
						//ʵ����post
						Post post=new Post(postId,title,content,photo,s,bookImg,bookName,bookAuthor,numberOFLikes,comments,userId,userName);
						posts.add(post);		
					}
						
				}else {
					System.out.println("���ݿ��û������");
				}
				System.out.println("\n"+"posts���ݳ��ȣ�"+posts.size());
				
				//�����󼯺�ת����
				Gson gson=new Gson();
				String listArray=gson.toJson(posts);
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
