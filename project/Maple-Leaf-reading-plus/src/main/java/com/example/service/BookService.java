package com.example.service;

import com.example.entity.Book;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getAllBooksByNumberOfCollectionsDesc();
    List<Book> getAllBooksByNumberOfReadingVplumeDesc();
    int insertBook(String bookName, String classification, int readingVolume, int numberOfChapters, Date releaseTime,String bookPhoto,double bookRating,String author,int numberOfCollections,String briefIntroduction);
    int updateBook(String bookName, String classification, int readingVolume, int numberOfChapters, Date releaseTime,String bookPhoto,double bookRating,String author,int numberOfCollections,String briefIntroduction);
    int deleteBook(String bookName);
    String getBooksCount();
    List<Book> findBookByBookName(String bookName);
    Book findBookByOneBookName(String bookName);
    List<Book> getAllBooksByPageF(int pageNum,int pageSize);
    PageInfo<Book> getAllBooksByPages(int pageNum, int pageSize);
    PageInfo<Book> getAllBooksByPagesAndDynamic(int pageNum, int pageSize,String bookName,String author,String classification);
    PageInfo<Book> getAllBooksByPagesAndBookName(int pageNum, int pageSize,String bookName);
    List<Book> findBookByManyCondition(String bookName,String classification,String author);
}
