package com.example.service;

import com.example.entity.Comments;

import java.util.List;

public interface CommentsService {
    void insertComments(int postId,String content,int commenterId);
    void deleteComments(int commentsId);
    Comments findCommentsById(int commentsId);
    String getCommentsNumber();
    List<Comments> findCommentsByPostId(int postId);
}
