package com.micky.commonproj.presenter;

import com.micky.commonproj.domain.model.Place;
import com.micky.commonproj.domain.service.response.WeatherResponse;

import java.util.List;

/**
 * Created by dawn-pc on 2016/5/4.
 */
public interface NewView {
    void showProgress();
    void hideProgress();
    void setupTitleData(List<Place> placeList);
}
