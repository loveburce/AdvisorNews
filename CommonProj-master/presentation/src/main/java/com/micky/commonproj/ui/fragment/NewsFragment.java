package com.micky.commonproj.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.micky.commonproj.R;
import com.micky.commonproj.domain.db.DBCore;
import com.micky.commonproj.domain.db.dao.DaoSession;
import com.micky.commonproj.domain.model.ChannelItem;
import com.micky.commonproj.presenter.NewsPresenter;
import com.micky.commonproj.presenter.NewsView;
import com.micky.commonproj.ui.adapter.NewsFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFragment extends BaseFragment{
    View mView;
//    @Bind(R.id.tab_layout)
    TabLayout tab_layout;
//    @Bind(R.id.info_viewpager)
    ViewPager info_viewpager;


    private List<Fragment> fragments;
    private NewsFragmentPagerAdapter mPagerAdater;
    List<ChannelItem> userChannelLists;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_news,container,false);
            initViews();
            initValidata();
        }
        return mView;
    }

    private void initViews(){
        tab_layout=(TabLayout)mView.findViewById(R.id.tab_layout);
        info_viewpager=(ViewPager)mView.findViewById(R.id.info_viewpager);

    }
    private void initValidata(){
        DaoSession daoSession = DBCore.getDaoSession();
//                    defaultUserChannels = daoSession.getChannelItemDao().loadAll();
        userChannelLists = daoSession.getChannelItemDao().queryRaw(" where selected = ?",new String[] {"1"});
        Log.d("userChannelLists","userChannelLists : "+userChannelLists.size());

        fragments=new ArrayList<>();
        for(int i=0;i<userChannelLists.size();i++){
            NewsListFragment oneFragment=new NewsListFragment();
            Bundle bundle=new Bundle();
            bundle.putString("extra",userChannelLists.get(i).getName());
            oneFragment.setArguments(bundle);
            fragments.add(oneFragment);
        }
        //创建Fragment的 ViewPager 自定义适配器
        mPagerAdater=new NewsFragmentPagerAdapter(getChildFragmentManager());
        //设置显示的标题
        mPagerAdater.setTitles(userChannelLists);
        //设置需要进行滑动的页面Fragment
        mPagerAdater.setFragments(fragments);

        info_viewpager.setAdapter(mPagerAdater);
        tab_layout.setupWithViewPager(info_viewpager);


        //设置Tablayout
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置自定义Tab--加入图标的demo
        for(int i=0;i<userChannelLists.size();i++){
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            tab.setCustomView(mPagerAdater.getTabView(i));
        }
    }
}
