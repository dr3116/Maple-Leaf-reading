package student.list;

import java.io.File;
import java.io.IOException;
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

import com.example.service.BookService;
import com.example.service.StudentService;

/**
 * Servlet implementation class Studnet22Servlet
 */
@WebServlet("/Studnet22Servlet")
public class Studnet22Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Studnet22Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//设置对客户端请求进行重新编码的编码。
		response.setCharacterEncoding("UTF-8");//指定对服务器响应进行重新编码的编码。
		response.setHeader("content-type","text/html;charset=UTF-8");
		
		//加载文件工厂
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		String bookName=request.getParameter("name").toString();	
		System.out.println("bookName="+bookName);	

		try {
			List<FileItem> list= upload.parseRequest(request);//设置编码方式
			upload.setHeaderEncoding("UTF-8");
			System.out.println("------开始处理上传文件");
			//服务器仓库地址
			String imgPath=this.getServletContext().getRealPath("/img1");//图片路径

			
			String imgName="";
			for(FileItem item : list) {//for便利每个文件				
				if(!item.isFormField()  && item.getName()!="") {//文件类型且文件名不为空
					String ext = item.getName().substring(item.getName().lastIndexOf("."), item.getName().length());//有点	
						imgName=bookName+ext;//图片名称=书名+后缀
						System.out.println("imgname="+imgName);
						
						//开始上传
						item.write(new File(imgPath+"/"+imgName));	
						//更改数据库名字			
						StudentService studnetService=new StudentService();
						studnetService.changeImageByName(imgName,bookName);
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("本次文件上传成功，----------------------------");


		request.setAttribute("message", "亲爱的管理员，头像上传成功");
		request.getRequestDispatcher("./student-list2.jsp").forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
	}

}
