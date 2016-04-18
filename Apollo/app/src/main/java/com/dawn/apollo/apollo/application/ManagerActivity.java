package com.dawn.apollo.apollo.application;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dawn-pc on 2016/4/18.
 */
public class ManagerActivity {
    public  static ManagerActivity instance = new ManagerActivity();
    private List<Activity> mLists = new ArrayList<>();
    private ManagerActivity(){

    }

    public synchronized static ManagerActivity getInstance(){
        return instance;
    }

    public void addActivity(Activity mActivity){
        if(mActivity != null){
            mLists.add(mActivity);
        }
    }

    public void removeActivity(Activity mActivity){
        if(mActivity != null){
            if(mLists.contains(mActivity)){
                mLists.remove(mActivity);
                mActivity.finish();
                mActivity = null;
            }
        }
    }

    //从栈中进行删除集合顶得Activity
    public void popActivity() {
        Activity activity = mLists.get(mLists.size() - 1);
        removeActivity(activity);
    }
    public int getNum() {
        return mLists.size();
    }
    /**
     * 完全删除集合中
     */
    public void finishActivity() {
        if (mLists != null && mLists.size() >= 0) {
            for (Activity pActivity : mLists) {
                pActivity.finish();
                pActivity = null;
            }
        }
    }
}
