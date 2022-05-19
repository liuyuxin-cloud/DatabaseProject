package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.Depository;

import java.util.List;

@Dao
public interface DepositoryDao {

    @Insert
    void insertRepository(Depository repository);

    @Query("DELETE FROM Depository")
    void deleteAllRepository();

    @Query("SELECT * FROM Depository")
    LiveData<List<Depository>> getRepository();

}
