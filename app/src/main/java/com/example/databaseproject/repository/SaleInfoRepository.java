package com.example.databaseproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.databaseproject.daos.PurchaseListDao;
import com.example.databaseproject.daos.SaleInfoDao;
import com.example.databaseproject.daos.SaleListDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.SaleInfo;
import com.example.databaseproject.entities.SaleList;

import java.util.List;

public class SaleInfoRepository {
    private SaleInfoDao saleInfoDao;

    public LiveData<List<SaleInfo>> getSaleInfo() {
        return saleInfo;
    }

    private LiveData<List<SaleInfo>> saleInfo;

    public SaleInfoRepository(Application application) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        saleInfoDao = db.saleInfoDao();
        saleInfo = saleInfoDao.getSaleInfo();
    }

    public void insert(SaleInfo sale) {
        new SaleInfoRepository.insertAsyncTask(saleInfoDao).execute(sale);
    }

    private static class insertAsyncTask extends AsyncTask<SaleInfo, Void, Void> {
        private SaleInfoDao mAsyncTaskDao;

        insertAsyncTask(SaleInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SaleInfo... params) {
            mAsyncTaskDao.insertSaleInfo(params[0]);
            return null;
        }
    }
}