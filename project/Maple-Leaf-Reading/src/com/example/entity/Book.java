package com.example.entity;

import java.util.Date;

public class Book {
	private String bookName;              //书名
	private String classification;		  //分类
	private int readingVolume;            //阅读量
	private int numberOfChapters;         //章节数
	private Date releaseTime;             //发布时间
	private String bookPhoto;             //图片
	private double bookRating;            //书籍评分
	private String author;                //作者
	private int numberOfCollections;      //收藏数
	private String briefIntroduction;     //简介
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public int getReadingVolume() {
		return readingVolume;
	}
	public void setReadingVolume(int readingVolume) {
		this.readingVolume = readingVolume;
	}
	public int getNumberOfChapters() {
		return numberOfChapters;
	}
	public void setNumberOfChapters(int numberOfChapters) {
		this.numberOfChapters = numberOfChapters;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getBookPhoto() {
		return bookPhoto;
	}
	public void setBookPhoto(String bookPhoto) {
		this.bookPhoto = bookPhoto;
	}
	public double getBookRating() {
		return bookRating;
	}
	public void setBookRating(double bookRating) {
		this.bookRating = bookRating;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNumberOfCollections() {
		return numberOfCollections;
	}
	public void setNumberOfCollections(int numberOfCollections) {
		this.numberOfCollections = numberOfCollections;
	}
	public String getBriefIntroduction() {
		return briefIntroduction;
	}
	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}
}
