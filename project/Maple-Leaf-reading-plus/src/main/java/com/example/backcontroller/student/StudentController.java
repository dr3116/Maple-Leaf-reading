package com.example.backcontroller.student;


import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 异侠 2021-05-12
 */



@Controller
@RequestMapping("/Student")
public class StudentController {


    @Autowired
    private StudentService studentService;



//    private int userId;                         //用户ID
//    private String userName;                    //用户名
//    private String userPassword;                //用户密码
//    private String userPhoto;                   //用户头像
//    private int num;        //累计阅读时间
//    userSex    userStyleText





    @GetMapping(value ="/toStudentList")
    public String toStudentList(Model model,
                @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
//        System.out.println("开始查询所有学生信息");
        model.addAttribute("allStudentsInfo",studentService.findAllStudentByPages(pageNum,5));
        model.addAttribute("msg","开始展示所有的用户信息");
        return "student-list";
    }



    @GetMapping(value ="/searchStudent")
    public String searchStudent(Model model,
                                @RequestParam(defaultValue = "",value = "studentName") String name){
//        System.out.println("开始查询所有学生信息");

        model.addAttribute("someStudentsInfo",studentService.findStudentByName(name));
        List<Student> students=studentService.findAllStudents();
        return "student-list1";
    }



    @GetMapping(value ="/toEditStudent")
    public String toEditStudent( Model model,
                             @RequestParam(value="userId",defaultValue = "") String userId,
                             @RequestParam(value="userStyleText",defaultValue = "") String userStyleText,
                             @RequestParam(value="userSex",defaultValue = "") String userSex,
                             @RequestParam(value="userName",defaultValue = "") String userName,
                             @RequestParam(value="num",defaultValue = "") String num){
//        System.out.println("开始修改学生信息");
        model.addAttribute("userName",userName);
        model.addAttribute("userId",userId);
        model.addAttribute("num",num);
        model.addAttribute("userStyleText",userStyleText);
        model.addAttribute("userSex",userSex);

//        System.out.println("打印 编辑图书时候往页面传递的参数"+userId +"   "+ userName+"    "+num+"     "+userSex+"    "+userStyleText);

        return "student-edit";
    }

    @PostMapping("/editStudent")
    public String editStudent(@RequestParam("userName") String userName,
                           @RequestParam("num") int num,
                          @RequestParam(value="userStyleText",defaultValue = "") String userStyleText,
                          @RequestParam(value="userSex",defaultValue = "") String userSex,
                           @RequestParam("userId") int userId,
                           @RequestParam("imageFile") MultipartFile imageFile){




        String pathImage ="F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\images\\student";
        String imageOriginalFilename=System.currentTimeMillis()+".jpg";


        if(!imageFile.isEmpty()){
            //保存到文件服务器，OSS服务器
            imageOriginalFilename = imageFile.getOriginalFilename();
            try {
                imageFile.transferTo(new File(pathImage+"/"+imageOriginalFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int num1=studentService.updateStudent(userId,userName,imageOriginalFilename,num,userSex,userStyleText);
        if(num1==1){
            return "图书信息修改成功";
        }
        return "redirect:/Student/toStudentList";
    }





    @GetMapping("/deleteStudent")
    public String editBook(@RequestParam("userId") int userId) {

//        System.out.println("要删除的用户id为" + userId);
        studentService.deleteStudent(userId);
        return "redirect:/Student/toStudentList";
    }




    @GetMapping(value ="/toAddStudent")
    public String toAddStudent(Model model){
//        System.out.println("System.getProperty(ser.dir)="+System.getProperty("user.dir"));
//        System.out.println("ClassUtils.getDefaultClassLoader().getResource().getPath()=="+ ClassUtils.getDefaultClassLoader().getResource("").getPath());

        return "student-add";
    }


    @PostMapping("/addStudent")
    @ResponseBody
    public String addStudent(@RequestParam("userName") String userName,
                             @RequestParam(value="userStyleText") String userStyleText,
                             @RequestParam(value="userSex") String userSex,
                           @RequestParam("password") String password,
                           @RequestParam("imageFile") MultipartFile imageFile){

//        System.out.println("打印增加用户是的部分参数   "+userStyleText+"--"+userSex);
        // 先插入数据，获取id

        int num=studentService.insertStudent(0,userName,password,"linshi",0,userSex,userStyleText);
        String userId=studentService.findStudentIdByAccount(userName);




        String pathImage = "F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\images\\student";
//        String pathImage ="C:/Users/Administrator/Desktop/Maple-Leaf-Reading/images/student";
        String imageOriginalFilename=userId+".jpg";


        if(!imageFile.isEmpty()){
            try {
                imageFile.transferTo(new File(pathImage+"/"+imageOriginalFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        studentService.updateStudent(Integer.parseInt(userId),userName,userId+".jpg",0,userSex,userStyleText);
        return "用户信息增加成功";
    }




}
