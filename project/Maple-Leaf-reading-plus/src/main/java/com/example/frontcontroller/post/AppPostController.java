package com.example.frontcontroller.post;

import com.alibaba.fastjson.JSON;
import com.example.entity.Comments;
import com.example.entity.Fans;
import com.example.entity.PostComment;
import com.example.entity.Student;
import com.example.service.CommentsService;
import com.example.service.FansService;
import com.example.service.PostService;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author 异侠 2021-05-27
 */


@Slf4j
@RestController
public class AppPostController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PostService postService;
    @Autowired
    private FansService fansService;





    @PostMapping("/APP/GetPostComment")
    public void getPostComment(HttpServletResponse response,
                               @RequestParam("postId")String postId) {
        //编码格式
        response.setContentType("text/html;charset=utf-8");
        //
        List<Comments> commentss=commentsService.findCommentsByPostId(Integer.parseInt(postId));
        List<PostComment> postComments=new ArrayList<>();
        for(Comments comments:commentss){
            PostComment postComment=new PostComment();
            Student student=studentService.findStudentInfoById(comments.getCommenterId());
            postComment.setCommenterId(comments.getCommenterId());
            postComment.setCommenterName(student.getUserName());
            postComment.setCommentContent(comments.getContent());
            postComments.add(postComment);
        }

        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(postComments));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @PostMapping("/APP/UpLoadPostComment")
    public void upLoadPostComment(HttpServletResponse response,
                                  @RequestParam("posterId")String posterId,
                                  @RequestParam("postId")String postId,
                                  @RequestParam("postComment")String postComment) {
//        System.out.println("UploadPostComment："+postId+" "+postComment+" "+posterId);
        //编码格式
        response.setContentType("text/html;charset=utf-8");
        //连接数据库
        commentsService.insertComments(Integer.parseInt(postId),postComment,Integer.parseInt(posterId));
        //返回数据

        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString("error"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }











    @PostMapping("/APP/UpPostImgService/{postImg}")
    public void upPostImgService(HttpServletResponse response,
                                 HttpServletRequest request,
                                 @RequestParam("image_file") MultipartFile imageFile,
                                 @PathVariable("postImg")String postImg) {
        try {
            imageFile.transferTo(new File("F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\images\\post\\"+postImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
}











    @PostMapping("/APP/UpPosetInfo")
    public void upPosetInfo(HttpServletResponse response,
                            HttpServletRequest request,
                                  @RequestParam("posterId")String posterId,
                                  @RequestParam("input")String inputStr,
                                  @RequestParam("bookName")String bookNameStr,
                                    @RequestParam("postImg")String postImg) {
        //编码格式
        response.setContentType("text/html;charset=utf-8");

        Date postTime=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpleDateFormat.format(postTime);
        Date date=new Date() ;
        try {
            date=simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String upPostImgName=(String)request.getServletContext().getAttribute("upPostImgName");
//        System.out.println("\n"+"发帖者id："+posterId+"内容:"+inputStr);



        postService.insertPost(inputStr,postImg,bookNameStr,0,0,Integer.parseInt(posterId),date);







        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString("error"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







    @PostMapping("/APP/AddAttention")
    public void addAttention(HttpServletResponse response,
                            HttpServletRequest request,
                            @RequestParam("userId")String userId,
                            @RequestParam("posterId")String posterId) {
        //编码格式
        response.setContentType("text/html;charset=utf-8");

        Fans fans=fansService.findFans(Integer.parseInt(posterId),Integer.parseInt(userId));
        if(fans!=null){
            PrintWriter writer= null;
            try {
                writer = response.getWriter();
                writer.write(JSON.toJSONString("error"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            fansService.insertFans(Integer.parseInt(posterId),Integer.parseInt(userId));
        }

    }













}
