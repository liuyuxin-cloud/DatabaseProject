package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;

import java.util.List;

@Dao
public interface SaleInfoDao {

    @Query("DELETE FROM sale_info")
    void deleteSaleInfo();

    @Query("SELECT * FROM purchase_list")
    LiveData<List<PurchaseList>> getAllSale();

    @Query("SELECT * FROM purchase_info")
    LiveData<List<PurchaseInfo>> getSaleInfo();
}
