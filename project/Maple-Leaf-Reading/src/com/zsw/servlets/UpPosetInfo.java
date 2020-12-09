package com.zsw.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbu.DBUtil;

/**
 * Servlet implementation class UpPosetInfo
 */
@WebServlet("/UpPosetInfo")
public class UpPosetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpPosetInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n"+"开始上传评论字符");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String posterId=(String)request.getParameter("posterId");
		String inputStr=(String)request.getParameter("input");
		String bookNameStr=(String)request.getParameter("bookName");
		Date postTime=new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = simpleDateFormat.format(postTime);
		String upPostImgName=(String)request.getServletContext().getAttribute("upPostImgName");
		System.out.println("\n"+"发帖者id："+posterId+"内容:"+inputStr);
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql="insert into post(content,photo,book_name,user_id,post_time) values('"+inputStr+"','"+upPostImgName+"','"+bookNameStr+"','"+posterId+"','"+s+"')";
			dbUtil.insertData(sql);
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
