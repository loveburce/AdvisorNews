package com.dawn.apollo.apollo.bean;

/**
 * Created by Administrator on 2016/5/1.
 */
public class PhotoModel extends BaseModel{
    private String setid;
    private String seturl;
    private String clientcover;
    private String setname;

    public String getSetid() {
        return setid;
    }

    public void setSetid(String setid) {
        this.setid = setid;
    }

    public String getSeturl() {
        return seturl;
    }

    public void setSeturl(String seturl) {
        this.seturl = seturl;
    }

    public String getClientcover() {
        return clientcover;
    }

    public void setClientcover(String clientcover) {
        this.clientcover = clientcover;
    }

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }
}
