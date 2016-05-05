package com.micky.commonproj.presenter.impl;

import com.micky.commonlib.utils.RxBus;
import com.micky.commonproj.BaseApplication;
import com.micky.commonproj.domain.model.ChannelItem;
import com.micky.commonproj.domain.model.Place;
import com.micky.commonproj.domain.service.response.PlaceRepository;
import com.micky.commonproj.domain.service.response.TitleRepository;
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
//                    getPlaceAndWeatherData("成都");
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
    public void getTitleData() {
        TitleRepository repository = new TitleRepository();
        mSubscriptions.add(repository.getUserTileList(BaseApplication.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ChannelItem>>() {
                    @Override
                    public void onNext(List<ChannelItem> channelItemList) {
                        mNewView.setupTitleData(channelItemList);
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
