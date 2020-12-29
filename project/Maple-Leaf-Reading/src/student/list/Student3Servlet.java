package student.list;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.entity.Student;
import com.example.service.StudentService;


@WebServlet("/Student3Servlet")
public class Student3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Student3Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		
		//获取参数
		Object name1=request.getParameter("name");
		Object password1=request.getParameter("password");
		Object id1=request.getParameter("id");
		String name="";
		String password = "";
		String id="";
		
		if(name1!=null && !name1.equals("")) {
			name=name1.toString();
		}
		if(password1!=null && !password1.equals("")) {
			password=password1.toString();
		}
		if(id1!=null && !id1.equals("")) {
			id=id1.toString();
		}
		
		

		StudentService studentService=new StudentService();
		try {
			int num=studentService.changeMessage(id, name, password);
			if(num==1) {
				request.setAttribute("message", "亲爱的管理员，学生信息修改成功");				
			}else {
				request.setAttribute("message", "亲爱的管理员，学生信息修改失败，请注意信息书否输入正确");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("./student-list3.jsp").forward(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
