package com.example.backcontroller.center;

import com.example.entity.Master;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * @author 异侠 2021-05-05
 */


@Controller
public class CenterController {

    @Autowired
    private BookService bookservice;
    @Autowired
    private BookReviewService bookReviewService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private PostService postService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CommentsService commentsService;





    @GetMapping("/toWelcome")
    public String toWelcome(HttpSession session) {

        Master master=(Master)session.getAttribute("loginMaster");


        //图书
        String booksCount=bookservice.getBooksCount();
        session.setAttribute("booksCount",booksCount);
        //科目
        String bookSpeciesCount=classificationService.getClassificationNumber();
        session.setAttribute("bookSpeciesCount",bookSpeciesCount);
        //评论
        String bookReviewsCount = bookReviewService.getBookReviewNumber();
        session.setAttribute("bookReviewsCount",bookReviewsCount);
        //用户
        String studentsCount=studentService.findStudentNumber();
        session.setAttribute("studentsCount",studentsCount);
        //朋友圈
        String postsCount=postService.findPostNumber();
        session.setAttribute("postsCount",postsCount);
        //评论数
        String commentsCount=commentsService.getCommentsNumber();
        session.setAttribute("commentsCount",commentsCount);




        Random random1 = new Random(1);
        Random random2 = new Random(5);
        Random random3 = new Random(17);
        Random random4 = new Random(20);
        Random random5 = new Random();
            for(int j=0;j<7;++j){
                session.setAttribute("num0"+j,random1.nextInt(1000)+"");
                session.setAttribute("num1"+j,random2.nextInt(1000)+"");
                session.setAttribute("num2"+j,random3.nextInt(1000)+"");
                session.setAttribute("num3"+j,random4.nextInt(1000)+"");
                session.setAttribute("num4"+j,random5.nextInt(1000)+"");
            }





        return "welcome";
    }



    @GetMapping("/zhexian")
    public String zheXian(HttpSession session){
        Random random1 = new Random(1);
        Random random2 = new Random(5);
        Random random3 = new Random(17);
        Random random4 = new Random(20);
        Random random5 = new Random();
        for(int j=0;j<7;++j){
            session.setAttribute("num0"+j,random1.nextInt(1000)+"");
            session.setAttribute("num1"+j,random2.nextInt(1000)+"");
            session.setAttribute("num2"+j,random3.nextInt(1000)+"");
            session.setAttribute("num3"+j,random4.nextInt(1000)+"");
            session.setAttribute("num4"+j,random5.nextInt(1000)+"");
        }

        return "echarts-zhexian";

    }


}
