package com.example.databaseproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.example.databaseproject.daos.PurchaseListDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.PurchaseList;

import java.util.List;

public class PurchaseListRepository {

    private PurchaseListDao purchaseListDao;

    public LiveData<List<PurchaseList>> getPurchaseInfo() {
        return purchaseList;
    }

    private LiveData<List<PurchaseList>> purchaseList;

    public PurchaseListRepository(Application application) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        purchaseListDao = db.purchaseListDao();
        purchaseList = purchaseListDao.getAllPurchase();
    }

    public void insert(PurchaseList pur) {
        new PurchaseListRepository.insertAsyncTask(purchaseListDao).execute(pur);
    }

    private static class insertAsyncTask extends AsyncTask<PurchaseList, Void, Void> {
        private PurchaseListDao mAsyncTaskDao;

        insertAsyncTask(PurchaseListDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PurchaseList... params) {
            mAsyncTaskDao.insertPurchase(params[0]);
            return null;
        }
    }
}
