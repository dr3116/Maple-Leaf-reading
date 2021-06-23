package com.example.test4;

public class BookCategory {
    //分类
    private String category;
    private String bookName;
    private String bookPhoto;
    private int readingVolume;
    private int chapterNum;
    private String introduction;

    public BookCategory(String category, String bookName, String bookPhoto, int readingVolume, int chapterNum, String introduction) {
        this.category = category;
        this.bookName = bookName;
        this.bookPhoto = bookPhoto;
        this.readingVolume = readingVolume;
        this.chapterNum = chapterNum;
        this.introduction = introduction;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPhoto() {
        return bookPhoto;
    }

    public void setBookPhoto(String bookPhoto) {
        this.bookPhoto = bookPhoto;
    }

    public int getReadingVolume() {
        return readingVolume;
    }

    public void setReadingVolume(int readingVolume) {
        this.readingVolume = readingVolume;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(int chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
