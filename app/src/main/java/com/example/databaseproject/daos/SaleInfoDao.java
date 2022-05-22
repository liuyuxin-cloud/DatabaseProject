package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.SaleInfo;

import java.util.List;

@Dao
public interface SaleInfoDao {

    @Query("DELETE FROM sale_info")
    void deleteSaleInfo();

    @Insert
    void insertSaleInfo(SaleInfo saleInfo);

    @Query("SELECT * FROM purchase_info")
    LiveData<List<PurchaseInfo>> getSaleInfo();
}
