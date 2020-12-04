package book.list;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.service.BookReviewService;
import com.example.service.BookService;

import dbu.DBUtil;



@WebServlet("/BookList22Servlete")
public class BookList22Servlete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BookList22Servlete() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//设置对客户端请求进行重新编码的编码。
		response.setCharacterEncoding("UTF-8");//指定对服务器响应进行重新编码的编码。
		response.setHeader("content-type","text/html;charset=UTF-8");
		
		//加载文件工厂
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		
		//先上传书，在上传图片
		//图片名=书名+后缀
		try {
			List<FileItem> list= upload.parseRequest(request);//设置编码方式
			upload.setHeaderEncoding("UTF-8");
			System.out.println("------开始处理上传文件");
			//服务器仓库地址
			String imgPath=this.getServletContext().getRealPath("/img");//图片路径
			String txtPath=this.getServletContext().getRealPath("/txt");//图书路径
			
			
			String txtName="";
			String imgName="";
			for(FileItem item : list) {//for便利每个文件				
				if(!item.isFormField()  && item.getName()!="") {//文件类型且文件名不为空
					//System.out.println("getName"+item.getName());//这是文件的全称
					String ext = item.getName().substring(item.getName().lastIndexOf("."), item.getName().length());//有点	
					//System.out.println("ext"+ext);//这是文件的类型，无点



					String bookName=request.getParameter("bookName");	
					if(ext.equals(".txt")) {//上传的是文件
						//获得参数传过来的数据库名字					
						txtName=bookName+ext;
						System.out.println("txtname="+txtName);
						//开始上传
						item.write(new File(txtPath+"/"+txtName));
						//强制文件名是.txt
					}else {//上传的是图片
						imgName=bookName+ext;//图片名称=书名+后缀
						System.out.println("imgname="+imgName);
						//开始上传
						item.write(new File(imgPath+"/"+imgName));	
						//更改数据库名字
						BookService bookService=new BookService();
						bookService.changeBookImageNameByBookName(bookName, imgName);
					}
					
					
					
					

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("本次文件上传成功，----------------------------");


		request.setAttribute("message", "亲爱的管理员，图书上传成功");
		request.getRequestDispatcher("./book-list2.jsp").forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
