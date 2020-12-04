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
		System.out.println("\n"+"��¼�������ӳɹ�");
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
				System.out.print("�жϽ����"+b);
				if(b) {
					ResultSet res=dbUtil.queryDate(sql);
					
					//����next��������һ��Ҫд����Ȼû��ָ����һ��
					while(res.next()) {
						userId=res.getInt("user_id");
						//�����󼯺�ת����
						Gson gson=new Gson();
						String userIdStr=gson.toJson(userId);
						System.out.println("\n"+"��¼�û�Id:"+userIdStr);
						PrintWriter writer=response.getWriter();
						writer.write(userIdStr);
					}
						
				}else {
					Gson gson=new Gson();
					String error=gson.toJson("error");
					PrintWriter writer=response.getWriter();
					writer.write(error);
					System.out.println("���ݿ��û������");
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
