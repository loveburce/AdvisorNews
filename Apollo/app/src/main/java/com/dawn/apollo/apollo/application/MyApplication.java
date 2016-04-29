package com.dawn.apollo.apollo.application;

import android.app.Application;
import android.content.Context;

import com.dawn.apollo.apollo.db.SQLHelper;

/**
 * Created by Administrator on 2016/4/9.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;
    private SQLHelper sqlHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initImageLoader(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    public static MyApplication getApp() {
        return myApplication;
    }

    /** 获取数据库Helper */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(myApplication);
        return sqlHelper;
    }

    @Override
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
        // 整体摧毁的时候调用这个方法
    }

    public static void initImageLoader(Context context) {

    }
}
