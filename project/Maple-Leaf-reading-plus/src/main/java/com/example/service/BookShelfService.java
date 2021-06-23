package com.example.service;

import com.example.entity.BookShelf;

import java.util.List;

public interface BookShelfService {
    List<BookShelf> findAllBookShelf();
    void insertBookShelf(int userId,String bookName);
    void deleteBookShelf(int userId,String bookName);
    BookShelf findBookShelfByIdAndName(int userId,String bookName);
    List<BookShelf> findBookShelfById(int userId);
}
