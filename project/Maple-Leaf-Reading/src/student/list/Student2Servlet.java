package student.list;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.entity.Book;
import com.example.entity.Student;
import com.example.service.BookService;
import com.example.service.StudentService;


@WebServlet("/Student2Servlet")
public class Student2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Student2Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		System.out.println(" ");
		System.out.println("this is Student2Servlet ");
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		
		//获取表单填写的数据
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		int sign_num=0;
		String image="default.jpg";

		//构建Book对象
		Student student=new Student();
		student.setNum(sign_num);
		student.setUserName(name);
		student.setUserPassword(password);
		student.setUserPhoto(image);
		
		
		
		//判断有没有那本书
		StudentService studentService=new StudentService();

		int findUserInt=0;// 0 没有书，1有了那本书
		try { 
			findUserInt= studentService.findUser(name);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		
		if(findUserInt==0) {
			//存数据库
			try {
				studentService.addUser(student);
				System.out.println("开始存储用户");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("name", name);
			request.setAttribute("message", "亲爱的管理员，图书添加成功,请开始添加图书图片与图书文件");
			request.getRequestDispatcher("./student-list22.jsp").forward(request, response);
		}else {
			System.out.println("用户已存在，请检查信息是否正确");
			request.setAttribute("message", "亲爱的管理员，用户已存在，请检查信息是否正确");
			request.getRequestDispatcher("./student-list2.jsp").forward(request, response);
		}
		
				

				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
