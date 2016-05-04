package com.micky.commonproj.presenter.impl;

import com.micky.commonlib.utils.RxBus;
import com.micky.commonproj.presenter.NewPresenter;
import com.micky.commonproj.presenter.NewView;

import org.apache.log4j.Logger;

import rx.functions.Action1;

/**
 * Created by dawn-pc on 2016/5/4.
 */
public class NewPresenterImpl extends BasePresenterImpl implements NewPresenter {
    private final Logger mLogger = Logger.getLogger(getClass());

    private NewView mNewView;

    public NewPresenterImpl(NewView newView) {
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

    }

    static class RefreshEvent extends RxBus.BusEvent{}
}
