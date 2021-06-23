package com.example.test4;


public class RecentRead {
    private int userId;              //用户编号
    private String bookName;         //书名
    private int readingVolume;            //阅读量
    private int numberOfChapters;         //章节数
    private String classification;		  //分类
    private String releaseTime;           //发布时间
    private String bookPhoto;             //图片
    private double bookRating;            //书籍评分
    private String author;                //作者
    private int numberOfCollections;      //收藏数
    private String briefIntroduction;     //简介
    private String lastReadingTime;    //上次阅读时间
    public RecentRead(int userId, String bookName, int readingVolume, int numberOfChapters, String classification, String releaseTime, String bookPhoto, double bookRating, String author, int numberOfCollections, String briefIntroduction, String lastReadingTime){
        this.userId = userId;
        this.bookName = bookName;
        this.readingVolume = readingVolume;
        this.numberOfChapters = numberOfChapters;
        this.numberOfCollections = numberOfCollections;
        this.classification = classification;
        this.releaseTime = releaseTime;
        this.bookPhoto = bookPhoto;
        this.bookRating = bookRating;
        this.author = author;
        this.briefIntroduction = briefIntroduction;
        this.lastReadingTime = lastReadingTime;
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
    public String getLastReadingTime() {
        return lastReadingTime;
    }
    public void setLastReadingTime(String lastReadingTime) {
        this.lastReadingTime = lastReadingTime;
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
