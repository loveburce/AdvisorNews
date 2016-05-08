package com.micky.commonproj.domain.service.response;

import android.content.Context;

import com.micky.commonlib.config.Url;
import com.micky.commonlib.http.HttpUtil;
import com.micky.commonlib.http.json.NewListJson;
import com.micky.commonlib.http.model.NewModle;
//import com.micky.commonproj.domain.http.HttpUtil;
//import com.micky.commonproj.domain.http.json.NewListJson;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dawn-pc on 2016/5/8.
 */
public class WebDataResponse {
    public Observable<List<NewModle>> getNewsModelList(final Context context, final String url) {
        return Observable.create(new Observable.OnSubscribe<List<NewModle>>() {
            @Override
            public void call(Subscriber<? super List<NewModle>> subscriber) {
                try {
                    List<NewModle> newModleList;
                    String result = HttpUtil.getByHttpClient(context, url);
                    newModleList = NewListJson.instance(context).readJsonNewModles(result, Url.TopId);

                    subscriber.onNext(newModleList);
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }
}
