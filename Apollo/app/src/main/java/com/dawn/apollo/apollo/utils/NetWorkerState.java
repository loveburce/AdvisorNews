package com.dawn.apollo.apollo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dawn-pc on 2016/4/19.
 */
public class NetWorkerState {
    private static final int NET = 1;
    private static final int WAP = 2;
    private static final int WIFI = 3;

    public static boolean checkConnectionState(Context context){
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isConnectedOrConnecting()){
            return false;
        }else{
            return true;
        }
    }

    public static int getAPNType(Context context){
        int netType = -1;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return  netType;
        }
        int nType = networkInfo.getType();
        if(nType == connectivityManager.TYPE_MOBILE){
            if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                netType = NET;
            }else{
                netType = WAP;
            }
        }else if(nType == connectivityManager.TYPE_WIFI){
            netType = WIFI;
        }
        return netType;
    }
}
