package com.example.databaseproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.databaseproject.daos.BookInfoDao;
import com.example.databaseproject.daos.RepositoryDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.Repository;

import java.util.List;

public class ReRepository {

    private RepositoryDao repositoryDao;
    private LiveData<List<Repository>> repositories;

    ReRepository(Application application) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        repositoryDao = db.repositoryDao();
        repositories = repositoryDao.getRepository();
    }

    LiveData<List<Repository>> getRepositories() {
        return repositories;
    }

    public void insert(Repository repository) {
        new insertAsyncTask(repositoryDao).execute(repository);
    }

    private static class insertAsyncTask extends AsyncTask<Repository, Void, Void> {
        private RepositoryDao mAsyncTaskDao;

        insertAsyncTask(RepositoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Repository... params) {
            mAsyncTaskDao.insertRepository(params[0]);
            return null;
        }
    }

}
