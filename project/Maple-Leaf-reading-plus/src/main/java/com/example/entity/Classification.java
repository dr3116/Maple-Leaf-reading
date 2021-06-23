package com.example.entity;

public class Classification  {
    private String classification;  //书的类别（小类）
    private String bookName;        //书名
    private String category;        //书的类别（大类）

    public String getClassification() {
        return classification;
    }
    public void setClassification(String classification) {
        this.classification = classification;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "classification='" + classification + '\'' +
                ", bookName='" + bookName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
