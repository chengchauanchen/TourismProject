package com.lq.lianjibusiness.base_libary.ui.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.lq.lianjibusiness.base_libary.R;
import com.lq.lianjibusiness.base_libary.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by ccc on 2020/9/15.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends SupportActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();

    public void setStateBar(View top) {
        int i = StatusBarUtil.StatusBarLightMode(this);
        if(i == 0) {
            top.setBackgroundResource(R.drawable.top_view_bg);
        }
    }

}
