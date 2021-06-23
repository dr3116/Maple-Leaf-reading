package com.example.entity;

public class Comments  {
    private int commentsId;           //评论编号
    private int postId;               //帖子编号
    private String content;           //内容
    private int commenterId;          //评论人编号

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

    public int getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(int commenterId) {
        this.commenterId = commenterId;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentsId=" + commentsId +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", commenterId=" + commenterId +
                '}';
    }


    public Comments() {
    }

    public Comments(int commentsId, int postId, String content, int commenterId) {
        this.commentsId = commentsId;
        this.postId = postId;
        this.content = content;
        this.commenterId = commenterId;
    }
}
