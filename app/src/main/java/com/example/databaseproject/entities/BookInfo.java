package com.example.databaseproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_info")
public class BookInfo {

    @PrimaryKey
    @ColumnInfo(name = "book_id")
    private int bookId;
    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "book_type")
    private String bookType;
    @ColumnInfo(name = "book_price")
    private double bookPrice;
    @ColumnInfo(name = "book_press")
    private String bookPress;

    public BookInfo(int bookId, String bookName, String bookType, double bookPrice, String bookPress) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookType = bookType;
        this.bookPrice = bookPrice;
        this.bookPress = bookPress;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }
}
