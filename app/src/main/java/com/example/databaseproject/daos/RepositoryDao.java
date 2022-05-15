package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Repository;

import java.util.List;

public interface RepositoryDao {

    @Insert
    void insertRepository(Repository repository);

    @Query("DELETE FROM repository")
    void deleteAllRepository();

    @Query("SELECT * FROM repository")
    LiveData<List<Repository>> getRepository();

}
