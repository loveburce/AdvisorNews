package com.micky.commonproj.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.micky.commonproj.R;

import butterknife.ButterKnife;

/**
 * Created by dawn-pc on 2016/5/3.
 */
public class SearchFragment extends Fragment {
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search,null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
