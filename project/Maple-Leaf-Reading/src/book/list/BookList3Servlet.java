package book.list;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.entity.Book;
import com.example.service.BookService;


@WebServlet("/BookList3Servlet")
public class BookList3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BookList3Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.setHeader("content-type","text/html;charset=UTF-8");
				System.out.println("this is BookList2Servlet ");
				
				//获取表单填写的数据
				String bookName=request.getParameter("bookName");
				String classification=request.getParameter("classification");
				String readingVolume=request.getParameter("readingVolume");
					int readingVolumeInt=Integer.parseInt(readingVolume);
				String numberOfChapters=request.getParameter("numberOfChapters");
					int numberOfChaptersInt=Integer.valueOf(numberOfChapters);
					//时间
					java.util.Date date = new java.util.Date();          // 获取一个Date对象
			        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
//			        System.out.println("11111    "+timeStamp);
					
			        
					//------------
//				String bookPhoto=request.getParameter("bookPhoto");
				//书籍评分，默认5.0
					Double bookRatingDouble=5.0;
				String author=request.getParameter("author");
				String briefIntroduction=request.getParameter("briefIntroduction");
				//默认收藏数
				int numberOfCollectionsInt=0;

				
				//判断有没有那本书
				BookService bookService = new BookService();

				int findBookInt=0;// 0 没有书，1有了那本书
				try { findBookInt= bookService.findBook(bookName);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
				
				if(findBookInt==1) {
					//存数据库
					try {
						//bookService.addBook(book);
						bookService.reviseBookWithoutImage(bookName, classification, readingVolumeInt, numberOfChaptersInt, timeStamp, bookRatingDouble, author, numberOfChaptersInt, briefIntroduction);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					request.setAttribute("message", "亲爱的管理员，图书修改成功");
				}else {
					request.setAttribute("message", "亲爱的管理员，请检查图书是否输入正确");
				}
				request.getRequestDispatcher("book-list3.jsp").forward(request, response);
				
				

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
