package com.example.test4.Fragment;

public class CollectionPaihang {
    private String bookName;
    private int numberOfCollections;
    private String bookPhoto;
    public CollectionPaihang(String bookName, int numberOfCollections, String bookPhoto) {
        this.bookName = bookName;
        this.numberOfCollections = numberOfCollections;
        this.bookPhoto = bookPhoto;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public int getNumberOfCollections() {
        return numberOfCollections;
    }
    public void setNumberOfCollections(int numberOfCollections) {
        this.numberOfCollections = numberOfCollections;
    }
    public String getBookPhoto() {
        return bookPhoto;
    }
    public void setBookPhoto(String bookPhoto) {
        this.bookPhoto = bookPhoto;
    }
}
