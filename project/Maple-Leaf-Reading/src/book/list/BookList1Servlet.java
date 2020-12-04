package book.list;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.entity.Book;
import com.example.service.BookService;

import dbu.DBUtil;

@WebServlet("/BookList1")
public class BookList1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil dbUtil;
       

    public BookList1Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		System.out.println("   -   -   -   -   -   -   -   -   -   -   -   ");
		
		
		//接受操作的数据与命令（order）
		Object deleteNameObject=request.getParameter("deletename");
		Object orderObject=request.getParameter("order");
		String order="";
		String deleteBookName="";
		Object searchname=request.getParameter("searchname");
		String searchName="";
		if(deleteNameObject!=null) {
			deleteBookName=deleteNameObject.toString();
//			System.out.println("deleteBookName="+deleteBookName);
		}
		if(orderObject!=null) {
			order=orderObject.toString();
//			System.out.println("order="+order);
		}
		if(searchname!=null) {
			searchName=searchname.toString();
		}
		
		
		
		
		
		
		


		int aim;
		String page = request.getParameter("page");//目标页---------------------
		if(page==null || page.equals("")) {
			aim=1;
		}else {
			aim= Integer.parseInt(page);
		}
//		System.out.println("目标页为"+aim);
		
		
		
		
		
		
		//开始处理分页
		int booksNumber=1;//图书总数--------------
		BookService bookService = new BookService();
		booksNumber = bookService.getBooksNumber();
		request.setAttribute("booksnumber", booksNumber);
		
		
		
		

		//判断怎么查数据库
		int lastPage=booksNumber/10;//最终页
		if(booksNumber%10>0){
			lastPage++;
		}
		
		if(aim<1) {
			aim=1;
		}
		if(aim>lastPage) {
			aim=lastPage;
		}
		
		
		
		
		
		//判断命令 order
		if(order.equals("delete")) {//删除图书，	参数  deleteBookName
			try {
				bookService.deleteBookByName(deleteBookName);
				booksNumber--;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		//编写查询语句‘
		int num1=(aim-1)*10;
		int num2= aim*10;
		String sql="select * from book limit "+num1+","+num2;	
		//判断是不是精确查询
		if(searchName!="" && !searchName.equals("")) {
			sql="select * from book where book_name like'%"+searchName+"%'";
			System.out.println("search SQL="+sql);
		}
		List<Book> books = bookService.getBooks(sql);	
		System.out.println("books.length"+books.size());
		if(searchName!="" && !searchName.equals("")) {
			request.setAttribute("booksnumber",books.size());
		}
		
		
		
		
		
		//跳转页面
		request.setAttribute("booklist1", books);
		request.setAttribute("aim", aim);
		request.setAttribute("lastpage", lastPage);
		if(searchName!="" && !searchName.equals("")) {//这是搜索的结果
			request.getRequestDispatcher("book-list11.jsp").forward(request, response);
		}else {//这是正常结果
			request.getRequestDispatcher("book-list1.jsp").forward(request, response);
		}
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
