package com.dawn.apollo.apollo.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhangyanye.freewalker.BuildConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import application.FreeWalkerApplication;

/**
 * Created by zhangyanye on 2015/8/23
 * Description:工具集合
 * 1.toast
 * 2.Log
 * 3.gson
 * 4.获取app版本�?
 * 5.MD5加密
 * 6.dp转px
 * 7.px转dp
 */
public class CommonUtils {

    public static Gson gson = new GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static void showToast(String content, int time) {
        Toast.makeText(FreeWalkerApplication.getApp(), content, time).show();
    }

    public static void showToast(String content) {
        showToast(content, Toast.LENGTH_SHORT);
    }

    public static void log(String content) {
        if (BuildConfig.DEBUG) Log.e("zyy", content);
    }


    public static int getAppVersion() {
        try {
            PackageInfo info = FreeWalkerApplication.getApp().getPackageManager().getPackageInfo
                    (FreeWalkerApplication.getApp().getPackageName(), 0);
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

    /**
     * 根据手机的分辨率�? dp 的单�? 转成�? px(像素)
     */
    public static int dipTopx(float dpValue) {
        final float scale = FreeWalkerApplication.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率�? px(像素) 的单�? 转成�? dp
     */
    public static int pxTodip(float pxValue) {
        final float scale = FreeWalkerApplication.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
