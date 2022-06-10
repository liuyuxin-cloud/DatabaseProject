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
        Depository.class, SaleList.class, SaleInfo.class}, version = 4, exportSchema = false)
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
        private final DepositoryDao depositoryDao;
        private final BookInfoDao bookInfoDao;
        private final MemberDao memberDao;
        private final SaleInfoDao saleInfoDao;
        private final SaleListDao saleListDao;
        private final PurchaseListDao purchaseListDao;
        private final PurchaseInfoDao purchaseInfoDao;
        Depository[] depos = {new Depository(1001, "我与地坛", 10),
                                new Depository(1002, "三体", 20),
                                new Depository(1003, "世界史", 50)};
        BookInfo[] books = {new BookInfo(1001, "我与地坛", "人文艺术类", 20.00, 25.00, "人民艺术出版社", "史铁生"),
        new BookInfo(1002,"三体", "人文艺术类", 18.00, 26.00, "人民艺术出版社", "刘慈欣"),
                new BookInfo(1003, "世界史", "科普类", 15.00, 25.00,"人民艺术出版社", "wobuzhidao")

        };
        PopulateDbAsync(BookStoreDatabase db) {
            depositoryDao = db.depositoryDao();
            bookInfoDao = db.bookInfoDao();
            memberDao = db.memberDao();
            purchaseListDao = db.purchaseListDao();
            purchaseInfoDao = db.purchaseInfoDao();
            saleInfoDao = db.saleInfoDao();
            saleListDao = db.saleListDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            depositoryDao.deleteAllRepository();
            bookInfoDao.deleteAllBookInfo();
            memberDao.deleteAllMember();
            purchaseListDao.deleteAllPurchase();
            purchaseInfoDao.deletePurchaseInfo();
            for(int i = 0; i <= depos.length - 1; i++) {
                depositoryDao.insertRepository(depos[i]);
                bookInfoDao.insertBookInfo(books[i]);
            }
            return null;
        }
    }
}