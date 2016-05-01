package com.dawn.apollo.apollo.http.json;

import android.content.Context;

import com.dawn.apollo.apollo.bean.PictureDetailModel;
import com.dawn.apollo.apollo.bean.PictureModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/1.
 */
public class PictureSinaJson extends JsonPacket{
    private final List<PictureModel> PicuterModles = new ArrayList<PictureModel>();

    private List<PictureDetailModel> picuterDetailModles;

    public static PictureSinaJson picuterJson;

    private String title;

    public PictureSinaJson(Context context) {
        super(context);
    }

    public static PictureSinaJson instance(Context context) {
        if (picuterJson == null) {
            picuterJson = new PictureSinaJson(context);
        }
        return picuterJson;
    }

    public List<PictureDetailModel> readJsonPicuterModle(String res) {
        picuterDetailModles = new ArrayList<PictureDetailModel>();
        try {
            if (res == null || res.equals("")) {
                return null;
            }
            PictureDetailModel picuterDetailModle = null;
            JSONObject jsonObject = new JSONObject(res).getJSONObject("data");
            title = getString("title", jsonObject);
            JSONArray jsonArray = jsonObject.getJSONArray("pics");
            for (int i = 0; i < jsonArray.length(); i++) {
                picuterDetailModle = readJsonPicuterDetailModle(jsonArray.getJSONObject(i));
                picuterDetailModles.add(picuterDetailModle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.gc();
        }
        return picuterDetailModles;
    }

    /**
     * 得到图片单个详情
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    private PictureDetailModel readJsonPicuterDetailModle(JSONObject jsonObject) throws Exception {

        PictureDetailModel pictureDetailModel = null;

        String pic = "";
        String alt = "";

        pic = getString("pic", jsonObject);
        alt = getString("alt", jsonObject);

        pictureDetailModel = new PictureDetailModel();

        pictureDetailModel.setAlt(alt);
        pictureDetailModel.setPic(pic);
        pictureDetailModel.setTitle(title);

        return pictureDetailModel;
    }

    /**
     * 得到图片列表
     *
     * @param res
     * @return
     */
    public List<PictureModel> readJsonPhotoListModles(String res) {
        PicuterModles.clear();
        try {
            if (res == null || res.equals("")) {
                return null;
            }
            PictureModel PicuterModle = null;
            JSONArray jsonArray = new JSONObject(res).getJSONObject("data").getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                PicuterModle = readJsonPicuterModle(jsonArray.getJSONObject(i));
                PicuterModles.add(PicuterModle);
            }
        } catch (Exception e) {

        } finally {
            System.gc();
        }
        return PicuterModles;
    }

    /**
     * 图片列表详情
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    private PictureModel readJsonPicuterModle(JSONObject jsonObject) throws Exception {

        PictureModel picuterModle = null;

        String id = "";
        String title = "";
        String pic = "";

        id = getString("id", jsonObject);
        title = getString("title", jsonObject);
        pic = getString("pic", jsonObject);

        picuterModle = new PictureModel();

        picuterModle.setId(id);
        picuterModle.setPic(pic);
        picuterModle.setTitle(title);

        return picuterModle;
    }
}
