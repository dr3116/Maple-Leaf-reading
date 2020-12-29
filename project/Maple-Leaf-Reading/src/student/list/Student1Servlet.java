package student.list;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.entity.Book;
import com.example.entity.Student;
import com.example.service.BookService;
import com.example.service.StudentService;

@WebServlet("/Student1Servlet")
public class Student1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Student1Servlet() {
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
			System.out.println("deleteBookName="+deleteBookName);
		}
		if(orderObject!=null) {
			order=orderObject.toString();
			System.out.println("order="+order);
		}
		if(searchname!=null) {
			searchName=searchname.toString();
			System.out.println("searchName="+searchname);
		}
		
		
		
		
		
		
		


		int aim;
		String page = request.getParameter("page");//目标页---------------------
		if(page==null || page.equals("")) {
			aim=1;
		}else {
			aim= Integer.parseInt(page);
		}

		
		
		
		
		
		
		//开始处理分页
		int studentsNumber=1;//总数--------------
		StudentService studentService=new StudentService();
		studentsNumber=studentService.getStudentsNumber();//-----		
		request.setAttribute("studentsnumber", studentsNumber);
		
		
		
		

		//判断怎么查数据库
		int lastPage=studentsNumber/10;//最终页
		if(studentsNumber%10>0){
			lastPage++;
		}
		
		if(aim<1) {
			aim=1;
		}
		if(aim>lastPage) {
			aim=lastPage;
		}
		System.out.println("最终aim="+aim);
		
		
		
		
		//判断命令 order//---
		if(order.equals("delete")) {//删除图书，	参数  deleteBookName
			try {
				int userID=Integer.parseInt(deleteBookName);
				studentService.deleteUserById(userID);
				studentsNumber--;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		//编写查询语句
		int num1=(aim-1)*10;
		int num2= aim*10;
		String sql="select * from user limit "+num1+","+num2;	
		//判断是不是精确查询
		if(searchName!="" && !searchName.equals("")) {
			sql="select * from user where user_name like'%"+searchName+"%'";
			System.out.println("search SQL="+sql);
		}
		List<Student> students = studentService.getUsers(sql);	
		System.out.println("getUser的查询结果为："+students.size());
		if(searchName!="" && !searchName.equals("")) {
			request.setAttribute("studentsnumber",students.size());
		}
		
		
		
		
		
		//跳转页面
		request.setAttribute("studentslist1", students);//数据源
		request.setAttribute("aim", aim);
		request.setAttribute("lastpage", lastPage);
		if(searchName!="" && !searchName.equals("")) {//这是搜索的结果
			request.getRequestDispatcher("student-list11.jsp").forward(request, response);
		}else {//这是正常结果
			request.getRequestDispatcher("student-list1.jsp").forward(request, response);
		}
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
