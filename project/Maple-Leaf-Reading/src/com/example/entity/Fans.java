package com.example.entity;
//粉丝
public class Fans {
	private int userId;              //本人ID
	private int peopleConcernedId;   //被关注人ID
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
}
