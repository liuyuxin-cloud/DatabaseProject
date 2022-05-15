package com.example.databaseproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "sale_list")
public class SaleList {

    @PrimaryKey
    @ColumnInfo(name = "sale_id")
    private int SaleId;
    @ColumnInfo(name = "total_price")
    private double totalPrice;
    @ColumnInfo(name = "sale_time")
    private Date saleTime;

    public SaleList(int saleId, double totalPrice, Date saleTime) {
        this.SaleId = saleId;
        this.totalPrice = totalPrice;
        this.saleTime = saleTime;
    }

    public int getPurchaseId() {
        return SaleId;
    }

    public void setPurchaseId(int purchaseId) {
        this.SaleId = purchaseId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPurchaseTime() {
        return saleTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.saleTime = purchaseTime;
    }
}
