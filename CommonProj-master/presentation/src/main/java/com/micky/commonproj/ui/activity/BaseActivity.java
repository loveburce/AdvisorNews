package com.micky.commonproj.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micky.commonproj.BaseApplication;
import com.micky.commonproj.R;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Package com.micky.commonproj.ui.activity
 * @Project CommonProj
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Team KTEAM
 * @Date 2016-01-04 21:27
 */
public class BaseActivity extends AppCompatActivity {
//    @Bind(R.id.toolbar) Toolbar mToolBar;
//    @Bind(R.id.tv_title) TextView mTvTitle;
    private boolean mAutoBindView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, false);
    }

    public void setContentView(int layoutResID, boolean hideBackButton) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
//        initToolBar(hideBackButton);
        mAutoBindView = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAutoBindView) {
            ButterKnife.unbind(this);
        }
        BaseApplication.getRefWatcher().watch(this);
    }

//    private void initToolBar(boolean hideBackButton) {
//        if (mToolBar == null) {
//            return;
//        }
//        setTitle("");
//
//        setSupportActionBar(mToolBar);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(false);
//
//        if (!hideBackButton) {
//            mToolBar.setNavigationIcon(R.mipmap.back);
//            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
//    }
//
//    protected void setTitleText(String title) {
//        mTvTitle.setVisibility(View.VISIBLE);
//        mTvTitle.setText(title);
//    }
//
//    protected void setTitleRes(int resId) {
//        mTvTitle.setVisibility(View.VISIBLE);
//        mTvTitle.setText(resId);
//    }
//
//    protected void hideTitle() {
//        mTvTitle.setVisibility(View.GONE);
//    }

     public void setTranslucentNavigation(boolean isSet){
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
             //透明状态栏
             getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
             //透明导航栏
             getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

             LinearLayout linear_bar=(LinearLayout)findViewById(R.id.linear_bar);
             linear_bar.setVisibility(View.VISIBLE);
             int statusHeight=getStatusBarHeight();
             android.widget.RelativeLayout.LayoutParams params=(android.widget.RelativeLayout.LayoutParams )linear_bar.getLayoutParams();
             params.height=statusHeight;
             linear_bar.setLayoutParams(params);

         }
     }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    private int getStatusBarHeight(){
        try
        {
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
