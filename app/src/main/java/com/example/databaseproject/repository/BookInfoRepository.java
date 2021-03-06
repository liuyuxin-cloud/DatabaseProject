package com.example.databaseproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.databaseproject.daos.BookInfoDao;
import com.example.databaseproject.daos.DepositoryDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Depository;

import java.util.List;

public class BookInfoRepository {
    private BookInfoDao bookInfoDao;
    private LiveData<BookInfo> bookInfo;
    private LiveData<List<BookInfo>> bookInfos;

    public BookInfoRepository(Application application) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        bookInfoDao = db.bookInfoDao();
        bookInfos = bookInfoDao.getBookInfos();
    }

    public LiveData<List<BookInfo>> getBookInfos() {
        return bookInfos;
    }

    public LiveData<BookInfo> getBookInfo(String name) {
        return bookInfoDao.getBookInfo(name);
    }

    public void insert(BookInfo bookInfo) {
        new BookInfoRepository.insertAsyncTask(bookInfoDao).execute(bookInfo);
    }

    private static class insertAsyncTask extends AsyncTask<BookInfo, Void, Void> {
        private BookInfoDao mAsyncTaskDao;

        insertAsyncTask(BookInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BookInfo... params) {
            mAsyncTaskDao.insertBookInfo(params[0]);
            return null;
        }
    }

}
