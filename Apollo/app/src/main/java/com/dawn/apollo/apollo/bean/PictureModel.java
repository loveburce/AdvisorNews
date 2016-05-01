package com.dawn.apollo.apollo.bean;

/**
 * Created by Administrator on 2016/5/1.
 */
public class PictureModel extends BaseModel{
    private String id;
    private String title;
    private String pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
