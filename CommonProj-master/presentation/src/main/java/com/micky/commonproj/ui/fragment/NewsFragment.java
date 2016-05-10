package com.micky.commonproj.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.micky.commonproj.R;
import com.micky.commonproj.domain.db.DBCore;
import com.micky.commonproj.domain.db.dao.DaoSession;
import com.micky.commonproj.domain.model.ChannelItem;
import com.micky.commonproj.ui.activity.NewsChannelActivity;
import com.micky.commonproj.ui.adapter.NewsFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFragment extends BaseFragment{
    View rootView;
    @Bind(R.id.tab_layout)
    TabLayout tab_layout;
    @Bind(R.id.info_viewpager)
    ViewPager info_viewpager;
    @Bind(R.id.fragment_news_add_item)
    ImageButton ib_channelManager;


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
        rootView=inflater.inflate(R.layout.fragment_news,container,false);
        ButterKnife.bind(this, rootView);
        initValidata();
        return rootView;
    }


    private void initValidata(){
        ib_channelManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMyActivity(), NewsChannelActivity.class);
                getMyActivity().startActivity(intent);
            }
        });


        DaoSession daoSession = DBCore.getDaoSession();
        userChannelLists = daoSession.getChannelItemDao().queryRaw(" where selected = ?",new String[] {"1"});
        Log.d("userChannelLists","userChannelLists : "+userChannelLists.size());

        fragments=new ArrayList<>();
        for(int i=0;i<userChannelLists.size();i++){
            NewsListFragment oneFragment=new NewsListFragment();
            Bundle bundle=new Bundle();
            bundle.putString("extra",userChannelLists.get(i).getCId()+"");
            bundle.putLong("IdKey",userChannelLists.get(i).getId());
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
        info_viewpager.setOffscreenPageLimit(1);
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
