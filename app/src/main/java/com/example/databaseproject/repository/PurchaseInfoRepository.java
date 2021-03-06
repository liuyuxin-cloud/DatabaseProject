package com.example.databaseproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.databaseproject.daos.PurchaseInfoDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.PurchaseInfo;

import java.util.List;

public class PurchaseInfoRepository {
    private PurchaseInfoDao purchaseInfoDao;

    public LiveData<List<PurchaseInfo>> getPurchaseInfo() {
        return purchaseInfo;
    }

    private LiveData<List<PurchaseInfo>> purchaseInfo;

    public PurchaseInfoRepository(Application application) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        purchaseInfoDao = db.purchaseInfoDao();
        purchaseInfo = purchaseInfoDao.getPurchaseInfo();
    }

    public void insert(PurchaseInfo pur) {
        new PurchaseInfoRepository.insertAsyncTask(purchaseInfoDao).execute(pur);
    }

    private static class insertAsyncTask extends AsyncTask<PurchaseInfo, Void, Void> {
        private PurchaseInfoDao mAsyncTaskDao;

        insertAsyncTask(PurchaseInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PurchaseInfo... params) {
            mAsyncTaskDao.insertPurchaseInfo(params[0]);
            return null;
        }
    }
}
