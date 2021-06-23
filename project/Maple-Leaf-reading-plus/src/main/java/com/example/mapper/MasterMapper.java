package com.example.mapper;

import com.example.entity.Master;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MasterMapper {
    Master findLoginUserInfo(@Param("account")String account);
}