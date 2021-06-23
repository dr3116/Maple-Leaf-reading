package com.example.mapper;

import com.example.entity.PostPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PostPlusMapper {
    List<PostPlus> findAllPostPlus();
    List<PostPlus> findAllPostPlusByUserId(@Param("id")String id);
}
