package com.example.entity;

//书评
public class BookReview {
    private int bookReviewId;            //书评编号
    private String bookName;             //书名
    private int userId;                  //用户编号
    private String content;              //内容
    private double score;                //评分
    private String userName;                   //用户对象(只有名字)

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
    @Override
    public String toString() {
        return "BookReview{" +
                "bookReviewId=" + bookReviewId +
                ", bookName='" + bookName + '\'' +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", userName='" + userName + '\'' +
                '}';
    }
}
