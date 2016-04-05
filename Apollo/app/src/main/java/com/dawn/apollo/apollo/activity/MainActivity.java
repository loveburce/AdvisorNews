package com.dawn.apollo.apollo.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.dawn.apollo.apollo.R;
import com.dawn.apollo.apollo.adapter.MyFragmentAdapter;
import com.dawn.apollo.apollo.fragment.HotNewsFragment;
import com.dawn.apollo.apollo.fragment.Fragment2;
import com.dawn.apollo.apollo.fragment.Fragment3;
import com.dawn.apollo.apollo.fragment.Fragment4;

public class MainActivity extends BaseActivity implements
        OnPageChangeListener, OnTabChangeListener {

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = { HotNewsFragment.class, Fragment.class, Fragment3.class, Fragment4.class, Fragment4.class };
    private int imageViewArray[] = { R.drawable.mywork, R.drawable.mypatient,
            R.drawable.infusion, R.drawable.personal, R.drawable.personal };
    private String textViewArray[] = { "推荐", "关注", "视频", "图片","我的" };
    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_layout);

        initView();
        initPage();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        vp = (ViewPager) findViewById(R.id.pager);
        vp.setOnPageChangeListener(this);
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);
        mTabHost.setOnTabChangedListener(this);

        int count = textViewArray.length;

        for (int i = 0; i < count; i++) {
            TabSpec tabSpec = mTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
        }
    }

    /**
     * 初始化Fragment
     */
    private void initPage() {
        HotNewsFragment fragment1 = new HotNewsFragment();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
    }

    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.tab_content, null);
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        mTabHost.setCurrentTab(arg0);
        widget.setDescendantFocusability(oldFocusability);
        mTabHost.getTabWidget().getChildAt(arg0).setBackgroundResource(R.drawable.selector_tab_background);
    }

    @Override
    public void onTabChanged(String tabId) {
        int position = mTabHost.getCurrentTab();
        vp.setCurrentItem(position);
    }

}
