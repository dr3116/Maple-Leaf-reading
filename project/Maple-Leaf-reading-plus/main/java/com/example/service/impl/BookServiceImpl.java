package com.example.service.impl;

import com.example.entity.Book;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.findAllBooks();
    }
    @Override
    public String getBooksCount(){
        return bookMapper.getBooksNumber()+"";
    }
    @Override
    public void deleteBook(String bookName){
        bookMapper.deleteBook(bookName);
    }
}
