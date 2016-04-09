package com.dawn.apollo.apollo.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
import com.dawn.apollo.apollo.utils.CommonUtils;
import com.dawn.apollo.apollo.utils.FileUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CustomImageCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache = null;
    private static final long DISK_CACHE_MAX_SIZE = 100 * 1024 * 1024;
    private boolean isCache = true;

    public CustomImageCache() {
        int maxSize = (int) Runtime.getRuntime().maxMemory() / 8;
        mMemoryCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getHeight() * value.getRowBytes();
            }
        };
        try {
            File cacheDir = FileUtils.getDiskCacheDir("thumb");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache
                    .open(cacheDir, CommonUtils.getAppVersion(), 1, DISK_CACHE_MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        if (mMemoryCache.get(url) != null) {
            bitmap = mMemoryCache.get(url);
        } else {
            String key = CommonUtils.encryptionWithMD5(url);
            try {
                if (mDiskLruCache.get(key) != null) {
                    CommonUtils.log("hehe");
                    DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
                    InputStream is = snapShot.getInputStream(0);
                    bitmap = BitmapFactory.decodeStream(is);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @Override
    public void putBitmap(final String url, final Bitmap bitmap) {
        if (isCache) {
            mMemoryCache.put(url, bitmap);
            setDiskCache(url, bitmap);
        }
    }

    public void isCache(boolean flag) {
        this.isCache = flag;
    }

    public void clearMemory() {
        if (this.mMemoryCache != null)
            this.mMemoryCache.evictAll();
    }

    public void removeCache(String url) {
        try {
            this.mMemoryCache.remove(url);
            String key = CommonUtils.encryptionWithMD5(url);
            this.mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setDiskCache(final String url, final Bitmap bitmap) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String key = CommonUtils.encryptionWithMD5(url);
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        outputStream = baos;
                        if (outputStream != null) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                        mDiskLruCache.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}