package com.example.service.impl;

import com.example.entity.Master;
import com.example.mapper.MasterMapper;
import com.example.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    @Autowired
    MasterMapper masterMapper;

    @Override
    public Master findLoginUserInfo(String account) {
        return  masterMapper.findLoginUserInfo(account);
    }
}
