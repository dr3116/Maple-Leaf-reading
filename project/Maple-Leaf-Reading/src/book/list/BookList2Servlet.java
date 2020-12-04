package book.list;

import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.entity.Book;
import com.example.service.BookService;


@WebServlet("/BookList2Servlet")
public class BookList2Servlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       

    public BookList2Servlet() {
        super();
    }


	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		System.out.println("this is BookList2Servlet ");
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		
		//获取表单填写的数据
		String bookName=request.getParameter("bookName");
		System.out.println("BookName1="+bookName);
		String classification=request.getParameter("classification");
		String readingVolume=request.getParameter("readingVolume");
			int readingVolumeInt=Integer.parseInt(readingVolume);
		String numberOfChapters=request.getParameter("numberOfChapters");
			int numberOfChaptersInt=Integer.valueOf(numberOfChapters);
			//时间
			java.util.Date date = new java.util.Date();          // 获取一个Date对象
	        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
//	        System.out.println("11111    "+timeStamp);
			
	        
			//------------
		String bookPhoto="testName.jpg";
		//书籍评分，默认5.0
			Double bookRatingDouble=5.0;
		String author=request.getParameter("author");
		String briefIntroduction=request.getParameter("briefIntroduction");
		//默认收藏数
		int numberOfCollectionsInt=0;
		//构建Book对象
		Book book=new Book();
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setBookPhoto(bookPhoto);
		book.setBookRating(bookRatingDouble);
		book.setBriefIntroduction(briefIntroduction);
		book.setClassification(classification);
		book.setNumberOfChapters(numberOfChaptersInt);
		book.setReadingVolume(readingVolumeInt);
		book.setReleaseTime(timeStamp);
		book.setNumberOfCollections(numberOfCollectionsInt);
		
		
		
		//判断有没有那本书
		BookService bookService = new BookService();

		int findBookInt=0;// 0 没有书，1有了那本书
		try { findBookInt= bookService.findBook(bookName);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(findBookInt==0) {
			//存数据库
			try {
				bookService.addBook(book);
				System.out.println("开始存储图书");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("bookName", bookName);
			request.setAttribute("message", "亲爱的管理员，图书添加成功,请开始添加图书图片与图书文件");
			request.getRequestDispatcher("./book-list22.jsp").forward(request, response);
		}else {
			System.out.println("图书有了，不可以存");
			request.setAttribute("message", "亲爱的管理员，图书唯一，不可重复添加哦");
			request.getRequestDispatcher("./book-list2.jsp").forward(request, response);
		}
		
		

		
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
