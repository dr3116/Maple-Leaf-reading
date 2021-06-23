package com.example.entity;

public class Fans  {
    private int userId;              //本人ID
    private int peopleConcernedId;   //被关注人ID
    private int userIds;                         //用户ID
    private String userName;                    //用户名
    private String userPassword;                //用户密码
    private String userPhoto;                   //用户头像
    private int num;        //累计阅读时间
    private String userSex;
    private String userStyleText;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPeopleConcernedId() {
        return peopleConcernedId;
    }
    public void setPeopleConcernedId(int peopleConcernedId) {
        this.peopleConcernedId = peopleConcernedId;
    }

    public int getUserIds() {
        return userIds;
    }

    public void setUserIds(int userIds) {
        this.userIds = userIds;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserStyleText() {
        return userStyleText;
    }

    public void setUserStyleText(String userStyleText) {
        this.userStyleText = userStyleText;
    }

    @Override
    public String toString() {
        return "Fans{" +
                "userId=" + userId +
                ", peopleConcernedId=" + peopleConcernedId +
                ", userIds=" + userIds +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", num=" + num +
                ", userSex='" + userSex + '\'' +
                ", userStyleText='" + userStyleText + '\'' +
                '}';
    }
}
