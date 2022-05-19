package com.example.databaseproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Membership;

import java.util.List;

@Dao
public interface MemberDao {

    @Insert
    void insertMember(Membership membership) ;

    @Query("DELETE FROM membership")
    void deleteAllMember();

    @Query("SELECT * FROM membership")
    LiveData<List<Membership>> getAllMember();
}
