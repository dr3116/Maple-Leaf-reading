package com.zsw.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbu.DBUtil;

/**
 * Servlet implementation class UpSignDateNum
 */
@WebServlet("/UpSignDateNum")
public class UpSignDateNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpSignDateNum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n"+"开始增加签到数量");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String signerId=(String)request.getParameter("signerId");
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql="select * from user where user_id='"+ signerId +"'";
				boolean b=false;
				b=dbUtil.isExist(sql);
				System.out.print("判断结果："+b);
				int signNum=0;
				if(b) {
					ResultSet res=dbUtil.queryDate(sql);
					//这里next（）方法一定要写，不然没有指到第一行
					while(res.next()) {
						signNum=res.getInt("sign_num");
					}
				}
				++signNum;
				String sql2="update user set sign_num='"+signNum+"' where user_id='"+signerId+"'";
				dbUtil.updateData(sql2);
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
