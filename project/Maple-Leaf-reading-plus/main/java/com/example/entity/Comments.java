package com.example.entity;

import java.util.Date;

public class Comments {
    private int commentsId;           //评论编号
    private int postId;               //帖子编号
    private String content;           //内容
    private int reviewersId;          //评论人编号
    private Date time;                //评论时间
    private int numberOfLikes;        //评论的点赞数

    public int getCommentsId() {
        return commentsId;
    }
    public void setCommentsId(int commentsId) {
        this.commentsId = commentsId;
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
    public int getReviewersId() {
        return reviewersId;
    }
    public void setReviewersId(int reviewersId) {
        this.reviewersId = reviewersId;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public int getNumberOfLikes() {
        return numberOfLikes;
    }
    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentsId=" + commentsId +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", reviewersId=" + reviewersId +
                ", time=" + time +
                ", numberOfLikes=" + numberOfLikes +
                '}';
    }
}
