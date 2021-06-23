package com.example.service;

import com.example.entity.Master;


/**
 * @author 异侠 2021-05-05
 */
public interface MasterService {
    Master findLoginUserInfo(String account);
}

