package com.example.databaseproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "sale_info", primaryKeys = {"sale_id", "book_id"})
public class SaleInfo {

    @ColumnInfo(name = "sale_id")
    private int saleId;
    @ColumnInfo(name = "book_id")
    private int bookId;
    @ColumnInfo(name = "book_price")
    private double bookPrice;
    @ColumnInfo(name = "book_num")
    private int bookNum;

    public SaleInfo(int saleId, int bookId, double bookPrice, int bookNum) {
        this.saleId = saleId;
        this.bookId = bookId;
        this.bookPrice = bookPrice;
        this.bookNum = bookNum;
    }

    public int getPurchaseId() {
        return saleId;
    }

    public void setPurchaseId(int purchaseId) {
        this.saleId = purchaseId;
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
