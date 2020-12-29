package download.books;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.twelvemonkeys.lang.SystemUtil;


@WebServlet("/DownloadBooksServlet")
public class DownloadBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DownloadBooksServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String name=request.getParameter("info");
		
		//获得本地文件输出流
		//1. 得到文件的路径
		String path=this.getServletContext().getRealPath("/pdf/");
		File file=new File(path+name);
		//F:\WorkSpace\(new)SpaceForEclipseEE\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Maple_leaf_reading\pdf\Android Studio 用户指南
		
		//输出流 
		response.setCharacterEncoding("utf-8");
		OutputStream out = new FileOutputStream(file, true);
		out.flush();
		out.close();		

		
		
		response.getWriter().write("文件流输出成功");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
