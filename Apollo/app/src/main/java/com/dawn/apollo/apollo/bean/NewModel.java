package com.dawn.apollo.apollo.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class NewModel extends BaseModle{
    private String docid;
    private String title;
    private String digest;
    private String imgsrc;
    private String source;
    private String ptime;
    private String tag;
    private ImagesModle imagesModle;
    private List<ImagesModle> imgHeadLists;

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

    public ImagesModle getImagesModle() {
        return imagesModle;
    }

    public void setImagesModle(ImagesModle imagesModle) {
        this.imagesModle = imagesModle;
    }

    public List<ImagesModle> getImgHeadLists() {
        return imgHeadLists;
    }

    public void setImgHeadLists(List<ImagesModle> imgHeadLists) {
        this.imgHeadLists = imgHeadLists;
    }
}
