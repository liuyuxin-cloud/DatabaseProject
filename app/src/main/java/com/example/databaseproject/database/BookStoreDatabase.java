package com.example.databaseproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.databaseproject.daos.BookInfoDao;
import com.example.databaseproject.daos.MemberDao;
import com.example.databaseproject.daos.PurchaseDao;
import com.example.databaseproject.daos.RepositoryDao;
import com.example.databaseproject.daos.SaleDao;
import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Membership;
import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.Repository;
import com.example.databaseproject.entities.SaleInfo;
import com.example.databaseproject.entities.SaleList;

@Database(entities = {BookInfo.class, Membership.class, PurchaseInfo.class, PurchaseList.class,
        Repository.class, SaleList.class, SaleInfo.class}, version = 1, exportSchema = false)
public abstract class BookStoreDatabase extends RoomDatabase {

    public abstract BookInfoDao bookInfoDao();
    public abstract MemberDao memberDao();
    public abstract PurchaseDao purchaseDao();
    public abstract RepositoryDao repositoryDao();
    public abstract SaleDao saleDao();

    private static BookStoreDatabase INSTANCE;

    public static BookStoreDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookStoreDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookStoreDatabase.class, "bookstore_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}