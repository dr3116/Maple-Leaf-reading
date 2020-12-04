package com.zsw.servlets;

public class BookShelfBook {
    private String bookName;
    private String bookImg;

    public BookShelfBook(String bookName, String bookImg) {
        this.bookName = bookName;
        this.bookImg = bookImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }
}
