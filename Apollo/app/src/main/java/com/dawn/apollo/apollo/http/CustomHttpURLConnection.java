package com.dawn.apollo.apollo.http;

import org.apache.http.NameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/30.
 */
public class CustomHttpURLConnection {
    private static String TAG = "CustomHttpUrlConnection";
    private static HttpURLConnection httpURLConnection;

    public CustomHttpURLConnection() {
    }

    public static String GetFromWebByHttpUrlConnection(String strUrl, NameValuePair... nameValuePairs){
        String result = "";
        try {
            URL url = new URL(strUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setReadTimeout(4000);
            httpURLConnection.setRequestProperty("accept", "*/*");
            // int resCode=conn.getResponseCode();
            httpURLConnection.connect();

            InputStream stream = httpURLConnection.getInputStream();
            InputStreamReader inReader = new InputStreamReader(stream);
            BufferedReader buffer = new BufferedReader(inReader);
            String strLine = null;
            while ((strLine = buffer.readLine()) != null) {
                result += strLine;
            }
            inReader.close();
            httpURLConnection.disconnect();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String PostFromWebByHttpURLConnection(String strUrl, NameValuePair... nameValuePairs) {
        String result = "";
        try{
            URL url = null;
            url = new URL(strUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            // http正文内，因此�?��设为true, 默认情况下是false;
            httpURLConnection.setDoOutput(true);
            // 设定请求的方法为"POST"，默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置超时
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setReadTimeout(4000);
            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            // 设定传�?的内容类型是可序列化的java对象
            // (如果不设此项,在传送序列化对象�?当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 连接，从上述�?条中url.openConnection()至此的配置必须要在connect之前完成�?
            //httpURLConnection.connect();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inStream = new InputStreamReader(in);
            BufferedReader buffer = new BufferedReader(inStream);
            String strLine = null;
            while ((strLine = buffer.readLine()) != null)
            {
                result += strLine;
            }
            return result;
        }catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
