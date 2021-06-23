package com.example.service.impl;

import com.example.entity.Comments;
import com.example.mapper.CommentsMapper;
import com.example.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsMapper commentsMapper;
    @Override
    public void insertComments(int postId, String content, int commenterId) {
        commentsMapper.insertComments(postId,content,commenterId);
    }

    @Override
    public void deleteComments(int commentsId) {
        commentsMapper.deleteComments(commentsId);
    }

    @Override
    public Comments findCommentsById(int commentsId) {
        return commentsMapper.findCommentsById(commentsId);
    }

    @Override
    public String getCommentsNumber() {
        return commentsMapper.getCommentsNumber();
    }

    @Override
    public List<Comments> findCommentsByPostId(int postId) {
        return commentsMapper.findCommentsByPostId(postId);
    }
}
