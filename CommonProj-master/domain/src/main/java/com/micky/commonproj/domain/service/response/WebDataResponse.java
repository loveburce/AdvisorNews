package com.micky.commonproj.domain.service.response;

import android.content.Context;
import android.util.Log;

import com.micky.commonlib.config.Url;
import com.micky.commonlib.http.HttpUtil;
import com.micky.commonlib.http.json.NewDetailJson;
import com.micky.commonlib.http.json.NewListJson;
import com.micky.commonlib.http.model.NewDetailModle;
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
    public Observable<List<NewModle>> getNewsModelList(final Context context, final String url,final String keyId) {
        return Observable.create(new Observable.OnSubscribe<List<NewModle>>() {
            @Override
            public void call(Subscriber<? super List<NewModle>> subscriber) {
                try {
                    List<NewModle> newModleList;
                    String result = HttpUtil.getByHttpClient(context, url);

                    newModleList = NewListJson.instance(context).readJsonNewModles(result, keyId);

                    Log.d("placeList", "placeListplaceList:result " + result);
                    Log.d("placeList","placeListplaceList:newModleList "+newModleList.size());

                    subscriber.onNext(newModleList);
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }

    public Observable<NewDetailModle> getNewsDetail(final Context context, final String url, final String keyId) {
        return Observable.create(new Observable.OnSubscribe<NewDetailModle>() {
            @Override
            public void call(Subscriber<? super NewDetailModle> subscriber) {
                try {
                    String result = HttpUtil.getByHttpClient(context, url);
                    Log.d("placeList", "placeListplaceList:result " + result);
                    NewDetailModle newDetailModle = NewDetailJson.instance(context).readJsonNewModles(result, keyId);

                    subscriber.onNext(newDetailModle);
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }
}
