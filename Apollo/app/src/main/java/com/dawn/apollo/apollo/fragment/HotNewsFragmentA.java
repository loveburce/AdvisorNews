/**
 * 
 */
package com.dawn.apollo.apollo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dawn.apollo.apollo.R;
import com.dawn.apollo.apollo.adapter.NewsFragmentPagerAdapter;
import com.dawn.apollo.apollo.customview.ColumnHorizontalScrollView;
import com.dawn.apollo.apollo.model.NewsClassify;
import com.dawn.apollo.apollo.utils.BaseTools;
import com.dawn.apollo.apollo.utils.Constants;

import java.util.ArrayList;


/**
 * @author lili.guo
 *
 * 2014-10-22
 */
public class HotNewsFragmentA extends Fragment {

	/** 自定义HorizontalScrollView */
	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	LinearLayout mRadioGroup_content;
	LinearLayout ll_more_columns;
	RelativeLayout rl_column;
	private ViewPager mViewPager;
	private ImageView button_more_columns;
	/** 新闻分类列表*/
	private ArrayList<NewsClassify> newsClassify=new ArrayList<NewsClassify>();
	/** 当前选中的栏目*/
	private int columnSelectIndex = 0;
	/** 左阴影部分*/
	public ImageView shade_left;
	/** 右阴影部分 */
	public ImageView shade_right;
	/** 屏幕宽度 */
	private int mScreenWidth = 0;
	/** Item宽度 */
	private int mItemWidth = 0;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_hot_news, null);

		mScreenWidth = BaseTools.getWindowsWidth(getActivity());
		mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
		initView();


		return rootView;
	}

	/** 初始化layout控件*/
	private void initView() {
		mColumnHorizontalScrollView =  (ColumnHorizontalScrollView)rootView.findViewById(R.id.mColumnHorizontalScrollView);
		mRadioGroup_content = (LinearLayout) rootView.findViewById(R.id.mRadioGroup_content);
		ll_more_columns = (LinearLayout) rootView.findViewById(R.id.ll_more_columns);
		rl_column = (RelativeLayout) rootView.findViewById(R.id.rl_column);
		button_more_columns = (ImageView) rootView.findViewById(R.id.button_more_columns);
		mViewPager = (ViewPager) rootView.findViewById(R.id.mViewPager);
		shade_left = (ImageView) rootView.findViewById(R.id.shade_left);
		shade_right = (ImageView) rootView.findViewById(R.id.shade_right);
		button_more_columns.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		setChangelView();
	}
	/**
	 *  当栏目项发生变化时候调用
	 * */
	private void setChangelView() {
		initColumnData();
		initTabColumn();
		initFragment();
	}
	/** 获取Column栏目 数据*/
	private void initColumnData() {
		newsClassify = Constants.getData();
	}

	/**
	 *  初始化Column栏目项
	 * */
	private void initTabColumn() {
		mRadioGroup_content.removeAllViews();
		int count =  newsClassify.size();
		mColumnHorizontalScrollView.setParam(getActivity(), mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
		for(int i = 0; i< count; i++){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth , LinearLayout.LayoutParams.WRAP_CONTENT);
			params.leftMargin = 10;
			params.rightMargin = 10;
//			TextView localTextView = (TextView) mInflater.inflate(R.layout.column_radio_item, null);
			TextView localTextView = new TextView(getActivity());
			localTextView.setTextAppearance(getActivity(), R.style.top_category_scroll_view_item_text);
//			localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
			localTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
			localTextView.setGravity(Gravity.CENTER);
			localTextView.setPadding(5, 0, 5, 0);
			localTextView.setId(i);
			localTextView.setText(newsClassify.get(i).getTitle());
			localTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
			if(columnSelectIndex == i){
				localTextView.setSelected(true);
			}
			localTextView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
						View localView = mRadioGroup_content.getChildAt(i);
						if (localView != v)
							localView.setSelected(false);
						else{
							localView.setSelected(true);
							mViewPager.setCurrentItem(i);
						}
					}
					Toast.makeText(getActivity(), newsClassify.get(v.getId()).getTitle(), Toast.LENGTH_SHORT).show();
				}
			});
			mRadioGroup_content.addView(localTextView, i ,params);
		}
	}
	/**
	 *  选择的Column里面的Tab
	 * */
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
		//判断是否选中
		for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
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
	/**
	 *  初始化Fragment
	 * */
	private void initFragment() {
		int count =  newsClassify.size();
		for(int i = 0; i< count;i++){
			Bundle data = new Bundle();
			data.putString("text", newsClassify.get(i).getTitle());
			NewsFragmentA newfragment = new NewsFragmentA();
			newfragment.setArguments(data);
			fragments.add(newfragment);
		}
		NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
		mViewPager.setAdapter(mAdapetr);
		mViewPager.setOnPageChangeListener(pageListener);
	}
	/**
	 *  ViewPager切换监听方法
	 * */
	public ViewPager.OnPageChangeListener pageListener= new ViewPager.OnPageChangeListener(){

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			mViewPager.setCurrentItem(position);
			selectTab(position);
		}
	};

}
