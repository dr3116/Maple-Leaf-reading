package com.example.service.impl;

import com.example.entity.Fans;
import com.example.mapper.FansMapper;
import com.example.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class FansServiceImpl implements FansService {
    @Autowired
    FansMapper fansMapper;

    @Override
    public List<Fans> findAllFans() {
        return fansMapper.findAllFans();
    }

    @Override
    public void insertFans(int userId, int peopleConcernedId) {
        fansMapper.insertFans(userId,peopleConcernedId);
    }

    @Override
    public void deleteFans(int userId, int peopleConcernedId) {
        fansMapper.deleteFans(userId,peopleConcernedId);
    }

    @Override
    public Fans findFans(int userId, int peopleConcernedId) {
        return fansMapper.findFans(userId, peopleConcernedId);
    }
    @Override
    public List<Fans> findFansByUserID(int userId) {
        return fansMapper.findFansByUserID(userId);
    }

    @Override
    public List<Fans> findFansByUserIds(int userIds) {
        return fansMapper.findFansByUserIds(userIds);
    }
}
