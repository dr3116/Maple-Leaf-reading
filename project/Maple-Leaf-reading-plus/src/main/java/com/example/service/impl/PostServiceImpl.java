package com.example.service.impl;

import com.example.entity.Post;
import com.example.mapper.PostMapper;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostMapper postMapper;

    @Override
    public List<Post> findAllPosts() {
        return postMapper.findAllPosts();
    }

    @Override
    public void insertPost(String content, String photo, String bookName, int numberOfLikes, int comments, int userId, Date postTime) {
        postMapper.insertPost(content,photo,bookName,numberOfLikes,comments,userId,postTime);
    }

    @Override
    public void updatePost(int postId,String content, String photo, String bookName, int numberOfLikes, int comments, int userId, Date postTime) {
        postMapper.updatePost(postId,content,photo,bookName,numberOfLikes,comments,userId,postTime);
    }

    @Override
    public void deletePost(int postId) {
        postMapper.deletePost(postId);
    }

    @Override
    public Post findPostById(int postId) {
        return postMapper.findPostById(postId);
    }

    @Override
    public String findPostNumber() {
        return postMapper.findPostNumber();
    }
}
