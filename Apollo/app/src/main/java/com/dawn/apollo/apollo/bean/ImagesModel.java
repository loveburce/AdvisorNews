package com.dawn.apollo.apollo.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class ImagesModel extends BaseModel {
    private String docid;
    private String title;
    private List<String> imgList;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
