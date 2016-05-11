package com.micky.commonproj.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micky.commonproj.R;
import com.micky.commonproj.ui.customview.DragImageView.ImageInfo;
import com.micky.commonproj.ui.customview.DragImageView.MyPagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dawn-pc on 2016/5/3.
 */
public class ThemeFragment extends Fragment {
    View rootView;
    @Bind(R.id.mynum)
    TextView mynum; // 页码
    @Bind(R.id.vPager)
    ViewPager vpager;

    ArrayList<ImageInfo> data; // 菜单数据


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_theme,null);
        ButterKnife.bind(this, rootView);
        initData();
        initView();
        return rootView;
    }

    private void initView(){
        vpager.setAdapter(new MyPagerAdapter(getActivity(), data));
        vpager.setPageMargin(50);
        vpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                mynum.setText("" + (int) (arg0 + 1));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void initData() {
        data = new ArrayList<ImageInfo>();
        mynum.setText("1");
        data.add(new ImageInfo("今日看点", R.drawable.icon1, R.drawable.icon_bg01));
        data.add(new ImageInfo("新浪微博", R.drawable.icon2, R.drawable.icon_bg01));
        data.add(new ImageInfo("我的收藏", R.drawable.icon3, R.drawable.icon_bg01));
        data.add(new ImageInfo("新闻头条", R.drawable.icon4, R.drawable.icon_bg02));
        data.add(new ImageInfo("科技频道", R.drawable.icon5, R.drawable.icon_bg02));
        data.add(new ImageInfo("汽车频道", R.drawable.icon6, R.drawable.icon_bg02));
        data.add(new ImageInfo("军事频道", R.drawable.icon7, R.drawable.icon_bg02));
        data.add(new ImageInfo("新华炫文", R.drawable.icon8, R.drawable.icon_bg02));
        data.add(new ImageInfo("ͨ娱乐八卦", R.drawable.icon9, R.drawable.icon_bg02));
        data.add(new ImageInfo("体育新闻", R.drawable.icon10, R.drawable.icon_bg02));
        data.add(new ImageInfo("互联网新闻", R.drawable.icon11, R.drawable.icon_bg02));
        data.add(new ImageInfo("奢侈品频道", R.drawable.icon12, R.drawable.icon_bg02));
        data.add(new ImageInfo("时尚频道", R.drawable.icon13, R.drawable.icon_bg02));
        data.add(new ImageInfo("财经频道", R.drawable.icon14, R.drawable.icon_bg02));
        data.add(new ImageInfo("财经新闻", R.drawable.icon15, R.drawable.icon_bg02));
        data.add(new ImageInfo("福布斯中文网", R.drawable.icon16,
                R.drawable.icon_bg02));
        data.add(new ImageInfo("旅游频道", R.drawable.icon3, R.drawable.icon_bg02));
        data.add(new ImageInfo("游戏频道", R.drawable.icon8, R.drawable.icon_bg02));
        data.add(new ImageInfo("开心笑话", R.drawable.icon10, R.drawable.icon_bg02));

    }


}
