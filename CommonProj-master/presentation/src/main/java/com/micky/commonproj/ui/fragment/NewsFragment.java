package com.micky.commonproj.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micky.commonlib.utils.BaseTools;
import com.micky.commonproj.R;
import com.micky.commonproj.domain.model.ChannelItem;
import com.micky.commonproj.presenter.NewsPresenter;
import com.micky.commonproj.presenter.NewsView;
import com.micky.commonproj.presenter.impl.NewsPresenterImpl;
import com.micky.commonproj.ui.activity.BaseActivity;
import com.micky.commonproj.ui.adapter.NewsFragmentPagerAdapter;
import com.micky.commonproj.ui.customview.ColumnHorizontalScrollView;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFragment extends BaseFragment implements NewsView {
    View rootView;
    @Bind(R.id.tab_layout)
    TabLayout tab_layout;
    @Bind(R.id.info_viewpager)
    ViewPager info_viewpager;

    private NewsFragmentPagerAdapter mAdapetr;
    private List<Fragment> fragments;
    private NewsFragmentPagerAdapter mPagerAdater;
    private NewsPresenter newPresenter;
    List<ChannelItem> userChannelLists;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, rootView);

        newPresenter = new NewsPresenterImpl(this);
        newPresenter.getTitleData();
        initView();

        return rootView;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setupTitleData(List<ChannelItem> placeList) {
        userChannelLists = placeList;
        Log.d("userChannelLists", "userChannelLists : " + userChannelLists.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                initFragment();
            }
        });


    }

    private void initView(){
        //创建Fragment的 ViewPager 自定义适配器
        mPagerAdater=new NewsFragmentPagerAdapter(getChildFragmentManager());

        info_viewpager.setAdapter(mPagerAdater);
        tab_layout.setupWithViewPager(info_viewpager);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            NewsListFragment oneFragment=new NewsListFragment();
            Bundle bundle=new Bundle();
            bundle.putString("extra",userChannelLists.get(i).getName());
            oneFragment.setArguments(bundle);
            fragments.add(oneFragment);
        }

        //设置显示的标题
        mPagerAdater.setTitles(userChannelLists);
        //设置需要进行滑动的页面Fragment
        mPagerAdater.setFragments(fragments);

        //设置Tablayout
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置自定义Tab--加入图标的demo
        for(int i=0;i<12;i++){
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            tab.setCustomView(mPagerAdater.getTabView(i));
        }
    }

}
