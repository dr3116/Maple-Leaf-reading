package com.example.service.impl;

import com.example.entity.PostPlus;
import com.example.mapper.PostPlusMapper;
import com.example.service.PostPlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Administrator
 */
@Service
public class PostPlusServiceImpl implements PostPlusService {
    @Autowired
    PostPlusMapper postPlusMapper;
    @Override
    public List<PostPlus> findAllPostPlus() {
        return postPlusMapper.findAllPostPlus();
    }
    @Override
    public List<PostPlus> findAllPostPlusByUserId(String id) {
        return postPlusMapper.findAllPostPlusByUserId(id);
    }
}


