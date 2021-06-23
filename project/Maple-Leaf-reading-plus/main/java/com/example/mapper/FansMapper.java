package com.example.mapper;

import com.example.entity.Fans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FansMapper {
    List<Fans> findAllFans();
    Fans insertFans(@Param("userId") int userId,@Param("peopleConcernedId") int peopleConcernedId);
    void deleteFans(@Param("userId") int userId,@Param("peopleConcernedId") int peopleConcernedId);
}
