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
 * Servlet implementation class GetSignInPaihangList
 */
@WebServlet("/GetSignInPaihangList")
public class GetSignInPaihangList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSignInPaihangList() {
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
		List<SignInPaihang> signInPaihangs = new ArrayList<>();
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql = "select * from user order by sign_num desc";
			boolean b=false;
			b=dbUtil.isExist(sql);
			System.out.print("判断结果："+b);
			if(b) {
				ResultSet res=dbUtil.queryDate(sql);
				//这里next（）方法一定要写，不然没有指到第一行
				while(res.next()) {
					String userName = res.getString("user_name");
					int signInDay = res.getInt("sign_num");
					String userPhoto = res.getString("user_photo");
					SignInPaihang signInPaihang = new SignInPaihang(userName, signInDay,userPhoto);
					signInPaihangs.add(signInPaihang);
				}
			}else {
				System.out.println("数据库表没有数据");	
			}
			System.out.println("\n"+"signInPaihangs数据长度："+signInPaihangs.size());
			//将对象集合转换成
			Gson gson=new Gson();
			String listArray=gson.toJson(signInPaihangs);
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
