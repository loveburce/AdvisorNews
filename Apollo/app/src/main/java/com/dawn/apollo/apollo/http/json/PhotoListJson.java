package com.dawn.apollo.apollo.http.json;

import android.content.Context;

import com.dawn.apollo.apollo.bean.PhotoModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/1.
 */
public class PhotoListJson extends JsonPacket{
    public List<PhotoModel> photoModles = new ArrayList<PhotoModel>();

    public static PhotoListJson photoListJson;

    public PhotoListJson(Context context) {
        super(context);
    }

    public static PhotoListJson instance(Context context) {
        if (photoListJson == null) {
            photoListJson = new PhotoListJson(context);
        }
        return photoListJson;
    }

    public List<PhotoModel> readJsonPhotoListModles(String res) {
        photoModles.clear();
        try {
            if (res == null || res.equals("")) {
                return null;
            }
            PhotoModel photoModle = null;
            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                photoModle = readJsonPhotoModle(jsonArray.getJSONObject(i));
                photoModles.add(photoModle);
            }
        } catch (Exception e) {

        } finally {
            System.gc();
        }
        return photoModles;
    }

    private PhotoModel readJsonPhotoModle(JSONObject jsonObject) throws Exception {

        PhotoModel photoModle = null;

        String setid = "";
        String seturl = "";
        String clientcover = "";
        String setname = "";

        setid = getString("setid", jsonObject);
        seturl = getString("seturl", jsonObject);
        clientcover = getString("clientcover1", jsonObject);
        setname = getString("datetime", jsonObject);

        setname = setname.split(" ")[0];

        photoModle = new PhotoModel();

        photoModle.setClientcover(clientcover);
        photoModle.setSetid(setid);
        photoModle.setSetname(setname);
        photoModle.setSeturl(seturl);

        return photoModle;
    }
}
