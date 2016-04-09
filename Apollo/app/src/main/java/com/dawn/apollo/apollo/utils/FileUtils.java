package com.dawn.apollo.apollo.utils;

import android.os.Environment;

import java.io.File;

import application.FreeWalkerApplication;

/**
 * Created by zhangyanye on 2015/9/21.
 * Description:
 */
public class FileUtils {


    /**
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(String uniqueName) {
        String cachePath;
        //当SD卡存在或者SD卡不可被移除的时候，就调用getExternalCacheDir()方法来获取缓存路�?
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //eg�? /sdcard/Android/data/<application package>/cache
            cachePath = FreeWalkerApplication.getApp().getExternalCacheDir().getPath();
        } else {
            //eg�? /data/data/<application package>/cache
            cachePath = FreeWalkerApplication.getApp().getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
}
