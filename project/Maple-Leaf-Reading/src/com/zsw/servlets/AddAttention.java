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
 * Servlet implementation class UpAttentionInfo
 */
@WebServlet("/AddAttention")
public class AddAttention extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAttention() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n"+"开始添加关注信息");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userId=(String)request.getParameter("userId");
		String posterId=(String)request.getParameter("posterId");
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql="select * from fans where user_id='"+ userId +"' and people_concerned_id='"+posterId+"'";
				boolean b=false;
				b=dbUtil.isExist(sql);
				System.out.print("判断结果："+b);
				if(!b) {
					String sql2="insert into fans(user_id,people_concerned_id) values('"+userId+"','"+posterId+"')" ;
					dbUtil.insertData(sql2);
				}else {
					Gson gson=new Gson();
					String error=gson.toJson("error");
					PrintWriter writer=response.getWriter();
					writer.write(error);
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
