package com.dawn.apollo.apollo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawn.apollo.apollo.R;

/**
 * @author lili.guo
 *
 * 2014-10-22
 */
public class Fragment3 extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_item3, null);
		return view;
	}
}
