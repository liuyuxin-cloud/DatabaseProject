package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.BookInfo;

@Dao
public interface BookInfoDao {

    @Insert
    void insertBookInfo(BookInfo bookInfo);

    @Query("DELETE FROM book_info")
    void deleteAllBookInfo();

    @Query("SELECT * FROM book_info WHERE book_name = :name")
    LiveData<BookInfo> getBookInfo(String name);
}
