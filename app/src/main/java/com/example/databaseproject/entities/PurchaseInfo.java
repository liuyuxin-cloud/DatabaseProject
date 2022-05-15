package com.example.databaseproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "purchase_info", primaryKeys = {"purchase_id", "book_id"})
public class PurchaseInfo {

    @ColumnInfo(name = "purchase_id")
    private int purchaseId;
    @ColumnInfo(name = "book_id")
    private int bookId;
    @ColumnInfo(name = "book_price")
    private double bookPrice;
    @ColumnInfo(name = "book_num")
    private int bookNum;

    public PurchaseInfo(int purchaseId, int bookId, double bookPrice, int bookNum) {
        this.purchaseId = purchaseId;
        this.bookId = bookId;
        this.bookPrice = bookPrice;
        this.bookNum = bookNum;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

}
