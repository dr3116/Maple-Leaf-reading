package com.example.mapper;

import com.example.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> findAllBooks();
    Book insertBook(@Param("bookName") String bookName, @Param("classification") String classification, @Param("readingVolume") int readingVolume, @Param("numberOfChapters") int numberOfChapters, @Param("releaseTime") Date releaseTime, @Param("bookPhoto") String bookPhoto, @Param("bookRating") double bookRating, @Param("author") String author, @Param("numberOfCollections") int numberOfCollections,@Param("briefIntroduction") String briefIntroduction);
    Book updateBook(@Param("bookName") String bookName, @Param("classification") String classification, @Param("readingVolume") int readingVolume, @Param("numberOfChapters") int numberOfChapters, @Param("releaseTime") Date releaseTime, @Param("bookPhoto") String bookPhoto, @Param("bookRating") double bookRating, @Param("author") String author, @Param("numberOfCollections") int numberOfCollections,@Param("briefIntroduction") String briefIntroduction);
    void deleteBook(@Param("bookName") String bookName);
    String getBooksNumber();
}
