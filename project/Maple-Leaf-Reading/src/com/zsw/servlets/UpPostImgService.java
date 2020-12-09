package com.zsw.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpPostImgService
 */
@WebServlet("/UpPostImgService")
public class UpPostImgService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpPostImgService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n"+"开始上传Post图片");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获得磁盘文件条目工厂。  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //获取文件上传需要保存的路径，upload文件夹需存在。  
        String path = request.getSession().getServletContext().getRealPath("/imgs");  
        
        //设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。  
        factory.setRepository(new File(path));  
        //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。  
        factory.setSizeThreshold(1024*1024);  
        //上传处理工具类（高水平API上传处理？）  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        
          
        try{  
            //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。  
            //Map<String, List<FileItem>> lists = upload.
        	Map<String, List<FileItem>> lists = upload.parseParameterMap(request);
//            file=[name=head.jpg, StoreLocation=null, size=3366 bytes, isFormField=false, FieldName=file]实例数据
//            Util.log(TAG,lists);
            List<FileItem> list = lists.get("image_file");
            for(FileItem item:list){  
                //获取表单属性名字。  
                String name = item.getFieldName();  
                //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。  
                if(item.isFormField()){  
                    //获取用户具体输入的字符串，  
                	
                	
                	
                	
                	
                	/**
            		 * 上传字符串信息不要用这个，不好用
            		 */
                    
                    
                }  
                //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。  
                else{   
                    //获取路径名  
                    String value = item.getName();  
                    //取到最后一个反斜杠。  
                    int start = value.lastIndexOf("\\");  
                    //截取上传文件的 字符串名字。+1是去掉反斜杠。  
                    String filename = value.substring(start+1); 
                    System.out.println("\n"+"上传图片名称："+filename);
                    request.setAttribute(name, filename);  
                    //获取后缀名称
                    String ext = item.getName().substring(item.getName().lastIndexOf("."), item.getName().length());
					//设置储存名称，防止图片重名
                    String storeName = ""+System.currentTimeMillis();
                    //将图片完整名称放到context
                    ServletContext context=request.getServletContext();
                    context.setAttribute("upPostImgName", storeName+ext);
                    /*直接写到文件夹中。 */
                    System.out.println("\n"+"上传图片路径："+path+"/"+storeName+ext);
                    //注意！这里在Webcontent/imgs下看不到，需要在真实路径下才能看到
					item.write(new File(path+"/"+storeName+ext));
                  
                }  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
		System.out.println("\n"+"上传图片完毕");
		
		
		
		
		
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
