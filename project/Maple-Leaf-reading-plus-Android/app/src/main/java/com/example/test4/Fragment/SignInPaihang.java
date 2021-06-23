package com.example.test4.Fragment;

public class SignInPaihang {
    private String userName;		   //用户名
    private int signInDay;          //累计签到时长
    private String userPhoto;       //用户头像
    public SignInPaihang(String userName, int signInDay, String userPhoto) {
        this.userName = userName;
        this.signInDay = signInDay;
        this.userPhoto = userPhoto;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getSignInDay() {
        return signInDay;
    }
    public void setSignInDay(int signInDay) {
        this.signInDay = signInDay;
    }
    public String getUserPhoto() {
        return userPhoto;
    }
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}
