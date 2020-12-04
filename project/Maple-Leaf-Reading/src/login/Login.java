package login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.entity.Student;
import com.example.service.StudentService;

import master.Master;
import master.MasterService;



@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
    public Login() {
        super();
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		if(name!=null && !name.equals("") && password!=null && !password.equals("")) {
			//System.out.println("管理员登陆    ,账号："+ name+"  密码："+password);
			//链接数据库，开始查询用户表,参数用户名与密码
			MasterService masterService=new MasterService();
			int judge=masterService.getUser(name,password);
			//System.out.println("管理员身份验证的结果为  "+judge);
			if(judge==1) {
				//管理员身份验证通过，可以跳转，
				request.getSession().setAttribute("username", name);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else if(judge==0) {
				//身份验证失败，提示用户,请重新验证
				request.setAttribute("loginMessage", "登陆失败，请重新登录");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else {
			//身份验证失败，提示用户,请重新验证
			request.setAttribute("loginMessage", "登陆失败，请重新登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
}