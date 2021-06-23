package com.example.service.impl;

import com.example.entity.Book;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.findAllBooks();
    }
    @Override
    public List<Book> getAllBooksByNumberOfCollectionsDesc() {
        return bookMapper.getAllBooksByNumberOfCollectionsDesc();
    }
    @Override
    public List<Book> getAllBooksByNumberOfReadingVplumeDesc() {
        return bookMapper.getAllBooksByNumberOfReadingVplumeDesc();
    }
    //getAllBooksByNumberOfReadingVplumeDesc
    @Override
    public String getBooksCount(){
        return bookMapper.getBooksNumber()+"";
    }
    @Override
    public int deleteBook(String bookName){
        bookMapper.deleteBook(bookName);
        return 0;
    }

    @Override
    public int insertBook(String bookName, String classification, int readingVolume, int numberOfChapters, Date releaseTime, String bookPhoto, double bookRating, String author, int numberOfCollections, String briefIntroduction) {
        return bookMapper.insertBook(bookName,classification,readingVolume,numberOfChapters,releaseTime,bookPhoto,bookRating,author,numberOfCollections,briefIntroduction);
    }

    @Override
    public int updateBook(String bookName, String classification, int readingVolume, int numberOfChapters, Date releaseTime, String bookPhoto, double bookRating, String author, int numberOfCollections, String briefIntroduction) {
        return bookMapper.updateBook(bookName,classification,readingVolume,numberOfChapters,releaseTime,bookPhoto,bookRating,author,numberOfCollections,briefIntroduction);
    }

    @Override
    public List<Book> findBookByBookName(String bookName) {
        return bookMapper.findBookByBookName(bookName);
    }
    @Override
    public Book findBookByOneBookName(String bookName) {
        return bookMapper.findBookByOneBookName(bookName);
    }

    @Override
    public List<Book> getAllBooksByPageF(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> lists = bookMapper.findAllBooks();
        return lists;
    }

    @Override
    public PageInfo<Book> getAllBooksByPages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Book> lists = bookMapper.findAllBooks();
        PageInfo<Book> pageInfo = new PageInfo<Book>(lists);
        return pageInfo;
    }


    @Override
    public PageInfo<Book> getAllBooksByPagesAndBookName(int pageNum, int pageSize,String bookName) {
        PageHelper.startPage(pageNum,pageSize);
        List<Book> lists =bookMapper.findBookByBookName(bookName);
        PageInfo<Book> pageInfo = new PageInfo<Book>(lists);
        return pageInfo;
    }




    //PageInfo<Book> getAllBooksByPagesAndDynamic(int pageNum, int pageSize,String bookName,String author,String classification);
    @Override
    public PageInfo<Book> getAllBooksByPagesAndDynamic(int pageNum, int pageSize,String bookName,String author,String classification) {
        PageHelper.startPage(pageNum,pageSize);
        List<Book> lists =bookMapper.findBookByManyCondition(bookName,classification,author);
        PageInfo<Book> pageInfo = new PageInfo<Book>(lists);
        return pageInfo;
    }





    @Override
    public List<Book> findBookByManyCondition(String bookName, String classification, String author) {
        return bookMapper.findBookByManyCondition(bookName,classification,author);
    }
}
