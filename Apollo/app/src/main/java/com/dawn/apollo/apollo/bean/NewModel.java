package com.dawn.apollo.apollo.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class NewModel extends BaseModel {
    private String docid;
    private String title;
    private String digest;
    private String imgsrc;
    private String source;
    private String ptime;
    private String tag;
    private ImagesModel imagesModle;
    private List<ImagesModel> imgHeadLists;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ImagesModel getImagesModle() {
        return imagesModle;
    }

    public void setImagesModle(ImagesModel imagesModle) {
        this.imagesModle = imagesModle;
    }

    public List<ImagesModel> getImgHeadLists() {
        return imgHeadLists;
    }

    public void setImgHeadLists(List<ImagesModel> imgHeadLists) {
        this.imgHeadLists = imgHeadLists;
    }
}
