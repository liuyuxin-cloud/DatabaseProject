package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;

import java.util.List;

@Dao
public interface PurchaseInfoDao {

    @Query("SELECT * FROM purchase_info")
    LiveData<List<PurchaseInfo>> getPurchaseInfo();

    @Insert
    void insertPurchaseInfo(PurchaseInfo purchaseInfo);

    @Query("DELETE FROM purchase_info")
    void deletePurchaseInfo();
}
