package com.example.databaseproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "sale_list")
public class SaleList {

    @PrimaryKey
    @ColumnInfo(name = "sale_id")
    private int saleId;
    @ColumnInfo(name = "total_price")
    private double totalPrice;
    @ColumnInfo(name = "sale_time")
    private String saleTime;
    @ColumnInfo(name = "vip_id")
    private int vipId;

    public SaleList(int saleId, double totalPrice, String saleTime, int vipId) {
        this.vipId = vipId;
        this.saleId = saleId;
        this.totalPrice = totalPrice;
        this.saleTime = saleTime;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }
}
