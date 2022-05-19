package com.example.databaseproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "purchase_list")
public class PurchaseList {

    @PrimaryKey
    @ColumnInfo(name = "purchase_id")
    private int purchaseId;
    @ColumnInfo(name = "total_price")
    private double totalPrice;
    @ColumnInfo(name = "purchase_time")
    private String purchaseTime;

    public PurchaseList(int purchaseId, double totalPrice, String purchaseTime) {
        this.purchaseId = purchaseId;
        this.totalPrice = totalPrice;
        this.purchaseTime = purchaseTime;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
