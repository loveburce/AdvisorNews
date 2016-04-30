package com.dawn.apollo.apollo.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dawn.apollo.apollo.R;
import com.dawn.apollo.apollo.adapter.NewFragmentAAdapter;
import com.dawn.apollo.apollo.bean.NewModel;
import com.dawn.apollo.apollo.http.volleyokhttp.HttpClientRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsFragmentA extends BaseFragment {
	private LinearLayout ll_topBarLinearBack;
	private TextView tv_topBarTitle;
	private RecyclerView recyclerView;
	private LinearLayoutManager linearLayoutManager;

	private SwipeRefreshLayout swipeRefreshLayout;
	private int lastVisibleItem;
	//是否正在加载更多的标志
	private boolean isMoreLoading=false;

	protected ProgressBar mProgressBar;
	protected HashMap<String, String> url_maps;

	protected HashMap<String, NewModel> newHashMap;

	protected NewFragmentAAdapter newFragmentAAdapter;
	protected List<NewModel> listsModles;
	private int index = 0;
	private boolean isRefresh = false;

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

		initData();
		getNewsData();

		return view;
	}

	private void initData(){
		listsModles = new ArrayList<NewModel>();
		url_maps = new HashMap<String, String>();
		newHashMap = new HashMap<String, NewModel>();
	}

	private void getNewsData(){
		String httpurl = getNewUrl(index + "");
		HttpClientRequest httpClientRequest = HttpClientRequest.getInstance(getActivity());
		StringRequest stringRequest = new StringRequest(Request.Method.POST,httpurl,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d("ffffffffffffff", "response -> " + response);

						JSONObject jsonObject = null;
						String results;
						try {
							jsonObject = new JSONObject(response);
							results = jsonObject.getString("results");

//							tunnelInfoList = JsonUtil.fromJson(results, new TypeToken<ArrayList<TunnelInfo>>() {
//							}.getType());
//							Log.d("ffffffffffffff", "response -> " + tunnelInfoList.size());

//							InitView();


						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("ffffffffffffff", error.getMessage(), error);
			}
		}) {
			@Override
			protected Map<String, String> getParams() {
				//在这里设置需要post的参数
				Map<String,String> params = new HashMap<String, String>();

//                String sign = null;
//                try {
//                    sign = URLEncoder.encode(MD5Tools.GetMD5Code(Constant.PublicKey), "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                params.put("sign", sign);
//                Log.e("ffffffffffffff", " sign : "+sign);
				return params;
			}
		};

		httpClientRequest.addRequest(stringRequest);

	}

}
