package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Depository;

import java.util.List;

@Dao
public interface BookInfoDao {

    @Insert
    void insertBookInfo(BookInfo bookInfo);

    @Query("DELETE FROM book_info")
    void deleteAllBookInfo();

    @Query("SELECT * FROM book_info WHERE book_name Like '%' || :name || '%' ")
    LiveData<BookInfo> getBookInfo(String name);

    @Query("SELECT * FROM book_info")
    LiveData<List<BookInfo>> getBookInfos();
}
