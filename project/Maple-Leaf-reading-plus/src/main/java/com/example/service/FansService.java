package com.example.service;

import com.example.entity.Fans;

import java.util.List;

public interface FansService {
    List<Fans> findAllFans();
    void insertFans(int userId,int peopleConcernedId);
    void deleteFans(int userId,int peopleConcernedId);
    Fans findFans(int userId,int peopleConcernedId);
    List<Fans> findFansByUserID(int userId);
    List<Fans> findFansByUserIds(int userIds);
}
