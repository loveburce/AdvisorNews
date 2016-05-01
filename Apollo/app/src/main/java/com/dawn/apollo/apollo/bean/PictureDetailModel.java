package com.dawn.apollo.apollo.bean;

/**
 * Created by Administrator on 2016/5/1.
 */
public class PictureDetailModel extends BaseModel{
    private String title;
    private String pic;
    private String alt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
