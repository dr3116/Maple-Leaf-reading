package com.example.mapper;

import com.example.entity.Fans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FansMapper {
    List<Fans> findAllFans();
    void insertFans(@Param("userId") int userId,@Param("peopleConcernedId") int peopleConcernedId);
    void deleteFans(@Param("userId") int userId,@Param("peopleConcernedId") int peopleConcernedId);
    Fans findFans(@Param("userId") int userId,@Param("peopleConcernedId") int peopleConcernedId);
    List<Fans> findFansByUserID(@Param("userId") int userId);
    List<Fans> findFansByUserIds(@Param("userIds") int userIds);
}
