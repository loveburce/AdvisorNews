package com.dawn.apollo.apollo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.dawn.apollo.apollo.application.ManagerActivity;

/**
 * Created by Administrator on 2016/4/5.
 */
public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ManagerActivity.getInstance().addActivity(this);

    }
}
