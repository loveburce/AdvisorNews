package com.micky.commonproj.presenter.impl;

import com.micky.commonlib.http.model.NewModle;
import com.micky.commonlib.utils.RxBus;
import com.micky.commonproj.BaseApplication;
import com.micky.commonproj.domain.service.response.WebDataResponse;
import com.micky.commonproj.presenter.NewsPresenter;
import com.micky.commonproj.presenter.NewsView;

import org.apache.log4j.Logger;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dawn-pc on 2016/5/4.
 */
public class NewsPresenterImpl extends BasePresenterImpl implements NewsPresenter {
    private final Logger mLogger = Logger.getLogger(getClass());

    private NewsView mNewView;

    public NewsPresenterImpl(NewsView newView) {
        mNewView = newView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSubscriptions.add(RxBus.getInstance().toObserverable().subscribe(new Action1<RxBus.BusEvent>() {
            @Override
            public void call(RxBus.BusEvent rxBusEvent) {
                if (rxBusEvent instanceof RefreshEvent) {
                }
            }
        }));
    }



    @Override
    public void onRefresh() {
        RxBus rxBus = RxBus.getInstance();
        if (rxBus.hasObservers()) {
            rxBus.send(new RefreshEvent());
        }
    }

    @Override
    public void getNewsData(String url,String keyId) {
        WebDataResponse repository = new WebDataResponse();

        mSubscriptions.add(repository.getNewsModelList(BaseApplication.getInstance(),url,keyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewModle>>() {
                    @Override
                    public void onNext(List<NewModle> newModleList) {
                        mNewView.setupNewsData(newModleList);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mLogger.error(e.getMessage(), e);
                    }
                }));
    }

    static class RefreshEvent extends RxBus.BusEvent{}
}
