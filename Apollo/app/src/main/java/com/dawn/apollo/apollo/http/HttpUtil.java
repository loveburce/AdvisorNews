package com.dawn.apollo.apollo.http;

import android.content.Context;
import android.text.TextUtils;

import org.apache.http.NameValuePair;

/**
 * Created by Administrator on 2016/4/30.
 */
public class HttpUtil {
    public static String postByHttpURLConnection(String strUrl, NameValuePair... nameValuePairs) {
        return CustomHttpURLConnection.PostFromWebByHttpURLConnection(strUrl, nameValuePairs);
    }

    public static String getByHttpURLConnection(String strUrl, NameValuePair... nameValuePairs) {
        return CustomHttpURLConnection.GetFromWebByHttpUrlConnection(strUrl, nameValuePairs);
    }

    public static String postByHttpClient(Context context, String strUrl, NameValuePair... nameValuePairs) throws Exception {
        String result = CustomHttpClient.PostFromWebByHttpClient(context, strUrl, nameValuePairs);
        return result;
    }

    public static String getByHttpClient(Context context, String strUrl, NameValuePair... nameValuePairs) throws Exception {
        String result = CustomHttpClient.getFromWebByHttpClient(context, strUrl, nameValuePairs);

        if (TextUtils.isEmpty(result)) {
            result = "";
        }
        return result;
    }

}
