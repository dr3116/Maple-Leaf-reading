package com.example.test4;

public class BookReview {
    private int bookReviewId;            //书评编号
    private String bookName;             //书名
    private int userId;                  //用户编号
    private String content;              //内容
    private double score;                //评分
    private String userName;
    public BookReview(int bookReviewId, String bookName, int userId, String userName, String content, double score) {
        this.bookReviewId = bookReviewId;
        this.bookName = bookName;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.score = score;
    }

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
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
