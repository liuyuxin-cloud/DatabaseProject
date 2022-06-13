package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.databaseproject.entities.Depository;

import java.util.List;

@Dao
public interface DepositoryDao {

    @Insert
    void insertRepository(Depository repository);

    @Query("DELETE FROM depository")
    void deleteAllRepository();

    @Query("SELECT * FROM depository")
    LiveData<List<Depository>> getRepository();

    @Query("SELECT book_num FROM depository WHERE book_name = :name")
    LiveData<Integer> getBookNum(String name);

    @Update
    void updateDepo(Depository depository);
}
