package com.example.service.impl;

import com.example.entity.BookShelf;
import com.example.mapper.BookShelfMapper;
import com.example.service.BookShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookShelfServiceImpl implements BookShelfService {
    @Autowired
    BookShelfMapper bookShelfMapper;

    @Override
    public List<BookShelf> findAllBookShelf() {
        return bookShelfMapper.findAllBookShelf();
    }

    @Override
    public void insertBookShelf(int userId, String bookName) {
        bookShelfMapper.insertBookShelf(userId,bookName);
    }

    @Override
    public void deleteBookShelf(int userId, String bookName) {
        bookShelfMapper.deleteBookShelf(userId,bookName);
    }

    @Override
    public BookShelf findBookShelfByIdAndName(int userId, String bookName) {
        return bookShelfMapper.findBookShelfByIdAndName(userId, bookName);
    }

    @Override
    public List<BookShelf> findBookShelfById(int userId) {
        return bookShelfMapper.findBookShelfById(userId);
    }
}
