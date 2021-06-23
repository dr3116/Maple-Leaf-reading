package com.example.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 异侠 2021-05-05
 */




//有参无参构造器
@ToString
@Data
public class Master implements Serializable {
    private String account;
    private String password;
    private String state;

    public Master() {
    }

}
