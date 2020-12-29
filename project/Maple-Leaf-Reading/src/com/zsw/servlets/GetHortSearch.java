package com.zsw.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
 * Servlet implementation class GetHortSearch
 */
@WebServlet("/GetHortSearch")
public class GetHortSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHortSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bName=(String)request.getParameter("search");
		String tempStr="";
		try {
			DBUtil dbUtil;
			dbUtil = DBUtil.getInstance();
			String sql="select * from search_history order by search_num desc";
			ResultSet res=dbUtil.queryDate(sql);
			
			while(res.next()) {
				String searchContent=res.getString("search_content");
				tempStr=tempStr+"&&"+searchContent;
				System.out.println("\n"+tempStr);
			}
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson=new Gson();
		String jsonStr=gson.toJson(tempStr);
		PrintWriter writer=response.getWriter();
		writer.write(jsonStr);
		System.out.println("\n"+jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
