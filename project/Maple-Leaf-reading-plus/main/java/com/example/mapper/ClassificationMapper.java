package com.example.mapper;

import com.example.entity.Classification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ClassificationMapper {
    List<Classification> findAllClassifications();
    Classification insertClassification(@Param("classification") String classification, @Param("bookName") String bookName, @Param("category") String category);
    void deleteClassification(@Param("classification") String classification, @Param("bookName") String bookName);
}
