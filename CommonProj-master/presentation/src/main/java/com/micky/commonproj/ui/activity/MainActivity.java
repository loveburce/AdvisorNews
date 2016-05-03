package com.micky.commonproj.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;

import com.micky.commonproj.domain.service.response.WeatherResponse;
import com.micky.commonproj.presenter.MainPresenter;
import com.micky.commonproj.ui.adapter.PlaceAdapter;
import com.micky.commonproj.ui.adapter.WeatherDataAdapter;
import com.micky.commonproj.ui.adapter.WeatherExtraAdapter;
import com.micky.commonproj.ui.fragment.HelpFragment;
import com.micky.commonproj.ui.fragment.NewsFragment;
import com.micky.commonproj.R;
import com.micky.commonproj.ui.fragment.SearchFragment;
import com.micky.commonproj.ui.fragment.ThemeFragment;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

/**
 * @Project CommonProject
 * @Packate com.micky.presentation
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 12:22
 * @Version 1.0
 */
public class MainActivity extends BaseActivity {

//    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
//    @Bind(R.id.rv_place) RecyclerView mRvPlace;
//    @Bind(R.id.rv_weather_data) RecyclerView mRvWeatherData;
//    @Bind(R.id.recyvler_view) RecyclerView mRvWeatherExtra;
//    @Bind(R.id.progress_bar) ProgressBar mProgressBar;
//    @Bind(R.id.tv_city) TextView mTvCity;
//    @Bind(R.id.tv_pm25) TextView mTvPm25;
////    @Bind(R.id.fab) FloatingActionButton mFloatingActionBar;
//    @Bind(R.id.tv_empty_data) TextView mTvEmptyData;

    private MainPresenter mMainPresenter;
    private WeatherExtraAdapter mWeatherExtraAdapter;
    private WeatherDataAdapter mWeatherDataAdapter;
    private PlaceAdapter mPlaceAdapter;

    int[] testColors = {0xFF00796B,0xFF8D6E63,0xFF2196F3,0xFF607D8B,0xFFF57C00};

    Controller controller;
    List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, true);
        setTranslucentNavigation(true);

//        final ActionBar ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.mipmap.menu);
//        ab.setDisplayHomeAsUpEnabled(true);

//        mDrawerLayout.setVisibility(View.GONE);

//        initView();

//        mMainPresenter = new MainPresenterImpl(this);
//        mMainPresenter.onCreate();
//        mMainPresenter.getPlaceData();
//        mMainPresenter.getWeatherData("北京");

//        mMainPresenter.getPlaceAndWeatherData("北京");
//        RxView.clicks(mFloatingActionBar).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                mMainPresenter.onRefresh();
//            }
//        });

        initFragment();

        BottomTabTest();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (ViewUtils.isFastClick()) return true;
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                break;
//            case R.id.weather:
//                mDrawerLayout.openDrawer(GravityCompat.END);
//                break;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mMainPresenter != null) {
//            mMainPresenter.onDestroy();
//        }
    }

    private void initView() {
//        mRvPlace.setHasFixedSize(true);
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        mRvPlace.setLayoutManager(staggeredGridLayoutManager);
//        mPlaceAdapter = new PlaceAdapter();
//        mRvPlace.setAdapter(mWeatherDataAdapter);
//        mPlaceAdapter.setOnPlaceClickListener(new PlaceAdapter.OnPlaceClickListener() {
//            @Override
//            public void onClick(View view, Place place) {
//                mMainPresenter.getWeatherData(place.getName());
//                mDrawerLayout.closeDrawer(GravityCompat.START);
//            }
//        });
//        mRvPlace.setAdapter(mPlaceAdapter);
//
//        mRvWeatherData.setHasFixedSize(true);
//        mRvWeatherData.addItemDecoration(new ItemDecoration(this));
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRvWeatherData.setLayoutManager(linearLayoutManager);
//        mWeatherDataAdapter = new WeatherDataAdapter();
//        mRvWeatherData.setAdapter(mWeatherDataAdapter);
//
//        mRvWeatherExtra.setHasFixedSize(true);
//        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRvWeatherExtra.setLayoutManager(linearLayoutManager);
//        mWeatherExtraAdapter = new WeatherExtraAdapter();
//        mRvWeatherExtra.setAdapter(mWeatherExtraAdapter);
    }

    public void setupWeatherData(WeatherResponse weatherResponse) {
//        int visibility = View.GONE;
//        if (weatherResponse == null) return;
//        setTitleText(DateUtils.getWeekDay(weatherResponse.date));
//        if (weatherResponse.results != null && weatherResponse.results.size() > 0) {
//            WeatherResult result = weatherResponse.results.get(0);
//            mTvCity.setText(getString(R.string.city, result.currentCity));
//            mTvPm25.setText(getString(R.string.pm25, result.pm25));
//
//            mWeatherDataAdapter.setData(result.weather_data);
//            mWeatherDataAdapter.notifyDataSetChanged();
//
//            mWeatherExtraAdapter.setData(result.index);
//            mWeatherExtraAdapter.notifyDataSetChanged();
//            visibility = result.index.size() > 0 ? View.GONE : View.VISIBLE;
//        }
//        mTvEmptyData.setVisibility(visibility);
    }

//    @Override
//    public void setupPlaceData(List<Place> placeList) {
//        if (placeList == null) {
//            return;
//        }
//        mPlaceAdapter.setData(placeList);
//        mPlaceAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void showProgress() {
//        mProgressBar.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hideProgress() {
//        mProgressBar.setVisibility(View.GONE);
//    }

    private void initFragment() {
        mFragments = new ArrayList<>();

        mFragments.add(new NewsFragment());
        mFragments.add(new ThemeFragment());
        mFragments.add(new SearchFragment());
        mFragments.add(new HelpFragment());
//        mFragments.add(createFragment("E"));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.push_up_in,R.anim.push_up_out);
        transaction.add(R.id.frameLayout,mFragments.get(0));
        transaction.commit();
    }

    private void BottomTabTest()
    {
        PagerBottomTabLayout pagerBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);

        //用TabItemBuilder构建一个导航按钮
        TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
                .setDefaultIcon(android.R.drawable.ic_menu_send)
                .setText("信息")
                .setSelectedColor(testColors[0])
                .setTag("A")
                .build();

        //构建导航栏,得到Controller进行后续控制
        controller = pagerBottomTabLayout.builder()
                .addTabItem(tabItemBuilder)
                .addTabItem(android.R.drawable.ic_menu_compass, "主题",testColors[1])
                .addTabItem(android.R.drawable.ic_menu_search, "搜索",testColors[2])
                .addTabItem(android.R.drawable.ic_menu_help, "帮助",testColors[3])
//                .setMode(TabLayoutMode.HIDE_TEXT)
//                .setMode(TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .setMode(TabLayoutMode.HIDE_TEXT| TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();

//        controller.setMessageNumber("A",2);
//        controller.setDisplayOval(0,true);

        controller.addTabItemClickListener(listener);
    }



    OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag)
        {
            Log.i("asd", "onSelected:" + index + "   TAG: " + tag.toString());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.push_up_in,R.anim.push_up_out);
            transaction.replace(R.id.frameLayout,mFragments.get(index));
            transaction.commit();
        }

        @Override
        public void onRepeatClick(int index, Object tag) {
            Log.i("asd","onRepeatClick:"+index+"   TAG: "+tag.toString());
        }
    };

}
