package com.zsw.servlets;

public class Collection {
    private int collectId;
    private int userId;
    private int postId;

    public Collection(int collectId, int userId, int postId) {
        this.collectId = collectId;
        this.userId = userId;
        this.postId = postId;
    }

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
