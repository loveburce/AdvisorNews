package com.micky.commonproj.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.micky.commonproj.R;
import com.micky.commonproj.domain.model.Place;
import com.micky.commonproj.domain.service.response.WeatherResponse;
import com.micky.commonproj.presenter.MainPresenter;
import com.micky.commonproj.presenter.MainView;
import com.micky.commonproj.presenter.impl.MainPresenterImpl;
import com.micky.commonproj.ui.adapter.PlaceAdapter;
import com.micky.commonproj.ui.adapter.WeatherDataAdapter;
import com.micky.commonproj.ui.adapter.WeatherExtraAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by dawn-pc on 2016/5/3.
 */
public class HelpFragment extends Fragment implements MainView {
    private Context mContext;
    private View rootView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.rv_place)
    RecyclerView mRvPlace;
    @Bind(R.id.rv_weather_data) RecyclerView mRvWeatherData;
    @Bind(R.id.recyvler_view) RecyclerView mRvWeatherExtra;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.tv_pm25) TextView mTvPm25;

    @Bind(R.id.tv_empty_data) TextView mTvEmptyData;

    private MainPresenter mMainPresenter;
    private WeatherExtraAdapter mWeatherExtraAdapter;
    private WeatherDataAdapter mWeatherDataAdapter;
    private PlaceAdapter mPlaceAdapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_help, null);

        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.onCreate();
//        mMainPresenter.getPlaceData();
//        mMainPresenter.getWeatherData("北京");

        mMainPresenter.getPlaceAndWeatherData("北京");
//        RxView.clicks(mFloatingActionBar).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                mMainPresenter.onRefresh();
//            }
//        });

        return rootView;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setupPlaceData(List<Place> placeList) {

    }

    @Override
    public void setupWeatherData(WeatherResponse response) {

    }
}
