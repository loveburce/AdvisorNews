package com.dawn.apollo.apollo.dao;

import android.content.ContentValues;

import com.dawn.apollo.apollo.datamodel.ChannelItem;

import java.util.List;
import java.util.Map;

/**
 * Created by dawn-pc on 2016/4/29.
 */
public interface ChannelDaoInterface {
    public boolean addCache(ChannelItem channelItem);

    public boolean deleteCache(String whereClause,String[] whereArgs);

    public boolean updateCache(ContentValues values,String whereClause,String[] whereArgs);

    public Map<String,String>viewCache(String selection,String[] selectionArgs);

    public List<Map<String,String>>listCache(String selection,String[] selectionArgs);

    public void clearFeedTable();
}
