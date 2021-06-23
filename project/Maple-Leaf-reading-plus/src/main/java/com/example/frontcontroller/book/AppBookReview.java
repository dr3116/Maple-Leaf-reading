package com.example.frontcontroller.book;

import com.alibaba.fastjson.JSON;
import com.example.entity.BookReview;
import com.example.entity.PostPlus;
import com.example.entity.Student;
import com.example.service.BookReviewService;
import com.example.service.BookShelfService;
import com.example.service.PostPlusService;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 异侠 2021-05-19
 */


@Slf4j
@RestController
public class AppBookReview {
    @Autowired
    private BookReviewService bookReviewService;
    @Autowired
    private BookShelfService bookShelfService;
    @Autowired
    private PostPlusService postPlusService;
    @Autowired
    private StudentService studentService;




    @PostMapping("/APP/GetBookReviewList")
    public void getBookReviewList(HttpServletResponse response,
                                  HttpServletRequest request,
                                  @RequestParam("bookName")String bookName) {
        response.setContentType("text/html;charset=utf-8");
    List<BookReview> bookReviews=bookReviewService.findBookReviewByBookName(bookName);
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(bookReviews));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @PostMapping("/APP/SetBookReviewList")
    public void setBookReviewList(HttpServletResponse response,
                                  HttpServletRequest request,
                                  @RequestParam("AddBookName")String bkName,
                                  @RequestParam("AddPingLun")String pingLun,
                                  @RequestParam("uuId")String uid) {

        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(uid);
        bookReviewService.insertBookReview(bkName,id,pingLun,5);

    }



    @GetMapping("/APP/GetCommentList")
    public void getCommentList(HttpServletResponse response,
                               HttpServletRequest request){
//                               @RequestParam(value = "AddBookName",defaultValue = "")String bkName,
//                               @RequestParam(value="AddPingLun")String pingLun,
//                               @RequestParam("uuId")String uid) {



        response.setContentType("text/html;charset=utf-8");


//        int id = Integer.parseInt(uid);
        List<PostPlus> postPluses=postPlusService.findAllPostPlus();

        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(postPluses));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @PostMapping("/APP/UpSignDateNum")
    public void upSignDateNum(HttpServletResponse response,
                               @RequestParam("signerId")String id) {

        Student student=studentService.findStudentInfoById(Integer.parseInt(id));
        int num=student.getNum()+1;
        studentService.updateStudent(Integer.parseInt(id), student.getUserName(),student.getUserPhoto(),num,student.getUserSex(),student.getUserStyleText());
    }




    @PostMapping("/APP/DeleteBookShelfList")
    public void deleteBookShelfList(HttpServletResponse response,
                                    @RequestParam("bookkName")String bookName,
                                    @RequestParam("uuserId")String uid ){

        bookShelfService.deleteBookShelf(Integer.parseInt(uid),bookName);
    }



//
//    @GetMapping("/images/{str1}/{str2}")
//    public void images(HttpServletResponse response,
//                       @PathVariable("str1")String str1,
//                       @PathVariable("str2")String str2){
//
//        System.out.println("images////请求开始启动");
//        File file=new File("C:\\Users\\Administrator\\Desktop\\Maple-Leaf-Reading\\images\\"+str1+"\\"+str2);
//
//
//        PrintWriter writer= null;
//        try {
//            writer = response.getWriter(file.toString().getBytes());
//            writer.write(JSON.toJSONString(""));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }









}



