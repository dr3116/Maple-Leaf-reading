package com.example.entity;

import java.util.Date;

public class Student {
	private int userId;                         //用户ID
	private String userName;                    //用户名
	private String userPassword;                //用户密码
	private String userPhoto;                   //用户头像
	private Date accumulatedReadingTime;        //累计阅读时间
	private int continuousSignInDays;           //连续签到天数
	private int cumulativeSignInDays;           //累计签到天数
	private Date lastCheckInTime;               //上次签到时间
	private Date timeOfJoiningTheOrganization;  //加入组织时间
	private Date readingTimeOfThisMonth;        //本月阅读时间
	private Date readingTimeToday;              //今日阅读时间
	private Date lastReadingTime;               //上次阅读时间
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public Date getAccumulatedReadingTime() {
		return accumulatedReadingTime;
	}
	public void setAccumulatedReadingTime(Date accumulatedReadingTime) {
		this.accumulatedReadingTime = accumulatedReadingTime;
	}
	public int getContinuousSignInDays() {
		return continuousSignInDays;
	}
	public void setContinuousSignInDays(int continuousSignInDays) {
		this.continuousSignInDays = continuousSignInDays;
	}
	public int getCumulativeSignInDays() {
		return cumulativeSignInDays;
	}
	public void setCumulativeSignInDays(int cumulativeSignInDays) {
		this.cumulativeSignInDays = cumulativeSignInDays;
	}
	public Date getLastCheckInTime() {
		return lastCheckInTime;
	}
	public void setLastCheckInTime(Date lastCheckInTime) {
		this.lastCheckInTime = lastCheckInTime;
	}
	public Date getTimeOfJoiningTheOrganization() {
		return timeOfJoiningTheOrganization;
	}
	public void setTimeOfJoiningTheOrganization(Date timeOfJoiningTheOrganization) {
		this.timeOfJoiningTheOrganization = timeOfJoiningTheOrganization;
	}
	public Date getReadingTimeOfThisMonth() {
		return readingTimeOfThisMonth;
	}
	public void setReadingTimeOfThisMonth(Date readingTimeOfThisMonth) {
		this.readingTimeOfThisMonth = readingTimeOfThisMonth;
	}
	public Date getReadingTimeToday() {
		return readingTimeToday;
	}
	public void setReadingTimeToday(Date readingTimeToday) {
		this.readingTimeToday = readingTimeToday;
	}
	public Date getLastReadingTime() {
		return lastReadingTime;
	}
	public void setLastReadingTime(Date lastReadingTime) {
		this.lastReadingTime = lastReadingTime;
	}
}
