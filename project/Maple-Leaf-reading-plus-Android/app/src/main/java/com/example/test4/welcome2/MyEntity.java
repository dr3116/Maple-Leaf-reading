package com.example.test4.welcome2;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyEntity implements CustomTabEntity {
    private int select;
    private int unSelect;
    private String title;
    private int img;

    public MyEntity(int select, int nuSelect, String title,int img1) {
        this.select = select;
        this.unSelect = nuSelect;
        this.title = title;
        this.img=img1;
    }

    //继承工具类
    @Override
    public String getTabTitle() {
        return title;
    }

    @Override//点击
    public int getTabSelectedIcon() {
        return select;
    }

    @Override//未点击
    public int getTabUnselectedIcon() {
        return unSelect;
    }
}
