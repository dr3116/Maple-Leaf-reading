package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Logout() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("name");
		String state=request.getParameter("state");
		System.out.println("管理员退出的用户名为：  "+username+"  退出的状态为  "+state);
		//注销Session
		request.getSession().removeAttribute("username");
		request.removeAttribute("username");
		//跳转到登陆页面
		if(state.equals("1")) {
			request.setAttribute("loginMessage", "退出登录成功");
		}else if(state.equals("2")) {
			request.setAttribute("loginMessage", "用户退出成功，开始重新登录");
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
		System.out.println("");
		System.out.println("身份注销成功");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
