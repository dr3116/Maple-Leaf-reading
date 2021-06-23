package com.example.entity;

import java.util.Date;
//书评
public class BookReview {
    private int bookReviewId;            //书评编号
    private String bookName;             //书名
    private int userId;                  //用户编号
    private String content;              //内容
    private double score;                //评分
    private Date bookReviewTime;         //书评时间

    public int getBookReviewId() {
        return bookReviewId;
    }
    public void setBookReviewId(int bookReviewId) {
        this.bookReviewId = bookReviewId;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public Date getBookReviewTime() {
        return bookReviewTime;
    }
    public void setBookReviewTime(Date bookReviewTime) {
        this.bookReviewTime = bookReviewTime;
    }

    @Override
    public String toString() {
        return "BookReview{" +
                "bookReviewId=" + bookReviewId +
                ", bookName='" + bookName + '\'' +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", bookReviewTime=" + bookReviewTime +
                '}';
    }
}
