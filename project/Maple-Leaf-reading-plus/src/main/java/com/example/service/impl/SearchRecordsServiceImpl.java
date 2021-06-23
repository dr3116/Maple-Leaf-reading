package com.example.service.impl;

import com.example.entity.SearchRecords;
import com.example.mapper.SearchRecordsMapper;
import com.example.service.SearchRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchRecordsServiceImpl implements SearchRecordsService {
    @Autowired
    SearchRecordsMapper searchRecordsMapper;

    @Override
    public List<SearchRecords> findAllSearchRecords() {
        return  searchRecordsMapper.findAllSearchRecords();
    }

    @Override
    public void insertSearchRecords( String searchHistory, int userId) {
        searchRecordsMapper.insertSearchRecords(searchHistory,userId);
    }

    @Override
    public void deleteSearchRecords(int searchId) {
        searchRecordsMapper.deleteSearchRecords(searchId);
    }

    @Override
    public SearchRecords findSearchRecords(int searchId) {
        return searchRecordsMapper.findSearchRecords(searchId);
    }
}
