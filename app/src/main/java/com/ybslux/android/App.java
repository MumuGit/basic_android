package com.ybslux.android;

import android.app.Application;

import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
//        UMShareAPI.init(this,"591156ce5312dd874f0004df");
        UMShareAPI.get(this);
    }
}
