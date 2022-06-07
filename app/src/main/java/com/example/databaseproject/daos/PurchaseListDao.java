package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.Membership;
import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;

import java.util.List;

@Dao
public interface PurchaseListDao {

    @Insert
    void insertPurchase(PurchaseList purchaseList);

    @Query("DELETE FROM purchase_list")
    void deleteAllPurchase();

    @Query("SELECT * FROM purchase_list")
    LiveData<List<PurchaseList>> getAllPurchase();

    @Query("SELECT purchase_id FROM purchase_list ORDER BY purchase_id")
    LiveData<Integer> getId();
}
