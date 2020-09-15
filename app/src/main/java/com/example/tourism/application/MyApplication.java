package com.example.tourism.application;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.tourism.di.component.AppComponent;
import com.example.tourism.di.component.DaggerAppComponent;
import com.example.tourism.di.module.AppModule;
import com.lq.lianjibusiness.base_libary.App.App;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;


/**
 * Created by ccc on 2020/9/15.
 */

public class MyApplication extends App {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
         MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //bugly异常上报
        CrashReport.initCrashReport(getApplicationContext(), "d0e5250213", true);
        initUM();
    }

    private void initUM() {
        UMConfigure.init(this, "5f605d09b4739632429f7e82", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(getApplicationContext()).setShareConfig(config);
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }
}
