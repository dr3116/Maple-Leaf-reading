package com.example.entity;


public class PostPlus{
    private int postId;
    private String content;
    private String photo;
    private String postTime;
    private String bookImg;
    private String bookName;
    private String bookAuthor;
    private int numberOfLikes;
    private int comments;
    private int userId;
    private String userName;

    public PostPlus(int postId, String content, String photo, String postTime, String bookImg, String bookName, String bookAuthor, int numberOfLikes, int comments, int userId, String userName) {
        this.postId = postId;
        this.content = content;
        this.photo = photo;
        this.postTime = postTime;
        this.bookImg = bookImg;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.numberOfLikes = numberOfLikes;
        this.comments = comments;
        this.userId = userId;
        this.userName = userName;
    }

    public PostPlus() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "PostPlus{" +
                "postId=" + postId +
                ", content='" + content + '\'' +
                ", photo='" + photo + '\'' +
                ", postTime='" + postTime + '\'' +
                ", bookImg='" + bookImg + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", numberOfLikes=" + numberOfLikes +
                ", comments=" + comments +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
