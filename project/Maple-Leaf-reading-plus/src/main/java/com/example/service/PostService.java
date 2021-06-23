package com.example.service;

import com.example.entity.Post;

import java.util.Date;
import java.util.List;

public interface PostService {
    List<Post> findAllPosts();
    void insertPost(String content,String photo,String bookName,int numberOfLikes,int comments,int userId,Date postTime);
    void updatePost(int postId,String content,String photo,String bookName,int numberOfLikes,int comments,int userId,Date postTime);
    void deletePost(int postId);
    Post findPostById(int postId);
    String findPostNumber();
}
