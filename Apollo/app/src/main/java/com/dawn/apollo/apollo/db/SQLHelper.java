package com.dawn.apollo.apollo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dawn-pc on 2016/4/29.
 */
public class SQLHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";// 数据库名称
    public static final int VERSION = 1;

    public static final String TABLE_CHANNEL = "ChannelItem";// 数据表

    public static final String ID = "id";//
    public static final String NAME = "name";
    public static final String ORDERID = "orderId";
    public static final String SELECTED = "selected";
    private final Context context;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists \" + TABLE_CHANNEL +\n" +
                "                \"(_id INTEGER PRIMARY KEY AUTOINCREMENT, \" +\n" +
                "                ID + \" INTEGER , \" +\n" +
                "                NAME + \" TEXT , \" +\n" +
                "                ORDERID + \" INTEGER , \" +\n" +
                "                SELECTED + \" TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
