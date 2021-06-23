package com.example.mapper;

import com.example.entity.BookReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BookReviewMapper {
    List<BookReview> findAllBookReviews();
    void insertBookReview(@Param("bookName") String bookName, @Param("userId") int userId, @Param("content") String content, @Param("score") double score);
    void updateBookReview(@Param("bookReviewId") int bookReviewId, @Param("bookName") String bookName,@Param("userId") int userId, @Param("content") String content, @Param("score") double score);
    void deleteBookReviewById(@Param("bookReviewId")int bookReviewId);
    BookReview findBookReviewById(@Param("bookReviewId")int bookReviewId);
    String getBookReviewNumber();
    List<BookReview> findBookReviewByBookName(@Param("bookName")String bookName);

}
