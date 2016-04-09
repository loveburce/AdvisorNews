package com.dawn.apollo.apollo.http;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

public class VolleyImageLoader {

    private static VolleyImageLoader mVolleyImageLoader;
    private ImageLoader mImageLoader;
    private CustomImageCache mCache;
    private ImageLoader.ImageListener mListener;

    public final static VolleyImageLoader getInstance() {
        if (mVolleyImageLoader == null) {
            synchronized (VolleyImageLoader.class) {
                if (mVolleyImageLoader == null) {
                    mVolleyImageLoader = new VolleyImageLoader();
                }
            }
        }
        return mVolleyImageLoader;
    }

    private VolleyImageLoader() {
        mCache = new CustomImageCache();
        mImageLoader = new ImageLoader(VolleyRequest.getInstance().getRequestQueues(), mCache);
    }


    public final void load(ImageView view, String url, @DrawableRes int defPic, @DrawableRes int failPic) {
        this.load(view, url, defPic, failPic, 0, 0);
    }

    public final void load(ImageView view, String url, @DrawableRes int defPic, @DrawableRes int failPic, int maxWidth, int maxHeight) {
        if (defPic == 0)
            defPic = android.R.drawable.ic_menu_gallery;
        if (failPic == 0)
            defPic = android.R.drawable.ic_menu_mapmode;
        mListener = ImageLoader.getImageListener(view, defPic, failPic);
        mImageLoader.get(url, mListener, maxWidth, maxHeight);
    }


    public final void loadWithoutCache(ImageView view, String url, @DrawableRes int defPic, @DrawableRes int failPic) {
        this.loadWithoutCache(view, url, defPic, failPic, 0, 0);
    }

    public final void loadWithoutCache(ImageView view, String url, @DrawableRes int defPic, @DrawableRes int failPic, int maxWidth, int maxHeight) {
        if (defPic == 0)
            defPic = android.R.drawable.ic_menu_gallery;
        if (failPic == 0)
            failPic = android.R.drawable.ic_menu_mapmode;
        mListener = ImageLoader.getImageListener(view, defPic, failPic);
        mCache.isCache(false);
        ImageLoader imageLoader = new ImageLoader(VolleyRequest.getInstance().getRequestQueues(), mCache);
        imageLoader.get(url, mListener, maxWidth, maxHeight);
    }

    public final void clearMemoryCache() {
        mCache.clearMemory();
    }

    public final void removeCache(String url) {
        mCache.removeCache(url);
    }
}
