package com.example.mapper;

import com.example.entity.BookShelf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BookShelfMapper {
    List<BookShelf> findAllBookShelf();
    void insertBookShelf(@Param("userId") int userId,@Param("bookName") String bookName);
    void deleteBookShelf(@Param("userId") int userId,@Param("bookName") String bookName);
    BookShelf findBookShelfByIdAndName(@Param("userId") int userId,@Param("bookName") String bookName);
    List<BookShelf> findBookShelfById(@Param("userId")int userId);
}
