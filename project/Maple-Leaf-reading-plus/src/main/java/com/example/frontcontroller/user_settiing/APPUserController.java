package com.example.frontcontroller.user_settiing;

import com.alibaba.fastjson.JSON;
import com.example.entity.*;
import com.example.entity.myself_style.Fens;
import com.example.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 异侠 2021-05-28
 */



@Slf4j
@RestController
public class APPUserController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private PostPlusService postPlusService;
    @Autowired
    private FansService fansService;
    @Autowired
    private RecentReadingService recentReadingService;


    @PostMapping("/APP/GetUserInfo")
    public void getUserInfo(HttpServletResponse response,
                            HttpServletRequest request,
                            @RequestParam("userId") String userIdStr) {
        response.setContentType("text/html;charset=utf-8");
        int userId = Integer.valueOf(userIdStr);

        Student student = studentService.findStudentInfoById(userId);


        String message = "";
        if (student == null) {
            message = "数据库无数据";
        } else {
            message = student.getUserSex() + "&&" + student.getUserStyleText() + "&&" + student.getUserName();
        }


        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @PostMapping("/APP/UpUserInfoChangeService")
    public void UpUserInfoChangeService(HttpServletResponse response,
                                        HttpServletRequest request,
                                        @RequestParam("userId") String userId,
                                        @RequestParam("userName") String userName,
                                        @RequestParam("userSex") String userSex,
                                        @RequestParam("userStyleText") String userStyleText) {
        response.setContentType("text/html;charset=utf-8");
//        System.out.println("UpPost");

        Student student = studentService.findStudentInfoById(Integer.parseInt(userId));
        student.setUserName(userName);
        student.setUserSex(userSex);
        student.setUserStyleText(userStyleText);
        studentService.updateStudent(student.getUserId(), student.getUserName(), student.getUserPhoto(), student.getNum(), student.getUserSex(), student.getUserStyleText());
    }


    /**
     * 我的界面，榜单，第一条榜单
     */
    @GetMapping("/APP/GetSignInPaihangList")
    public void getSignInPaihangList(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");

        List<SignInPaihang> signInPaiHangs = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        students = studentService.findAllStudentsBySignNumDesc();
        for (Student student : students) {
            SignInPaihang signInPaihang = new SignInPaihang();
            signInPaihang.setUserName(student.getUserName());
            signInPaihang.setSignInDay(student.getNum());
            signInPaihang.setUserPhoto(student.getUserPhoto());
            signInPaiHangs.add(signInPaihang);
        }


        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(signInPaiHangs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/APP/GetCollectionPaihangList")
    public void getCollectionPaihangList(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");


        List<CollectionPaihang> collectionPaihangs = new ArrayList<>();

        List<Book> books = new ArrayList<>();
        books = bookService.getAllBooksByNumberOfCollectionsDesc();
        for (Book book : books) {
            CollectionPaihang collectionPaihang = new CollectionPaihang();
            collectionPaihang.setBookName(book.getBookName());
            collectionPaihang.setNumberOfCollections(book.getNumberOfCollections());
            collectionPaihang.setBookPhoto(book.getBookPhoto());
            collectionPaihangs.add(collectionPaihang);
        }


        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(collectionPaihangs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @GetMapping("/APP/GetReadingVolumePaihangList")
    public void getReadingVolumePaihangList(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");



        List<ReadingVolumePaihang> readingVolumePaihangs = new ArrayList<>();

        List<Book> books = new ArrayList<>();
        books = bookService.getAllBooksByNumberOfReadingVplumeDesc();
        for (Book book : books) {
            ReadingVolumePaihang readingVolumePaihang=new ReadingVolumePaihang();
            readingVolumePaihang.setBookName(book.getBookName());
            readingVolumePaihang.setReadingVolume(book.getReadingVolume());
            readingVolumePaihang.setBookPhoto(book.getBookPhoto());
            readingVolumePaihangs.add(readingVolumePaihang);
        }


        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(readingVolumePaihangs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @PostMapping("/APP/GetRecentReadList")
    public void getRecentReadList(HttpServletResponse response,
                                  HttpServletRequest request,
                                  @RequestParam("userId") String uId){


        List<RecentRead> recentReads = new ArrayList<>();
        recentReads=recentReadingService.findRecentReadingInfoByUserId(Integer.parseInt(uId));




        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(recentReads));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    @PostMapping("/APP/GetMyPost")
    public void getMyPost(HttpServletResponse response,
                          @RequestParam("userId") String id) {
        response.setContentType("text/html;charset=utf-8");


        List<PostPlus> postPluss=new ArrayList<>();
        postPluss=postPlusService.findAllPostPlusByUserId(id);


        System.out.println("--------------------");
        for(PostPlus postPlus:postPluss){
            System.out.println(postPlus.toString());
        }


        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(postPluss));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @PostMapping("/APP/FensService")
    public void fensService(HttpServletResponse response,
                          @RequestParam("fensId") String userId) {
        response.setContentType("text/html;charset=utf-8");


        List<Fens> fens = new ArrayList<>();
        List<Fans> fans=fansService.findFansByUserIds(Integer.parseInt(userId));

        for(Fans fan:fans){
            Fens fen=new Fens();
            fen.setUserId(fan.getUserId());
            fen.setUserName(fan.getUserName());
            fen.setUserPhoto(fan.getUserPhoto());
            fens.add(fen);
        }




        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(fens));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @PostMapping("/APP/UpUserPhotoChangeService")
    public void upUserPhotoChangeService(HttpServletResponse response,
                                         @RequestParam("image_file") MultipartFile imageFile) {
        response.setContentType("text/html;charset=utf-8");

        String name=imageFile.getOriginalFilename();

        File old=new File("F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\images\\student\\"+name);
        if(old.exists()){
            old.delete();
        }

        try {
            imageFile.transferTo(new File("F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\images\\student\\"+name));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }



//    @PostMapping("/APP/UpPostImgService/{postImg}")
//    public void upPostImgService(HttpServletResponse response,
//                                 HttpServletRequest request,
//                                 @RequestParam("image_file") MultipartFile imageFile,
//                                 @PathVariable("postImg")String postImg) {
//        try {
//            imageFile.transferTo(new File("F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\images\\post\\"+postImg));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



}


