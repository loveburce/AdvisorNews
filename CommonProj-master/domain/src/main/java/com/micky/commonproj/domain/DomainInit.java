package com.micky.commonproj.domain;

import android.content.Context;

import com.micky.commonproj.domain.db.DBCore;
import com.micky.commonproj.domain.db.dao.DaoMaster;
import com.micky.commonproj.domain.db.dao.DaoSession;
import com.micky.commonproj.domain.model.ChannelItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain
 *
 * @Description Domain 相关初始化
 *
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-29 14:34
 * @Version 1.0
 */
public class DomainInit {

    public static List<ChannelItem> defaultUserChannels;
    /**
     * 默认的其他频道列表
     */
    public static List<ChannelItem> defaultOtherChannels;
    static DaoSession daoSession;

    public static void init(Context context) {
        initDatabase(context);
    }

    /**
     * 初始化数据库
     * @param context
     */
    public static void initDatabase(Context context) {
        new DaoMaster.DevOpenHelper(context, "notes-db", null);

        DBCore.init(context);
        DBCore.enableQueryBuilderLog();

        daoSession = DBCore.getDaoSession();
        initChannelItem();
    }

    private static void initChannelItem(){
        defaultUserChannels = new ArrayList<ChannelItem>();
        defaultOtherChannels = new ArrayList<ChannelItem>();
        defaultUserChannels.add(new ChannelItem(1, "头条", 1, 1,"http://c.m.163.com/nc/article/headline/","T1348647909107","-20.html"));
        defaultUserChannels.add(new ChannelItem(2, "足球", 2, 1,"http://c.m.163.com/nc/article/list/","T1399700447917","-20.html"));
        defaultUserChannels.add(new ChannelItem(3, "娱乐", 3, 1,"http://c.m.163.com/nc/article/list/","T1348648517839","-20.html"));
        defaultUserChannels.add(new ChannelItem(4, "体育", 4, 1,"http://c.m.163.com/nc/article/list/","T1348649079062","-20.html"));
        defaultUserChannels.add(new ChannelItem(5, "财经", 5, 1,"http://c.m.163.com/nc/article/list/","T1348648756099","-20.html"));
        defaultUserChannels.add(new ChannelItem(6, "科技", 6, 1,"http://c.m.163.com/nc/article/list/","T1348649580692","-20.html"));
        defaultOtherChannels.add(new ChannelItem(7, "CBA", 1, 0,"http://c.m.163.com/nc/article/list/","T1348649475931","-20.html"));
        defaultOtherChannels.add(new ChannelItem(8, "笑话", 2, 0,"http://c.m.163.com/nc/article/list/","T1350383429665","-20.html"));
        defaultOtherChannels.add(new ChannelItem(9, "汽车", 3, 0,"http://c.m.163.com/nc/article/list/","T1348654060988","-20.html"));
        defaultOtherChannels.add(new ChannelItem(10, "时尚", 4, 0,"http://c.m.163.com/nc/article/list/","T1348650593803","-20.html"));

        defaultOtherChannels.add(new ChannelItem(14, "游戏", 8, 0,"http://c.m.163.com/nc/article/list/","T1348654151579","-20.html"));
        defaultOtherChannels.add(new ChannelItem(15, "精选", 9, 0,"http://c.m.163.com/nc/article/list/","T1370583240249","-20.html"));
        defaultOtherChannels.add(new ChannelItem(16, "电台", 10, 0,"http://c.m.163.com/nc/article/list/","T1379038288239","-20.html"));
        defaultOtherChannels.add(new ChannelItem(17, "情感", 11, 0,"http://c.m.163.com/nc/article/list/","T1348650839000","-20.html"));
        defaultUserChannels.add(new ChannelItem(18, "电影", 12, 0,"http://c.m.163.com/nc/article/list/","T1348648650048","-20.html"));
        defaultUserChannels.add(new ChannelItem(19, "NBA", 13, 0,"http://c.m.163.com/nc/article/list/","T1348649145984","-20.html"));
        defaultUserChannels.add(new ChannelItem(20, "数码", 14, 0,"http://c.m.163.com/nc/article/list/","T1348649776727","-20.html"));
        defaultUserChannels.add(new ChannelItem(21, "移动", 15, 0,"http://c.m.163.com/nc/article/list/","T1351233117091","-20.html"));
        defaultUserChannels.add(new ChannelItem(22, "彩票", 16, 0,"http://c.m.163.com/nc/article/list/","T1356600029035","-20.html"));
        defaultUserChannels.add(new ChannelItem(23, "教育", 17, 0,"http://c.m.163.com/nc/article/list/","T1348654225495","-20.html"));
        defaultUserChannels.add(new ChannelItem(24, "论坛", 18, 0,"http://c.m.163.com/nc/article/list/","T1349837670307","-20.html"));
        defaultOtherChannels.add(new ChannelItem(25, "旅游", 19, 0,"http://c.m.163.com/nc/article/list/","T1348654204705","-20.html"));
        defaultOtherChannels.add(new ChannelItem(26, "手机", 20, 0,"http://c.m.163.com/nc/article/list/","T1348649654285","-20.html"));
        defaultOtherChannels.add(new ChannelItem(27, "博客", 21, 0,"http://c.m.163.com/nc/article/list/","T1349837698345","-20.html"));
        defaultOtherChannels.add(new ChannelItem(28, "社会", 22, 0,"http://c.m.163.com/nc/article/list/","T1348648037603","-20.html"));
        defaultOtherChannels.add(new ChannelItem(29, "家居", 23, 0,"http://c.m.163.com/nc/article/list/","T1348654105308","-20.html"));
        defaultOtherChannels.add(new ChannelItem(30, "暴雪", 24, 0,"http://c.m.163.com/nc/article/list/","T1397016069906","-20.html"));
        defaultUserChannels.add(new ChannelItem(31, "亲子", 25, 0,"http://c.m.163.com/nc/article/list/","T1397116135282","-20.html"));
        defaultUserChannels.add(new ChannelItem(32, "CBA", 26, 0,"http://c.m.163.com/nc/article/list/","T1397116135282","-20.html"));
        defaultUserChannels.add(new ChannelItem(33, "消息", 27, 0,"http://c.m.163.com/nc/article/list/","T1397116135282","-20.html"));

        defaultOtherChannels.add(new ChannelItem(11, "北京", 5, 0,"http://c.m.163.com/nc/article/local/","5YyX5Lqs","-20.html"));
        defaultOtherChannels.add(new ChannelItem(12, "军事", 6, 0,"http://c.m.163.com/nc/article/local/","T1348648141035","-20.html"));
        defaultOtherChannels.add(new ChannelItem(13, "房产", 7, 0,"http://c.m.163.com/nc/article/house/","5YyX5Lqs","-20.html"));

        for(ChannelItem channelItem : defaultUserChannels){
            daoSession.insert(channelItem);
        }

        for(ChannelItem channelItem : defaultOtherChannels){
            daoSession.insert(channelItem);
        }
    }

}
