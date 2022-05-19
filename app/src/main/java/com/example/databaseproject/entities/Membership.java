package com.example.databaseproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "membership")
public class Membership {
    @PrimaryKey
    @ColumnInfo(name = "member_id")
    private int memberId;
    @ColumnInfo(name = "member_name")
    private String memberName;
    @ColumnInfo(name = "register_time")
    private String registerTime;
    @ColumnInfo(name = "total_consumption")
    private double totalConsumption;

    public Membership(int memberId, String memberName, String registerTime, double totalConsumption) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.registerTime = registerTime;
        this.totalConsumption = totalConsumption;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }
}
