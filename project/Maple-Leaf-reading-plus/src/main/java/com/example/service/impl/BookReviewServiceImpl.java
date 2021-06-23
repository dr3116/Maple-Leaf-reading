package com.example.service.impl;


import com.example.entity.BookReview;
import com.example.mapper.BookReviewMapper;
import com.example.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookReviewServiceImpl implements BookReviewService {
    @Autowired
    BookReviewMapper bookReviewMapper;

    @Override
    public List<BookReview> findAllBookReviews() {
        return bookReviewMapper.findAllBookReviews();
    }

    @Override
    public void insertBookReview(String bookName, int userId, String content, double score) {
        bookReviewMapper.insertBookReview(bookName,userId,content,score);
    }

    @Override
    public void updateBookReview(int bookReviewId, String bookName, int userId, String content, double score) {
        bookReviewMapper.updateBookReview(bookReviewId,bookName,userId,content,score);
    }

    @Override
    public void deleteBookReviewById(int bookReviewId) {
        bookReviewMapper.deleteBookReviewById(bookReviewId);
    }

    @Override
    public BookReview findBookReviewById(int bookReviewId) {
        return bookReviewMapper.findBookReviewById(bookReviewId);
    }

    @Override
    public String getBookReviewNumber() {
        return  bookReviewMapper.getBookReviewNumber();
    }

    @Override
    public List<BookReview> findBookReviewByBookName(String bookName) {
        return bookReviewMapper.findBookReviewByBookName(bookName);
    }
}
