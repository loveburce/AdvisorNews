package com.micky.commonproj.presenter;

import com.micky.commonlib.http.model.NewDetailModle;
import com.micky.commonlib.http.model.NewModle;

import java.util.List;

/**
 * Created by dawn-pc on 2016/5/4.
 */
public interface NewsDetailView {
    void showProgress();
    void hideProgress();
    void setupNewsData(NewDetailModle result);
}
