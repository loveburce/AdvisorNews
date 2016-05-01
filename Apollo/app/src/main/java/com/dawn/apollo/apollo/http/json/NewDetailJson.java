package com.dawn.apollo.apollo.http.json;

import android.content.Context;

import com.dawn.apollo.apollo.bean.NewDetailModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/1.
 */
public class NewDetailJson extends JsonPacket {
    public static NewDetailJson newDetailJson;

    public NewDetailModel newDetailModle;

    public NewDetailJson(Context context) {
        super(context);
    }

    public static NewDetailJson instance(Context context){
        if(newDetailJson == null){
            newDetailJson = new NewDetailJson(context);
        }
        return newDetailJson;
    }

    public NewDetailModel readJsonNewModels(String res, String newId) {
        try {
            if (res == null || res.equals("")) {
                return null;
            }
            JSONObject jsonObject = new JSONObject(res).getJSONObject(newId);
            newDetailModle = readNewModle(jsonObject);
        } catch (Exception e) {

        } finally {
            System.gc();
        }
        return newDetailModle;
    }

    public List<String> readImgList(JSONArray jsonArray) throws Exception{
        List<String> imgList = new ArrayList<String>();
        for(int i=0; i<jsonArray.length(); i++){
            imgList.add(getString("src", jsonArray.getJSONObject(i)));
        }
        return imgList;
    }

    public NewDetailModel readNewModle(JSONObject jsonObject) throws Exception{
        NewDetailModel newDetailModel = null;
        String docid = "";
        String title = "";
        String source = "";
        String ptime = "";
        String body = "";
        String url_mp4 = "";
        String cover = "";

        docid = getString("docid", jsonObject);
        title = getString("title", jsonObject);
        source = getString("source", jsonObject);
        ptime = getString("ptime", jsonObject);
        body = getString("body", jsonObject);

        if (jsonObject.has("video")) {
            JSONObject jsonObje = jsonObject.getJSONArray("video").getJSONObject(0);
            url_mp4 = getString("url_mp4", jsonObje);
            cover = getString("cover", jsonObje);
        }

        JSONArray jsonArray = jsonObject.getJSONArray("img");

        List<String> imgList = readImgList(jsonArray);

        newDetailModle = new NewDetailModel();

        newDetailModle.setDocid(docid);
        newDetailModle.setImgList(imgList);
        newDetailModle.setPtime(ptime);
        newDetailModle.setSource(source);
        newDetailModle.setTitle(title);
        newDetailModle.setBody(body);
        newDetailModle.setUrl_mp4(url_mp4);
        newDetailModle.setCover(cover);

        return newDetailModle;
    }
}
