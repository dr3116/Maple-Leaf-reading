package com.example.mapper;

import com.example.entity.SearchRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SearchRecordsMapper {
    List<SearchRecords> findAllSearchRecords();
    void insertSearchRecords(@Param("searchHistory") String searchHistory, @Param("userId") int userId);
    void deleteSearchRecords(@Param("searchId") int searchId);
    SearchRecords findSearchRecords(@Param("searchId") int searchId);
}
