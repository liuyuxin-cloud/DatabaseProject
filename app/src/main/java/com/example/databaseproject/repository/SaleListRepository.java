package com.example.databaseproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.databaseproject.daos.PurchaseListDao;
import com.example.databaseproject.daos.SaleInfoDao;
import com.example.databaseproject.daos.SaleListDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.SaleList;

import java.util.List;

public class SaleListRepository {
    private SaleListDao saleListDao;

    public LiveData<List<SaleList>> getSaleList() {
        return saleList;
    }

    private LiveData<List<SaleList>> saleList;

    public SaleListRepository(Application application) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        saleListDao = db.saleListDao();
        saleList = saleListDao.getAllSale();
    }

    public void insert(SaleList sale) {
        new SaleListRepository.insertAsyncTask(saleListDao).execute(sale);
    }

    private static class insertAsyncTask extends AsyncTask<SaleList, Void, Void> {
        private SaleListDao mAsyncTaskDao;

        insertAsyncTask(SaleListDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SaleList... params) {
            mAsyncTaskDao.insertSale(params[0]);
            return null;
        }
    }
}
