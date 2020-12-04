package com.example.entity;
//书架
public class BookShelf {
	private int userId;        //用户名
	private String bookName;   //书名
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
}
