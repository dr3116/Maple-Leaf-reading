package com.example.mapper;

import com.example.entity.RecentRead;
import com.example.entity.RecentReading;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RecentReadingMapper {
    List<RecentReading> findAllRecentReading();
    List<RecentRead> findRecentReadingInfoByUserId(@Param("userId")int userId);
}
