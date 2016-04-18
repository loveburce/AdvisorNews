package com.dawn.apollo.apollo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawn.apollo.apollo.R;
import com.dawn.apollo.apollo.adapter.NewFragmentAAdapter;

public class NewsFragmentA extends Fragment {
	private LinearLayout ll_topBarLinearBack;
	private TextView tv_topBarTitle;
	private RecyclerView recyclerView;
	private LinearLayoutManager linearLayoutManager;
	private NewFragmentAAdapter newFragmentAAdapter;
	private SwipeRefreshLayout swipeRefreshLayout;
	private int lastVisibleItem;
	//是否正在加载更多的标志
	private boolean isMoreLoading=false;


	String text;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle args = getArguments();
		text = args != null ? args.getString("text") : "";
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news_a, null);


		return view;
	}

}
