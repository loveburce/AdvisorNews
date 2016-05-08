package com.micky.commonproj.domain.service.response;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.micky.commonproj.domain.db.DBCore;
import com.micky.commonproj.domain.db.dao.DaoSession;
import com.micky.commonproj.domain.model.ChannelItem;
import com.micky.commonproj.domain.model.Place;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dawn-pc on 2016/5/5.
 */
public class TitleRepository {

    public Observable<List<ChannelItem>> getUserTileList(final Context context) {
        return Observable.create(new Observable.OnSubscribe<List<ChannelItem>>() {
            @Override
            public void call(Subscriber<? super List<ChannelItem>> subscriber) {
                try {
                    List<ChannelItem> defaultUserChannels;
                    DaoSession daoSession = DBCore.getDaoSession();
                    defaultUserChannels = daoSession.getChannelItemDao().queryRaw(" where selected = ?",new String[] {"1"});
                    subscriber.onNext(defaultUserChannels);
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }
}
