package com.example.service.impl;

import com.example.entity.RecentRead;
import com.example.entity.RecentReading;
import com.example.mapper.RecentReadingMapper;
import com.example.service.RecentReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecentReadingServiceImpl implements RecentReadingService {
    @Autowired
    RecentReadingMapper recentReadingMapper;

    @Override
    public List<RecentReading> findAllRecentReading() {
        return recentReadingMapper.findAllRecentReading();
    }

    @Override
    public List<RecentRead> findRecentReadingInfoByUserId(int userId) {
        return recentReadingMapper.findRecentReadingInfoByUserId(userId);
    }
}
