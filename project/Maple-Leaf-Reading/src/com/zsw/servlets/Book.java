package com.zsw.servlets;


public class Book {
	private String bookName;              //书名
	private String classification;		  //分类
	private int readingVolume;            //阅读�?
	private int numberOfChapters;         //章节�?
	private String releaseTime;             //发布时间
	private String bookPhoto;             //图片
	private double bookRating;            //书籍评分
	private String author;                //作�??
	private int numberOfCollections;      //收藏�?
	private String briefIntroduction;     //�?�?
	public Book(String bookName, String classification, int readingVolume, int numberOfChapters, String releaseTime, String bookPhoto, double bookRating, String author, int numberOfCollections, String briefIntroduction){
        this.bookName = bookName;
        this.classification = classification;
        this.readingVolume = readingVolume;
        this.numberOfChapters = numberOfChapters;
        this.releaseTime = releaseTime;
        this.bookPhoto = bookPhoto;
        this.bookRating = bookRating;
        this.author = author;
        this.numberOfCollections = numberOfCollections;
        this.briefIntroduction = briefIntroduction;
    }
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
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
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
