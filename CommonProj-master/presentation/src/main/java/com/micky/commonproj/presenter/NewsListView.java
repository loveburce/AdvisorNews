package com.micky.commonproj.presenter;

import com.micky.commonlib.http.model.NewModle;

import java.util.List;

/**
 * Created by dawn-pc on 2016/5/4.
 */
public interface NewsListView {
    void showProgress();
    void hideProgress();
    void setupNewsData(List<NewModle> placeList);
}
