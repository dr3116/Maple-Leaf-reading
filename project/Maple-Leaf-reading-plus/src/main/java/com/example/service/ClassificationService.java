package com.example.service;

import com.example.entity.Classification;
import com.example.entity.myself_style.StrStrBean;

import java.util.List;

public interface ClassificationService {
    List<Classification> findAllClassifications();
    void insertClassification(String classification, String bookName, String category);
    void deleteClassification(String classification, String bookName);
    Classification findClassification(String classification, String bookName);
    String getClassificationNumber();


    List<StrStrBean> getClassificationNumberAndName();
}