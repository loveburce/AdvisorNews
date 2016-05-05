package com.micky.commonproj.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.micky.commonlib.config.Constants;
import com.micky.commonlib.utils.RxBus;
import com.micky.commonproj.BaseApplication;
import com.micky.commonproj.domain.model.Place;
import com.micky.commonproj.domain.service.response.PlaceRepository;
import com.micky.commonproj.domain.service.WebDataService;
import com.micky.commonproj.domain.service.response.WeatherResponse;
import com.micky.commonproj.presenter.MainPresenter;
import com.micky.commonproj.domain.service.ServiceManager;
import com.micky.commonproj.presenter.MainView;

import org.apache.log4j.Logger;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @Project CommonProject
 * @Packate com.micky.commonproj.presenter
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 14:33
 * @Version 1.0
 */
public class MainPresenterImpl extends BasePresenterImpl implements MainPresenter {
    private final Logger mLogger = Logger.getLogger(getClass());

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSubscriptions.add(RxBus.getInstance().toObserverable().subscribe(new Action1<RxBus.BusEvent>() {
            @Override
            public void call(RxBus.BusEvent rxBusEvent) {
                if (rxBusEvent instanceof RefreshEvent) {
                    getPlaceAndWeatherData("成都");
                }
            }
        }));
    }

    @Override
    public void getWeatherData(String place) {
        if (TextUtils.isEmpty(place)) {
            return;
        }
        mMainView.showProgress();
        mSubscriptions.add(ServiceManager.createService(BaseApplication.getInstance(), WebDataService.class).getWeatherInfo(place, Constants.BAIDU_AK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherResponse>() {
                    @Override
                    public void onCompleted() {
                        mMainView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLogger.error(e.getMessage(), e);
                        mMainView.hideProgress();
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        mMainView.setupWeatherData(weatherResponse);
                    }
                }));
    }

    @Override
    public void getPlaceData() {
        PlaceRepository repository = new PlaceRepository();
        mSubscriptions.add(repository.getPlaceList(BaseApplication.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Place>>() {
                    @Override
                    public void onNext(List<Place> places) {
                        mMainView.setupPlaceData(places);
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

    @Override
    public void getPlaceAndWeatherData(String place) {
        mMainView.showProgress();
        PlaceRepository repository = new PlaceRepository();
        Context context = BaseApplication.getInstance();
        Observable placeObservable = repository.getPlaceList(context);
        Observable weatherObservable =  ServiceManager.createService(BaseApplication.getInstance(), WebDataService.class).getWeatherInfo(place, Constants.BAIDU_AK);

        mSubscriptions.add(Observable.merge(placeObservable, weatherObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        mMainView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLogger.error(e.getMessage(), e);
                        mMainView.hideProgress();
                    }

                    @Override
                    public void onNext(Object obj) {
                        if (obj instanceof List) {
                            mMainView.setupPlaceData((List<Place>) obj);
                        } else if (obj instanceof WeatherResponse) {
                            mMainView.setupWeatherData((WeatherResponse) obj);
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

    static class RefreshEvent extends RxBus.BusEvent{}
}
