package com.zsw.servlets;

public class ReadingVolumePaihang {
	private String bookName;
    private int readingVolume;
    private String bookPhoto;
    public ReadingVolumePaihang(String bookName,int readingVolume,String bookPhoto) {
        this.bookName = bookName;
        this.readingVolume = readingVolume;
        this.bookPhoto = bookPhoto;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public int getReadingVolume() {
        return readingVolume;
    }
    public void setReadingVolume(int readingVolume) {
        this.readingVolume = readingVolume;
    }

    public void setBookPhoto(String bookPhoto) {
        this.bookPhoto = bookPhoto;
    }

    public String getBookPhoto() {
        return bookPhoto;
    }
}
