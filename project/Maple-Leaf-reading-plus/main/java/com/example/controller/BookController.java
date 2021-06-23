package com.example.backcontroller;

import com.example.entity.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @ResponseBody
    @GetMapping("/book")
    public List<Book> getBooks(){
        System.out.println(bookService.getAllBooks());
        return bookService.getAllBooks();
    }
    @ResponseBody
    @GetMapping("/bookCount")
    public String getBooksNumber(){
        System.out.println(bookService.getBooksCount());
        return bookService.getBooksCount()+"";
    }
    @ResponseBody
    @GetMapping("/deleteBook")
    public void deleteBook(@RequestParam("bookName") String bookName){
        bookService.deleteBook(bookName);
    }
}
