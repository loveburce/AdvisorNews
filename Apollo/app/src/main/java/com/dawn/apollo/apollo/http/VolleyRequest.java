package com.dawn.apollo.apollo.http;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dawn.apollo.apollo.application.MyApplication;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;

public class VolleyRequest {

    private static VolleyRequest mVolleyRequest;
    private static RequestQueue mRequestQueues;

    private VolleyRequest() {
        mRequestQueues = Volley.newRequestQueue(MyApplication.getApp(), new OkHttpStackO(new OkHttpClient()));
    }

    public static VolleyRequest getInstance() {
        if (mVolleyRequest == null) {
            synchronized (VolleyRequest.class) {
                if (mVolleyRequest == null) {
                    mVolleyRequest = new VolleyRequest();
                }
            }
        }
        return mVolleyRequest;
    }


    public CustomRequest setGetRequest(String url, Object tag, @NonNull VolleyListener listener) {
        mRequestQueues.cancelAll(tag);
        CustomRequest request = new CustomRequest(url, listener.sucessListener(), listener.errorListener());
        request.setTag(tag);
        return request;
    }

    public CustomRequest setPostRequest(String url, Object tag, final Map params,@NonNull VolleyListener listener) {
        mRequestQueues.cancelAll(tag);
        CustomRequest request = new CustomRequest(Request.Method.POST, url, listener.sucessListener(), listener.errorListener()) {
            @Override
            protected Map getParams() throws AuthFailureError {
                return params;
            }
        };
        request.setTag(tag);
        return request;
    }


    public void RequestGet(String url, Object tag, @NonNull VolleyListener listener) {
        start(setGetRequest(url, tag, listener));
    }

    public void RequestPost(String url, Object tag, final ArrayMap<String, String> params, @NonNull VolleyListener listener) {
        start(setPostRequest(url, tag, params, listener));
    }


    public void start(Request request) {
        mRequestQueues.add(request);
        mRequestQueues.start();
    }

    public RequestQueue getRequestQueues() {
        return mRequestQueues;
    }
}



