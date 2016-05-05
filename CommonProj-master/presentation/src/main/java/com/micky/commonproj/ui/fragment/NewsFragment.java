package com.micky.commonproj.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    @Bind(R.id.mColumnHorizontalScrollView)
    protected ColumnHorizontalScrollView mColumnHorizontalScrollView;
    @Bind(R.id.mRadioGroup_content)
    protected LinearLayout mRadioGroup_content;
    @Bind(R.id.ll_more_columns)
    protected LinearLayout ll_more_columns;
    @Bind(R.id.rl_column)
    protected RelativeLayout rl_column;
    @Bind(R.id.button_more_columns)
    protected ImageView button_more_columns;
    @Bind(R.id.mViewPager)
    protected ViewPager mViewPager;
    @Bind(R.id.shade_left)
    protected ImageView shade_left;
    @Bind(R.id.shade_right)
    protected ImageView shade_right;


    /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;

    /** 用户选择的新闻分类列表 */
    protected static List<ChannelItem> userChannelLists;
    /** 请求CODE */
    public final static int CHANNELREQUEST = 1;
    /** 调整返回的RESULTCODE */
    public final static int CHANNELRESULT = 10;
    /** 当前选中的栏目 */
    private int columnSelectIndex = 0;
    private ArrayList<Fragment> fragments;

    private Fragment newfragment;
    private double back_pressed;
    public static boolean isChange = false;

    private NewsFragmentPagerAdapter mAdapetr;


    private Context mContext;
    private View rootView;
    private NewsPresenter newPresenter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, rootView);

        newPresenter = new NewsPresenterImpl(this);
        newPresenter.getTitleData();
        initView();
        initViewPager();

        return rootView;
    }


    void initView() {
        ((Activity)mContext).getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        mScreenWidth = BaseTools.getWindowsWidth(((Activity)mContext));
        mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
        userChannelLists = new ArrayList<ChannelItem>();
        fragments = new ArrayList<Fragment>();
    }



    private void initViewPager() {
        mAdapetr = new NewsFragmentPagerAdapter(
                ((BaseActivity)mContext).getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.setOnPageChangeListener(pageListener);
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
        Log.d("userChannelLists","userChannelLists : "+userChannelLists.size());
        initTabColumn();
        initFragment();
    }


    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = userChannelLists.size();
        mColumnHorizontalScrollView.setParam(((BaseActivity) mContext), mScreenWidth, mRadioGroup_content, shade_left,
                shade_right, ll_more_columns, rl_column);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth,
                    LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            // TextView localTextView = (TextView)
            // mInflater.inflate(R.layout.column_radio_item, null);
            TextView columnTextView = new TextView(((BaseActivity)mContext));
            columnTextView.setTextAppearance(((BaseActivity) mContext), R.style.top_category_scroll_view_item_text);
            // localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
            columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelLists.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(
                    R.color.arc_progress_background));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else {
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                }
            });
            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments.clear();
        int count = userChannelLists.size();
        for (int i = 0; i < count; i++) {
             Bundle data = new Bundle();
            String nameString = userChannelLists.get(i).getName();
            int cId = userChannelLists.get(i).getCId();
            NewsListFragment newsListFragment = new NewsListFragment();
            data.putString("text", nameString);
            data.putInt("cId", cId);
             newfragment.setArguments(data);
            fragments.add(newfragment);
            // fragments.add(nameString);
        }
        mAdapetr.appendList(fragments);
    }



    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        // 判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

}
