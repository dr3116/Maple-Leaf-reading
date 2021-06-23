package com.example.service;

import com.example.entity.SearchRecords;

import java.util.List;

public interface SearchRecordsService {
    List<SearchRecords> findAllSearchRecords();
    void insertSearchRecords(String searchHistory,int userId);
    void deleteSearchRecords(int searchId);
    SearchRecords findSearchRecords(int searchId);
}
