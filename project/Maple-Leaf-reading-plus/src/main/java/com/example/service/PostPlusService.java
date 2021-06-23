package com.example.service;

import com.example.entity.PostPlus;

import java.util.List;

public interface PostPlusService {
    List<PostPlus> findAllPostPlus();
    List<PostPlus> findAllPostPlusByUserId(String id);
    //    Fans findFans(int userId, int peopleConcernedId);
}
