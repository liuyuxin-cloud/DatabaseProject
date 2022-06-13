package com.example.databaseproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.databaseproject.daos.DepositoryDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.Depository;

import java.util.List;

public class DepoRepository {

    private DepositoryDao repositoryDao;
    private LiveData<List<Depository>> repositories;

    public DepoRepository(Application application) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        repositoryDao = db.depositoryDao();
        repositories = repositoryDao.getRepository();
    }

    public LiveData<List<Depository>> getRepositories() {
        return repositories;
    }

    public void insert(Depository repository) {
        new insertAsyncTask(repositoryDao).execute(repository);
    }

    public void update(Depository depository) {
        new updateAsyncTask(repositoryDao).execute(depository);
    }

    private static class insertAsyncTask extends AsyncTask<Depository, Void, Void> {
        private DepositoryDao mAsyncTaskDao;

        insertAsyncTask(DepositoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Depository... params) {
            mAsyncTaskDao.insertRepository(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Depository, Void, Void> {
        private DepositoryDao mAsyncTaskDao;

        updateAsyncTask(DepositoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Depository... params) {
            mAsyncTaskDao.updateDepo(params[0]);
            return null;
        }
    }

}
