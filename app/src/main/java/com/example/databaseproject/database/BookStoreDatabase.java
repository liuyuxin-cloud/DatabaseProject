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
        Depository[] depos = {new Depository(1001, "哲学的故事", 20),
                                new Depository(1002, "你的第一本哲学书", 20),
                                new Depository(1003, "哲学与人生", 20),
                new Depository(1004, "西方哲学史", 20),
                new Depository(1005, "大问题", 20),
                new Depository(1006, "哲学的邀请", 20),
                new Depository(1007, "苏菲的世界", 20),
                new Depository(1008, "织梦人", 20),
                new Depository(1009, "哲学的慰藉", 20),
                new Depository(1010, "西方哲学史", 20),
                new Depository(2001, "相对论", 20),
                new Depository(2002, "物种起源（全新修订版）", 20),
                new Depository(2003, "几何原本", 20),
                new Depository(2004, "自然史", 20),
                new Depository(2005, "什么是数学", 20),
                new Depository(2006, "自然哲学之数学原理", 20),
                new Depository(2007, "天真的人类学家", 20),
                new Depository(2008, "地理学与生活", 20),
                new Depository(2009, "疯狂实验史II", 20),
                new Depository(2010, "超弦理论", 20),
                new Depository(3001, "如何阅读一本书", 20),
                new Depository(3002, "失控：全人类的最终命运和结局", 20),
                new Depository(3003, "图解《说文解字》", 20),
                new Depository(3004, "自由在高处（增订版）", 20),
                new Depository(3005, "乡土中国", 20),
                new Depository(3006, "请不要辜负这个时代", 20),
                new Depository(3007, "大手笔是怎样炼成的", 20),
                new Depository(3008, "天空的另一半", 20),
                new Depository(3009, "饱食穷民", 20),
                new Depository(3010, "贝多芬传:磨难与辉煌", 20),
                new Depository(4001, "月光落在左手上", 20),
                new Depository(4002, "万般滋味都是生活", 20),
                new Depository(4003, "我与地坛", 20),
                new Depository(4004, "菜根谭", 20),
                new Depository(4005, "沉默的大多数", 20),
                new Depository(4006, "正是橙黄橘绿时", 20),
                new Depository(4007, "傅雷家书", 20),
                new Depository(4008, "人间草木", 20),
                new Depository(4009, "心安即是归处", 20),
                new Depository(4010, "文化苦旅", 20),
                new Depository(5001, "明朝那些事儿全集", 20),
                new Depository(5002, "万历十五年", 20),
                new Depository(5003, "趣说中国史", 20),
                new Depository(5004, "中国近代史", 20),
                new Depository(5005, "全球通史", 20),
                new Depository(5006, "透过地理看历史", 20),
                new Depository(5007, "浪漫地理学", 20),
                new Depository(5008, "中国国家地理精华", 20),
                new Depository(5009, "国家地理百科全书合集", 20),
                new Depository(5010, "地球脉动", 20)
        };
        BookInfo[] books = {
                new BookInfo(1001, "哲学的故事", "哲学", 18.78, 21.00, "江苏人民出版社", "威尔•杜兰特"),
                new BookInfo(1002,"你的第一本哲学书", "哲学", 45.80, 52.00, "北京联合出版有限公司", "杰克•鲍温"),
                new BookInfo(1003, "哲学与人生", "哲学", 40.00, 47.00,"人民邮电出版社", "傅佩荣"),
                new BookInfo(1004, "西方哲学史", "哲学", 65.67, 70.00,"复旦大学出版社", "G・希尔贝克"),
                new BookInfo(1005, "大问题", "哲学", 197.36, 200.92,"上海联合出版公司", "罗伯所罗门"),
                new BookInfo(1006, "哲学的邀请", "哲学", 49.68, 52.34,"清华大学出版社", "费尔南多"),
                new BookInfo(1007, "苏菲的世界", "哲学", 77.45, 80.86,"广西师范大学出版社", "乔斯坦•贾德"),
                new BookInfo(1008, "织梦人", "哲学", 47.89, 51.50,"北京大学出版社", "杰克•鲍温"),
                new BookInfo(1009, "哲学的慰藉", "哲学", 15.98, 20.20,"高等教育出版社", "阿兰•德波顿"),
                new BookInfo(1010, "西方哲学史", "哲学", 44.68, 48.00,"浙江教育出版社", "赵琳、邓晓芒"),
                new BookInfo(2001, "相对论", "自然应用科学", 125.77, 128.00,"北京联合出版有限公司", "爱因斯坦"),
                new BookInfo(2002, "物种起源（全新修订版）", "自然应用科学", 75.45, 80.30,"人民邮电出版社", "查理•达尔文"),
                new BookInfo(2003, "几何原本", "自然应用科学", 19.89, 22.51,"人民出版社", "欧几里得"),
                new BookInfo(2004, "自然史", "自然应用科学", 59.76, 63.46,"中国人民解放军出版社", "布封"),
                new BookInfo(2005, "什么是数学", "自然应用科学", 35.66, 38.83,"清华大学出版社", "王晓夏"),
                new BookInfo(2006, "自然哲学之数学原理", "自然应用科学", 45.32, 49.00,"北京联合出版有限公司", "牛顿"),
                new BookInfo(2007, "天真的人类学家", "自然应用科学", 126.73, 129.30,"中华书局", "吉尔•巴利"),
                new BookInfo(2008, "地理学与生活", "自然应用科学", 16.34, 19.54,"科学出版有限公司", "阿瑟•格蒂斯"),
                new BookInfo(2009, "疯狂实验史II", "自然应用科学", 14.54, 19.52,"机械工业出版社", "施耐德"),
                new BookInfo(2010, "超弦理论", "自然应用科学", 86.45, 92.61,"机械工业出版社", "柯朗"),
                new BookInfo(3001, "如何阅读一本书", "人文社会科学", 42.45, 49.98,"重庆出版社", "艾德勒"),
                new BookInfo(3002, "失控：全人类的最终命运和结局", "人文社会科学", 50.16, 53.56,"星球地图出版社", "凯文•凯利"),
                new BookInfo(3003, "图解《说文解字》", "人文社会科学", 46.14, 49.00,"北京出版社", "费孝通"),
                new BookInfo(3004, "自由在高处(增订版)", "人文社会科学", 60.78, 66.88,"中国青年出版社", "熊培云"),
                new BookInfo(3005, "乡土中国", "人文社会科学", 15.99, 22.32,"解放军出版社", "费孝通"),
                new BookInfo(3006, "请不要辜负这个时代", "人文社会科学", 125.78, 132.00,"中国时代经济出版社", "周小平"),
                new BookInfo(3007, "大手笔是怎样炼成的", "人文社会科学", 195.67, 199.50,"中国财政经济出版社", "安吉丽娜"),
                new BookInfo(3008, "天空的另一半", "人文社会科学", 33.46, 37.80,"湖南科学技术出版社", "闾丘露薇比茨"),
                new BookInfo(3009, "饱食穷民", "人文社会科学", 35.78, 41.20,"人民文学出版社", "斋藤茂男"),
                new BookInfo(3010, "贝多芬传：磨难与辉煌", "人文社会科学", 42.59, 45.54,"人民音乐出版社", "扬•斯瓦福德"),
                new BookInfo(4001, "月光落在左手上", "人文艺术类", 54.67, 58.00,"北京十月文艺出版社", "余秀华"),
                new BookInfo(4002, "万般滋味都是生活", "人文艺术类", 31.48, 36.60,"华中科技大学出版社", "丰子恺"),
                new BookInfo(4003, "我与地坛", "人文艺术类", 12.27, 16.50,"人民文学出版社", "史铁生"),
                new BookInfo(4004, "菜根谭", "人文艺术类", 36.37, 39.60,"古吴轩出版社", "洪应明"),
                new BookInfo(4005, "沉默的大多数", "人文艺术类", 55.83, 69.00,"北京十月文艺出版社", "王小波"),
                new BookInfo(4006, "正是橙黄橘绿时", "人文艺术类", 21.64, 24.00,"北京联合出版有限公司", "肖复兴"),
                new BookInfo(4007, "傅雷家书", "人文艺术类", 12.38, 15.50,"万卷出版公司", "傅雷"),
                new BookInfo(4008, "人间草木", "人文艺术类", 19.57, 23.90,"北京时代华文书局", "汪曾祺"),
                new BookInfo(4009, "心安即是归处", "人文艺术类", 38.93, 42.70,"古吴轩出版社", "季羡林"),
                new BookInfo(4010, "文化苦旅", "人文艺术类", 46.28, 52.00,"北京联合出版有限公司", "余秋雨"),
                new BookInfo(5001, "明朝那些事儿全集", "历史地理类", 385.56, 393.00,"北京联合出版有限公司", "当年明月"),
                new BookInfo(5002, "万历十五年", "历史地理类", 12.53, 15.60,"生活读书新知三联书店", "黄仁宇"),
                new BookInfo(5003, "趣说中国史", "历史地理类", 44.32, 49.80,"台海出版社", "趣哥"),
                new BookInfo(5004, "中国近代史", "历史地理类", 15.8, 22.70,"民主与建设出版社", "斯塔夫"),
                new BookInfo(5005, "全球通史", "历史地理类", 58.56, 64.00,"北京大学出版社", "蒋廷黻"),
                new BookInfo(5006, "透过地理看历史", "历史地理类", 45.29, 48.00,"台海出版社", "李不白"),
                new BookInfo(5007, "浪漫地理学", "历史地理类", 53.42, 59.00,"译林出版社", "段义孚"),
                new BookInfo(5008, "中国国家地理精华", "历史地理类", 10.73, 13.30,"北京联合出版有限公司", "编委会"),
                new BookInfo(5009, "国家地理百科全书合集", "历史地理类", 250.55, 258.70,"北京联合出版有限公司", "张妙弟"),
                new BookInfo(5010, "地球脉动", "历史地理类", 42.78, 49.95,"人民邮电出版社", "吉尔"),

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
            saleListDao.deleteSale();
            saleInfoDao.deleteSaleInfo();
            for(int i = 0; i <= depos.length - 1; i++) {
                depositoryDao.insertRepository(depos[i]);
                bookInfoDao.insertBookInfo(books[i]);
            }
            return null;
        }
    }
}