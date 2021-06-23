package com.example.frontcontroller.login_logout;

import com.example.entity.Student;
import com.example.service.BookService;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

;

/**
 * @author 异侠 2021-05-15
 */

@Slf4j
@RestController
public class AppLoginController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;






    @PostMapping("/APP/LoginService")
    public void LoginService(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) {
//        System.out.println("开始处理客户端登录请求");
        //获取客户端数据
        String phoneStr=(String)request.getParameter("phoneStr");
        String passwordStr=(String)request.getParameter("passwordStr");

//        System.out.println("开始前台登录验证"+phoneStr+"|||||||"+passwordStr);

        //连接数据库，开始登录验证
        String password=studentService.findStudentPasswordByAccount(phoneStr);

        //开始返回数据
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            if(password!=null && password.equals(passwordStr)){

                Student student=studentService.findStudentInfoByAccount(phoneStr);
                session.setAttribute("loginStudent",student);



                writer.write(student.getUserId()+"");
            }else{
                writer.write("error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }










}


