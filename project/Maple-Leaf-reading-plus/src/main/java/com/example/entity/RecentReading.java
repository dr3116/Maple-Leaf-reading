package com.example.entity;

import java.util.Date;

public class RecentReading {
    private int recentReadingId;     //最近阅读编号
    private int userId;              //用户编号
    private String bookName;         //书名
    private Date lastReadingTime;    //上次阅读时间
    public int getRecentReadingId() {
        return recentReadingId;
    }
    public void setRecentReadingId(int recentReadingId) {
        this.recentReadingId = recentReadingId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public Date getLastReadingTime() {
        return lastReadingTime;
    }
    public void setLastReadingTime(Date lastReadingTime) {
        this.lastReadingTime = lastReadingTime;
    }

    @Override
    public String toString() {
        return "RecentReading{" +
                "recentReadingId=" + recentReadingId +
                ", userId=" + userId +
                ", bookName='" + bookName + '\'' +
                ", lastReadingTime=" + lastReadingTime +
                '}';
    }
}
