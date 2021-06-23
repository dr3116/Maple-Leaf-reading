package com.example.frontcontroller.book;

import com.alibaba.fastjson.JSON;
import com.example.entity.Book;
import com.example.entity.BookCategory;
import com.example.entity.BookShelf;
import com.example.entity.BookShelfBook;
import com.example.service.BookService;
import com.example.service.BookShelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 异侠 2021-05-26
 */



@Slf4j
@RestController
public class AppBookController {


    @Autowired
    private BookService bookService;
    @Autowired
    private BookShelfService bookShelfService;

    @PostMapping("/APP/GetOneBookInfo")
    public void GetOneBookInfo(HttpServletResponse response,
                               HttpServletRequest request,
                               @RequestParam("bookName") String bookName) {


        response.setContentType("text/html;charset=utf-8");
        Book book = bookService.findBookByOneBookName(bookName);
//        System.out.println("GetOneBookInfo:  "+book);


        List<Book> books = new ArrayList<>();
        books.add(book);

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(books));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/APP/GetBookCategory")
    public void getBookCategory(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        //连接数据库，开始登录验证
        List<BookCategory> bookCategories = new ArrayList<>();
        List<Book> books = bookService.getAllBooks();

        for (Book book : books) {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setCategory(book.getClassification());
            bookCategory.setBookName(book.getBookName());
            bookCategory.setBookPhoto(book.getBookPhoto());
            bookCategory.setReadingVolume(book.getReadingVolume());
            bookCategory.setIntroduction(book.getBriefIntroduction());
            bookCategory.setChapterNum(book.getNumberOfChapters());
            bookCategories.add(bookCategory);
        }
        //开始返回数据
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(bookCategories));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/APP/SetBookShelfList")
    public void setBookShelfList(HttpServletResponse response,
                                 @RequestParam("bbbookName") String bkName,
                                 @RequestParam("uuuserId") String uId) {


        response.setContentType("text/html;charset=utf-8");
        bookShelfService.insertBookShelf(Integer.parseInt(uId), bkName);
//
//        //开始返回数据
//        PrintWriter writer= null;
//        try {
//            writer = response.getWriter();
//            writer.write(JSON.toJSONString(bookCategories));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    @PostMapping("/APP/GetBookList")
    public void getBookList(HttpServletResponse response,
                            @RequestParam("classification") String bclassification) {

        response.setContentType("text/html;charset=utf-8");
        List<Book> books = bookService.findBookByManyCondition("", bclassification, "");
        //开始返回数据
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(books));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/APP/GetBookShelf")
    public void getBookShelf(HttpServletResponse response,
                             @RequestParam("userId") String userId) {

//        System.out.println("开始触发：GetBookShelf");


        response.setContentType("text/html;charset=utf-8");
        List<BookShelf> bookShelves = bookShelfService.findBookShelfById(Integer.parseInt(userId));
        List<BookShelfBook> bookShelfBooks = new ArrayList<>();
        for (BookShelf bookShelf : bookShelves) {
            Book book = bookService.findBookByOneBookName(bookShelf.getBookName());
            BookShelfBook bookShelfBook = new BookShelfBook();
            bookShelfBook.setBookImg(book.getBookPhoto());
            bookShelfBook.setBookName(book.getBookName());
            bookShelfBooks.add(bookShelfBook);
        }
        //开始返回数据
//        System.out.println("打印GetBookShelf返回数据:"+bookShelfBooks);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(bookShelfBooks));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







































    //web -》
    // 客户端下载图书
    @GetMapping(value = "APP/download/{bookName}")
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable("bookName") String bookName) throws Exception {





        Book book=bookService.findBookByOneBookName(bookName);
        String bookPhoto=book.getBookPhoto();
        String[] list=bookPhoto.split("\\.");



        File file = new File("F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\pdfs\\"+list[0]+".gz");
        System.out.println("最终app下载的图书的名字是:"+"F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\pdfs\\"+list[0]+".gz");

            response.setHeader("Content-Disposition", "attachment;filename=a.mp4");
//            String path = "/Users/g&c/Movies/17线性表12.mp4";

            long total = file.length();
            response.setContentLengthLong(total);
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[1024 * 8];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            fis.close();




    }




















}


