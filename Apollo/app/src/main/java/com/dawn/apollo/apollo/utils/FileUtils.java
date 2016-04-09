package com.dawn.apollo.apollo.utils;

import android.os.Environment;
import com.dawn.apollo.apollo.application.MyApplication;
import java.io.File;


public class FileUtils {


    /**
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = MyApplication.getApp().getExternalCacheDir().getPath();
        } else {
            cachePath = MyApplication.getApp().getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
}
