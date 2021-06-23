package com.example.mapper;

import com.example.entity.RecentReading;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RecentReadingMapper {
    List<RecentReading> findAllRecentReading();
}
