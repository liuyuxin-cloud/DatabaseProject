package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.SaleInfo;
import com.example.databaseproject.entities.SaleList;

import java.util.List;

public interface SaleDao {

    @Insert
    void insertSale(SaleList saleList);

    @Insert
    void insertSaleInfo(SaleInfo saleInfo);

    @Query("DELETE FROM sale_list")
    void deleteSale();

    @Query("DELETE FROM sale_info")
    void deleteSaleInfo();

    @Query("SELECT * FROM purchase_list")
    LiveData<List<PurchaseList>> getAllSale();

    @Query("SELECT * FROM purchase_info")
    LiveData<List<PurchaseInfo>> getSaleInfo();
}
