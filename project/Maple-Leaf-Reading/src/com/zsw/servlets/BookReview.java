package com.zsw.servlets;


public class BookReview {
	private int bookReviewId;            //�������
    private String bookName;             //����
    private int userId;                  //�û����
    private String content;              //����
    private double score;                //����
  
    public BookReview(int bookReviewId,String bookName,int userId,String content,double score) {
    	this.bookReviewId = bookReviewId;
    	this.bookName = bookName;
    	this.userId = userId;
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
}
