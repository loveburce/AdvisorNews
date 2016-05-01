package com.dawn.apollo.apollo.http.json;

import android.content.Context;

import com.dawn.apollo.apollo.bean.VideoModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/1.
 */
public class VideoListJson extends JsonPacket{
    public static VideoListJson newListJson;

    public List<VideoModel> videoModles;

    public VideoListJson(Context context) {
        super(context);
    }

    public static VideoListJson instance(Context context) {
        if (newListJson == null) {
            newListJson = new VideoListJson(context);
        }
        return newListJson;
    }

    public List<VideoModel> readJsonVideoModles(String res, String value) {
        videoModles = new ArrayList<VideoModel>();
        try {
            if (res == null || res.equals("")) {
                return null;
            }
            VideoModel videoModel = null;
            JSONObject jsonObject = new JSONObject(res);
            JSONArray jsonArray = jsonObject.getJSONArray(value);
            for (int i = 0; i < jsonArray.length(); i++) {
                videoModel = readVideoModle(jsonArray.getJSONObject(i));
                videoModles.add(videoModel);
            }
        } catch (Exception e) {

        } finally {
            System.gc();
        }
        return videoModles;
    }

    /**
     * 获取图文列表
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    public VideoModel readVideoModle(JSONObject jsonObject) throws Exception {
        VideoModel videoModel = null;

        String vid = "";
        String title = "";
        int length = 0;
        String cover = "";
        String mp4Hd_url = "";

        vid = getString("vid", jsonObject);
        title = getString("title", jsonObject);
        length = getInt("length", jsonObject);
        cover = getString("cover", jsonObject);
        mp4Hd_url = getString("mp4_url", jsonObject);

        videoModel = new VideoModel();

        videoModel.setCover(cover);
        if (length == -1) {
            videoModel.setTitle(getString("sectiontitle", jsonObject));
            videoModel.setLength(getTitle(title));
        } else {
            videoModel.setLength(getTime(length));
            videoModel.setTitle(getTitle(title));
        }
        videoModel.setMp4Hd_url(mp4Hd_url);
        videoModel.setVid(vid);

        return videoModel;
    }

    public String getTitle(String title) {
        if (title.contains("&quot;")) {
            title = title.replace("&quot;", "\"");
        }
        return title;
    }

    public String getTime(int length) {
        int fen = length / 60;
        int miao = length % 60;
        String fenString = fen + "";
        String miaoString = miao + "";
        fenString = fenString.length() == 1 ? "0" + fenString : fenString;
        miaoString = miaoString.length() == 1 ? miaoString + "0" : miaoString;
        return fenString + ":" + miaoString;
    }
}
