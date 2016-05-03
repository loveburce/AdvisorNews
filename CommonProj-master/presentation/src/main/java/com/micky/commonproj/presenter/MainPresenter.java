package com.micky.commonproj.presenter;

/**
 * @Project CommonProject
 * @Packate com.micky.presentation
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 13:46
 * @Version 1.0
 */
public interface MainPresenter extends BasePresenter {

    void getWeatherData(String place);

    void getPlaceData();

    void getPlaceAndWeatherData(String place);

    void onRefresh();
}
