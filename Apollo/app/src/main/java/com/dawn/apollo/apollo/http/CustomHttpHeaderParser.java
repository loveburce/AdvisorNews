package com.dawn.apollo.apollo.http;

import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;

public class CustomHttpHeaderParser extends HttpHeaderParser {
    public static Cache.Entry parseCacheHeaders(NetworkResponse response, long cacheTime) {
        long now = System.currentTimeMillis();
        long softExpire = now + cacheTime;
        Cache.Entry entry = parseCacheHeaders(response);
        if (null == entry && response.data.length > 0) {
            entry = new Cache.Entry();
            entry.data = response.data;
            entry.serverDate = now;
            entry.ttl = softExpire;
            entry.softTtl = softExpire;
        }
        return entry;
    }
}

