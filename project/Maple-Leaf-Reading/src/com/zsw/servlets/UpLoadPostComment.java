package com.zsw.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbu.DBUtil;

/**
 * Servlet implementation class UpLoadPostComment
 */
@WebServlet("/UpLoadPostComment")
public class UpLoadPostComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpLoadPostComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n"+"开始上传评论");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String posterId=(String)request.getParameter("posterId");
		String postId=(String)request.getParameter("postId");
		String postComment=(String)request.getParameter("postComment");
		System.out.println("\n"+"评论者id："+posterId+"评论Id:"+postId);
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql="insert into comments(post_id,content,commenter_id) values('"+postId+"','"+postComment+"','"+posterId+"')";
				dbUtil.insertData(sql);
				Gson gson=new Gson();
				String error=gson.toJson("error");
				PrintWriter writer=response.getWriter();
				writer.write(error);
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
