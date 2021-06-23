package com.example.entity;

import java.util.Date;

//帖子
public class Post {
    private int postId;                 //帖子编
    private String content;             //内容
    private String photo;               //照片
    private String bookName;            //书名
    private int numberOfLikes;          //点赞数
    private int comments;               //评论数
    private int userId;                 //用户ID
    private Date postTime;              //发帖时间
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
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
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
    public Date getPostTime() {
        return postTime;
    }
    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", content='" + content + '\'' +
                ", photo='" + photo + '\'' +
                ", bookName='" + bookName + '\'' +
                ", numberOfLikes=" + numberOfLikes +
                ", comments=" + comments +
                ", userId=" + userId +
                ", postTime=" + postTime +
                '}';
    }
}
