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
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n"+"登录请求链接成功");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String phoneStr=(String)request.getParameter("phoneStr");
		String passwordStr=(String)request.getParameter("passwordStr");
		int userId = 0;
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql = "select * from user where user_name like'" +phoneStr +"' and user_password like'" +passwordStr +"'";
				boolean b=false;
				b=dbUtil.isExist(sql);
				System.out.print("判断结果："+b);
				if(b) {
					ResultSet res=dbUtil.queryDate(sql);
					
					//这里next（）方法一定要写，不然没有指到第一行
					while(res.next()) {
						userId=res.getInt("user_id");
						//将对象集合转换成
						Gson gson=new Gson();
						String userIdStr=gson.toJson(userId);
						System.out.println("\n"+"登录用户Id:"+userIdStr);
						PrintWriter writer=response.getWriter();
						writer.write(userIdStr);
					}
						
				}else {
					Gson gson=new Gson();
					String error=gson.toJson("error");
					PrintWriter writer=response.getWriter();
					writer.write(error);
					System.out.println("数据库表没有数据");
				}
				
				
					
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
