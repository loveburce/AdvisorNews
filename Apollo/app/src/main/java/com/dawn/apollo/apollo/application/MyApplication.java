package com.dawn.apollo.apollo.application;

import android.app.Application;

/**
 * Created by Administrator on 2016/4/9.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    public static MyApplication getApp() {
        return myApplication;
    }
}
