package com.micky.commonproj.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.micky.commonlib.config.Url;
import com.micky.commonlib.utils.StringUtils;
import com.micky.commonproj.ui.activity.BaseActivity;


/**
 * Created by dawn-pc on 2016/5/5.
 */
public class BaseFragment extends Fragment {
    public View mView;
    /**
     * 当前页
     */
    public int currentPagte = 1;

    public BaseActivity getMyActivity() {
        return (BaseActivity) getActivity();
    }

    public String getUrlByParameter(String type, String index){
        String urlString = "";

        switch (type){
            case "1"://头条
                urlString = Url.TopUrl + Url.TopId + "/" + index + Url.endUrl;
                break;
            case "2"://足球
                urlString = Url.CommonUrl + Url.FootId + "/" + index + Url.endUrl;
                break;
            case "3"://娱乐
                urlString = Url.CommonUrl + Url.YuLeId + "/" + index + Url.endUrl;
                break;
            case "4"://体育
                urlString = Url.CommonUrl + Url.TiYuId + "/" + index + Url.endUrl;
                break;
            case "5"://财经
                urlString = Url.CommonUrl + Url.CaiJingId + "/" + index + Url.endUrl;
                break;
            case "6"://科技
                urlString = Url.CommonUrl + Url.KeJiId + "/" + index + Url.endUrl;
                break;
            case "7"://CBA
                urlString = Url.CommonUrl + Url.CBAId + "/" + index + Url.endUrl;
                break;
            case "8"://笑话
                urlString = Url.CommonUrl + Url.XiaoHuaId + "/" + index + Url.endUrl;
                break;
            case "9"://汽车
                urlString = Url.CommonUrl + Url.QiChiId + "/" + index + Url.endUrl;
                break;
            case "10"://时尚
                urlString = Url.CommonUrl + Url.ShiShangId + "/" + index + Url.endUrl;
                break;
            case "11"://北京
                urlString = Url.Local + Url.BeiJingId + "/" + index + Url.endUrl;
                break;
            case "12"://军事
                urlString = Url.Local + Url.JunShiId + "/" + index + Url.endUrl;
                break;
            case "13"://房产
                urlString = Url.FangChan + Url.FangChanId + "/" + index + Url.endUrl;
                break;
            case "14"://游戏
                urlString = Url.CommonUrl + Url.LvYouId + "/" + index + Url.endUrl;
                break;
            case "15"://精选
                urlString = Url.CommonUrl + Url.JingXuanId + "/" + index + Url.endUrl;
                break;
            case "16"://电台
                urlString = Url.CommonUrl + Url.DianTaiId + "/" + index + Url.endUrl;
                break;
            case "17"://情感
                urlString = Url.CommonUrl + Url.QingGanId + "/" + index + Url.endUrl;
                break;
            case "18"://电影
                urlString = Url.CommonUrl + Url.DianYingId + "/" + index + Url.endUrl;
                break;
            case "19"://NBA
                urlString = Url.CommonUrl + Url.NBAId + "/" + index + Url.endUrl;
                break;
            case "20"://数码
                urlString = Url.CommonUrl + Url.ShuMaId + "/" + index + Url.endUrl;
                break;
            case "21"://移动
                urlString = Url.CommonUrl + Url.YiDongId + "/" + index + Url.endUrl;
                break;
            case "22"://彩票
                urlString = Url.CommonUrl + Url.CaiPiaoId + "/" + index + Url.endUrl;
                break;
            case "23"://教育
                urlString = Url.CommonUrl + Url.JiaoYuId + "/" + index + Url.endUrl;
                break;
            case "24"://论坛
                urlString = Url.CommonUrl + Url.LunTanId + "/" + index + Url.endUrl;
                break;
            case "25"://旅游
                urlString = Url.CommonUrl + Url.LvYouId + "/" + index + Url.endUrl;
                break;
            case "26"://手机
                urlString = Url.CommonUrl + Url.ShouJiId + "/" + index + Url.endUrl;
                break;
            case "27"://博客
                urlString = Url.CommonUrl + Url.BoKeId + "/" + index + Url.endUrl;
                break;
            case "28"://社会
                urlString = Url.CommonUrl + Url.SheHuiId + "/" + index + Url.endUrl;
                break;
            case "29"://家居
                urlString = Url.CommonUrl + Url.JiaJuId + "/" + index + Url.endUrl;
                break;
            case "30"://暴雪
                urlString = Url.CommonUrl + Url.BaoXueId + "/" + index + Url.endUrl;
                break;
            case "31"://亲子
                urlString = Url.CommonUrl + Url.QinZiId + "/" + index + Url.endUrl;
                break;
            case "32"://时尚
                urlString = Url.CommonUrl + Url.ShiShangId + "/" + index + Url.endUrl;
                break;
            case "33"://CBA
                urlString = Url.CommonUrl + Url.CBAId + "/" + index + Url.endUrl;
                break;
            case "34"://消息
                urlString = Url.CommonUrl + Url.MsgId + "/" + index + Url.endUrl;
                break;
        }
        return urlString;
    }

    public String getNewUrl(String index) {
        String urlString = Url.TopUrl + Url.TopId + "/" + index + Url.endUrl;
        return urlString;
    }

    public String getCommonUrl(String index, String itemId) {
        String urlString = Url.CommonUrl + itemId + "/" + index + Url.endUrl;
        return urlString;
    }

    public String getLocalUrl(String index, String itemId) {
        String urlString = Url.Local + itemId + "/" + index + Url.endUrl;
        return urlString;
    }

    public String getFangUrl(String index, String itemId) {
        String urlString = Url.FangChan + itemId + "/" + index + Url.endUrl;
        return urlString;
    }

    public String getPhotosUrl(String index) {
        String urlString = Url.TuJi + index + Url.TuJiEnd;
        return urlString;
    }

    public String getReDianPicsUrl(String index) {
        String urlString = Url.TuPianReDian + index + Url.TuJiEnd;
        return urlString;
    }

    public String getDuJiaPicsUrl(String index) {
        String urlString = Url.TuPianDuJia + index + Url.TuJiEnd;
        return urlString;
    }

    public String getMingXingPicsUrl(String index) {
        String urlString = Url.TuPianMingXing + index + Url.TuJiEnd;
        return urlString;
    }

    public String getTiTanPicsUrl(String index) {
        String urlString = Url.TuPianTiTan + index + Url.TuJiEnd;
        return urlString;
    }

    public String getMeiTuPicsUrl(String index) {
        String urlString = Url.TuPianMeiTu + index + Url.TuJiEnd;
        return urlString;
    }

    public String getSinaJingXuan(String index) {
        String urlString = Url.JINGXUAN_ID + index;
        return urlString;
    }

    public String getSinaQuTu(String index) {
        String urlString = Url.QUTU_ID + index;
        return urlString;
    }

    public String getSinaMeiTu(String index) {
        String urlString = Url.MEITU_ID + index;
        return urlString;
    }

    public String getSinaGuShi(String index) {
        String urlString = Url.GUSHI_ID + index;
        return urlString;
    }

    // ��Ƶ http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public String getVideoUrl(String index, String videoId) {
        String urlString = Url.Video + videoId + Url.VideoCenter + index + Url.videoEndUrl;
        return urlString;
    }

    public boolean isNullString(String imgUrl) {

        if (StringUtils.isEmpty(imgUrl)) {
            return true;
        }
        return false;
    }
}
