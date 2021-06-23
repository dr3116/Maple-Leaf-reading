package com.example.service.impl;

import com.example.entity.Classification;
import com.example.entity.myself_style.StrStrBean;
import com.example.mapper.ClassificationMapper;
import com.example.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    ClassificationMapper classificationMapper;

    @Override
    public List<Classification> findAllClassifications() {
        return classificationMapper.findAllClassifications();
    }
    @Override
    public void insertClassification(String classification, String bookName, String category) {
        classificationMapper.insertClassification(classification,bookName,category);
    }

    @Override
    public void deleteClassification(String classification, String bookName) {
        classificationMapper.deleteClassification(classification,bookName);
    }

    @Override
    public Classification findClassification(String classification, String bookName) {
        return  classificationMapper.findClassification(classification, bookName);
    }

    @Override
    public String getClassificationNumber() {
        return  classificationMapper.getClassificationNumber();
    }

    @Override
    public     List<StrStrBean> getClassificationNumberAndName() {
//        System.out.println("classificationMapper.getClassificationNumberAndName()"+classificationMapper.getClassificationNumberAndName());
        return  classificationMapper.getClassificationNumberAndName();
    }
}
