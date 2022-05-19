package com.example.databaseproject.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.databaseproject.daos.BookInfoDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.BookInfo;

import java.util.List;

public class BookInfoRepository {
    private BookInfoDao bookInfoDao;
    private LiveData<BookInfo> bookInfo;

    public BookInfoRepository(Application application, String name) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        bookInfoDao = db.bookInfoDao();
        bookInfo = bookInfoDao.getBookInfo(name);
    }

    public LiveData<BookInfo> getBookInfo() {
        return bookInfo;
    }

}