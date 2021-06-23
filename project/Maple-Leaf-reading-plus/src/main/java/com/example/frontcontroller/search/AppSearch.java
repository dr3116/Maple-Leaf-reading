package com.example.frontcontroller.search;

import com.alibaba.fastjson.JSON;
import com.example.entity.Book;
import com.example.entity.SearchRecords;
import com.example.service.BookService;
import com.example.service.SearchRecordsService;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 异侠 2021-05-21
 */
@Slf4j
@RestController
public class AppSearch {
    @Autowired
    private SearchRecordsService searchRecordsService;
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentService studentService;



    @GetMapping("/APP/GetHortSearch")
    public void GetHortSearch(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        List<SearchRecords> searchRecordss = searchRecordsService.findAllSearchRecords();


        String tempStr="";
        for(SearchRecords searchRecord:searchRecordss){
            tempStr=tempStr+"&&"+searchRecord.getSearchHistory();
        }

        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(tempStr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/APP/GetSearchList")
    public void GetSearchList(HttpServletResponse response,
                              HttpServletRequest request,
                              HttpSession session,
                              @RequestParam("search")String searchName) {

        response.setContentType("text/html;charset=utf-8");
        //查书
        List<Book> books=bookService.findBookByBookName(searchName);
//        System.out.println("GetSearchList:"+books.toString());

        searchRecordsService.insertSearchRecords(searchName,2);




        PrintWriter writer= null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(books));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
