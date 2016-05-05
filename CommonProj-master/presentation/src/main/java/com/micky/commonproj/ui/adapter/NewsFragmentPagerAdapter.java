package com.micky.commonproj.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.micky.commonproj.BaseApplication;
import com.micky.commonproj.R;
import com.micky.commonproj.domain.model.ChannelItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dawn-pc on 2016/5/5.
 */
public class NewsFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<ChannelItem> titles;
    private LayoutInflater layoutInflater;

    public void setTitles(List<ChannelItem> titles){
        this.titles = titles;
    }

    private List<Fragment> fragmentList;

    public NewsFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList == null ? null: this.fragmentList.get(position);
    }


    @Override
    public int getCount() {
        return fragmentList == null ? 0: fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment=null;
        try {
            fragment=(Fragment)super.instantiateItem(container,position);
        }catch (Exception e){

        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    public List<Fragment> getFragments() {
        return fragmentList;
    }
    public void setFragments(List<Fragment> fragments) {
        this.fragmentList = fragments;
    }

    /**
     * 添加getTabView的方法，来进行自定义Tab的布局View
     * @param position
     * @return
     */
    public View getTabView(int position){
        layoutInflater=LayoutInflater.from(BaseApplication.getInstance());
        View view=layoutInflater.inflate(R.layout.tab_item_layout,null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(titles.get(position).getName());
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(R.mipmap.ic_launcher);
        return view;
    }
}
