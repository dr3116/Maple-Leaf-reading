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
 * Servlet implementation class GetBookShelf
 */
@WebServlet("/GetBookShelf")
public class GetBookShelf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookShelf() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userId=(String)request.getParameter("userId");
		List<BookShelfBook> books=new ArrayList<>();
			try {
				DBUtil dbUtil;
				dbUtil = DBUtil.getInstance();
				String sql = "select * from bookshelf where user_id='" +Integer.valueOf(userId) +"'";
				
				boolean b=false;
				b=dbUtil.isExist(sql);
				System.out.print("�жϽ����"+b);
				if(b) {
					ResultSet res=dbUtil.queryDate(sql);
					
					
					//����next��������һ��Ҫд����Ȼû��ָ����һ��
					while(res.next()) {
						
						String bookName=res.getString("book_name");
					//��book����ȥ����ͼ��ͼƬ����
						String sql4="select book_photo from book where book_name='"+ bookName+ "'";
						ResultSet res4=dbUtil.queryDate(sql4);
						res4.next();
						String bookImg=res4.getString("book_photo");
						//ʵ����bookShelfBook
						BookShelfBook bookShelfBook=new BookShelfBook(bookName,bookImg);
						books.add(bookShelfBook);		
					}
						
				}else {
					System.out.println("���ݿ��û������");
				}
				System.out.println("\n"+"postcomments���ݳ��ȣ�"+books.size());
				
				//�����󼯺�ת����
				Gson gson=new Gson();
				String listArray=gson.toJson(books);
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
