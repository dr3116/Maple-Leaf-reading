package com.example.mapper;

import com.example.entity.BookReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface BookReviewMapper {
    List<BookReview> findAllBookReviews();
    BookReview insertBookReview(@Param("bookReviewId") int bookReviewId, @Param("bookName") String bookName,@Param("userId") int userId, @Param("content") String content, @Param("score") double score,@Param("bookReviewTime") Date bookReviewTime);
    BookReview updateBookReview(@Param("bookReviewId") int bookReviewId, @Param("bookName") String bookName,@Param("userId") int userId, @Param("content") String content, @Param("score") double score,@Param("bookReviewTime") Date bookReviewTime);
    void deleteBookReviewById(@Param("bookReviewId")int bookReviewId);
}
