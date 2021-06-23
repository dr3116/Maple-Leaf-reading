package com.example.mapper;

import com.example.entity.Classification;
import com.example.entity.myself_style.StrStrBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassificationMapper {
    List<Classification> findAllClassifications();
    void insertClassification(@Param("classification") String classification, @Param("bookName") String bookName, @Param("category") String category);
    void deleteClassification(@Param("classification") String classification, @Param("bookName") String bookName);
    Classification findClassification(@Param("classification") String classification, @Param("bookName") String bookName);
    String getClassificationNumber();
    List<StrStrBean> getClassificationNumberAndName();
}
