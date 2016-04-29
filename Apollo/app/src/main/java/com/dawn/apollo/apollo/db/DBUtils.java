package com.dawn.apollo.apollo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dawn-pc on 2016/4/29.
 */
public class DBUtils {
    private static DBUtils mInstance;
    private SQLHelper mSQLHelp;
    private SQLiteDatabase mSQLiteDatabase;

    private DBUtils(Context context) {
        mSQLHelp = new SQLHelper(context);
        mSQLiteDatabase = mSQLHelp.getWritableDatabase();
    }

    public static DBUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBUtils(context);
        }
        return mInstance;
    }

    public void close() {
        mSQLHelp.close();
        mSQLHelp = null;
        mSQLiteDatabase.close();
        mSQLiteDatabase = null;
        mInstance = null;
    }

    public void insertData(ContentValues values){
        mSQLiteDatabase.insert(SQLHelper.TABLE_CHANNEL, null, values);
    }

    public void updateData(ContentValues values, String whereClause, String[] whereArgs) {
        mSQLiteDatabase.update(SQLHelper.TABLE_CHANNEL, values, whereClause, whereArgs);
    }

    public void deleteData(String whereClause, String[] whereArgs) {
        mSQLiteDatabase.delete(SQLHelper.TABLE_CHANNEL, whereClause, whereArgs);
    }

    public Cursor selectData(String[] columns, String selection,
                             String[] selectionArgs, String groupBy, String having,
                             String orderBy) {
        Cursor cursor = mSQLiteDatabase.query(SQLHelper.TABLE_CHANNEL, columns, selection, selectionArgs, groupBy, having, orderBy);
        return cursor;
    }
}
