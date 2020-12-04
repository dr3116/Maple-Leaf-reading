package com.example.entity;
//搜索
public class SearchRecords {
	private int searchId;            //搜索编号
	private String searchHistory;    //搜索历史
	private int userId;              //用户编号
	public int getSearchId() {
		return searchId;
	}
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	public String getSearchHistory() {
		return searchHistory;
	}
	public void setSearchHistory(String searchHistory) {
		this.searchHistory = searchHistory;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
