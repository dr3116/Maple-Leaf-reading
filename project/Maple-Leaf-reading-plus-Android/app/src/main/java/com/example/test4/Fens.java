package com.example.test4;

/**
 * 杜然 12/6
 */
public class Fens {
    private int userId;              //id
    private String  userName;   //被关注人昵称
    private String userPhoto;   //被关注人头像
    public Fens(int userId,String userName,String userPhoto){
        this.userId = userId;
        this.userName = userName;
        this.userPhoto = userPhoto;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserPhoto(){
        return userPhoto;
    }
    public void setUserPhoto(String userPhoto){
        this.userPhoto = userPhoto;
    }
}
