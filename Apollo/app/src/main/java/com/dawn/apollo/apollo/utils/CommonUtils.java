package com.dawn.apollo.apollo.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.dawn.apollo.apollo.BuildConfig;
import com.dawn.apollo.apollo.application.MyApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CommonUtils {

    public static Gson gson = new GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static void showToast(String content, int time) {
        Toast.makeText(MyApplication.getApp(), content, time).show();
    }

    public static void showToast(String content) {
        showToast(content, Toast.LENGTH_SHORT);
    }

    public static void log(String content) {
        if (BuildConfig.DEBUG) Log.e("zyy", content);
    }


    public static int getAppVersion() {
        try {
            PackageInfo info = MyApplication.getApp().getPackageManager().getPackageInfo
                    (MyApplication.getApp().getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static String encryptionWithMD5(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mDigest.digest().length; i++) {
                String hex = Integer.toHexString(0xFF & mDigest.digest()[i]);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            cacheKey = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    public static int dipTopx(float dpValue) {
        final float scale = MyApplication.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int pxTodip(float pxValue) {
        final float scale = MyApplication.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
