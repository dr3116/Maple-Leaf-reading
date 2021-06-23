package com.example.service;

import com.example.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    String getBooksCount();
    void deleteBook(String bookName);
}
