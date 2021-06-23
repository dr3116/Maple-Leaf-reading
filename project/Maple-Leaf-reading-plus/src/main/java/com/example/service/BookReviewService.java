package com.example.service;

import com.example.entity.BookReview;

import java.util.List;
public interface BookReviewService {
    List<BookReview> findAllBookReviews();
    void insertBookReview(String bookName, int userId, String content, double score);
    void updateBookReview(int bookReviewId, String bookName, int userId, String content, double score);
    void deleteBookReviewById(int bookReviewId);
    BookReview findBookReviewById(int bookReviewId);
    String getBookReviewNumber();
    List<BookReview> findBookReviewByBookName(String bookName);
}
