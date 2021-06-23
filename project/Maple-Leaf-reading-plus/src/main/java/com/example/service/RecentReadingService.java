package com.example.service;

import com.example.entity.RecentRead;
import com.example.entity.RecentReading;

import java.util.List;

public interface RecentReadingService {
    List<RecentReading> findAllRecentReading();
    List<RecentRead> findRecentReadingInfoByUserId(int userId);
}
