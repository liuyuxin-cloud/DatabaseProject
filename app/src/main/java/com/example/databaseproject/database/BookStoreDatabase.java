package com.example.databaseproject.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.databaseproject.daos.BookInfoDao;
import com.example.databaseproject.daos.MemberDao;
import com.example.databaseproject.daos.DepositoryDao;
import com.example.databaseproject.daos.PurchaseInfoDao;
import com.example.databaseproject.daos.PurchaseListDao;
import com.example.databaseproject.daos.SaleInfoDao;
import com.example.databaseproject.daos.SaleListDao;
import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Membership;
import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.entities.SaleInfo;
import com.example.databaseproject.entities.SaleList;

@Database(entities = {BookInfo.class, Membership.class, PurchaseInfo.class, PurchaseList.class,
        Depository.class, SaleList.class, SaleInfo.class}, version = 1, exportSchema = false)
public abstract class BookStoreDatabase extends RoomDatabase {

    public abstract BookInfoDao bookInfoDao();
    public abstract MemberDao memberDao();
    public abstract PurchaseListDao purchaseListDao();
    public abstract DepositoryDao depositoryDao();
    public abstract SaleListDao saleListDao();
    public abstract SaleInfoDao saleInfoDao();
    public abstract PurchaseInfoDao purchaseInfoDao();

    private static BookStoreDatabase INSTANCE;

    public static BookStoreDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookStoreDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookStoreDatabase.class, "bookstore_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final DepositoryDao mDao;
        Depository[] depos = {new Depository(1001, "我与地坛", 10),
                                new Depository(1002, "三体", 20),
                                new Depository(1003, "世界史", 50)};
        PopulateDbAsync(BookStoreDatabase db) {
            mDao = db.depositoryDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAllRepository();

            for(int i = 0; i <= depos.length - 1; i++) {

                mDao.insertRepository(depos[i]);
            }
            return null;
        }
    }
}