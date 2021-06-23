package com.example.entity;

public class PostComment {
    private int commenterId;
    private String commenterName;
    private String commentContent;

    public PostComment(int commenterId, String commenterName, String commentContent) {
        this.commenterId = commenterId;
        this.commenterName = commenterName;
        this.commentContent = commentContent;
    }

    public int getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(int commenterId) {
        this.commenterId = commenterId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public PostComment() {
    }
}
