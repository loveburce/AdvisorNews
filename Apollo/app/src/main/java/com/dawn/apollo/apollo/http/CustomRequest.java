package com.dawn.apollo.apollo.http;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

public class CustomRequest extends StringRequest {


    private Priority mPriority;
    private long mCacheTime = 1000 * 60;// 1m


    public CustomRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public CustomRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    public Priority getPriority() {
        return mPriority == null ? Priority.NORMAL : mPriority;
    }

    public final CustomRequest setPriority(Priority priority) {
        this.mPriority = priority;
        return this;
    }

    public final CustomRequest setDefaultRetryPolicy(int timeOut, int retryTime) {
        if (retryTime == 0)
            retryTime = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
        if (timeOut == 0)
            timeOut = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        this.setRetryPolicy(new DefaultRetryPolicy(
                timeOut,
                retryTime,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return this;
    }

    public final CustomRequest setCacheTime(long cacheTime) {
        this.mCacheTime = cacheTime;
        return this;
    }

    public final CustomRequest isCache(boolean flag) {
        super.setShouldCache(flag);
        return this;
    }

    public final void start() {
        VolleyRequest.getInstance().start(this);
    }

    @Override
    protected final Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, CustomHttpHeaderParser.parseCacheHeaders(response, mCacheTime));
    }
}
