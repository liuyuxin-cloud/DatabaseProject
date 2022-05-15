package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.Membership;
import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;

import java.util.List;

public interface PurchaseDao {

    @Insert
    void insertPurchase(PurchaseList purchaseList);

    @Insert
    void insertPurchaseInfo(PurchaseInfo purchaseInfo);

    @Query("DELETE FROM purchase_info")
    void deletePurchaseInfo();

    @Query("DELETE FROM purchase_list")
    void deleteAllPurchase();

    @Query("SELECT * FROM purchase_list")
    LiveData<List<PurchaseList>> getAllPurchase();

    @Query("SELECT * FROM purchase_info")
    LiveData<List<PurchaseInfo>> getPurchaseInfo();
}
