package com.example.tourism.base;

import android.os.Bundle;

import android.text.TextUtils;


import com.example.tourism.application.MyApplication;
import com.example.tourism.di.component.ActivityMainComponent;
import com.example.tourism.di.component.DaggerActivityMainComponent;
import com.example.tourism.di.module.ActivityMainModule;
import com.lq.lianjibusiness.base_libary.App.Constants;
import com.lq.lianjibusiness.base_libary.ui.base.BasePresenter;
import com.lq.lianjibusiness.base_libary.ui.base.BaseView;
import com.lq.lianjibusiness.base_libary.ui.base.NetActivity;
import com.lq.lianjibusiness.base_libary.utils.PrefUtils;
import com.lq.lianjibusiness.base_libary.utils.ToastUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by ccc on 2020/9/15.
 */

public abstract class BaseActivity<T extends BasePresenter> extends NetActivity implements BaseView {

    @Inject
    public T mPresenter;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnBinder = ButterKnife.bind(this);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        initEventAndData();
    }

    public abstract void initEventAndData();

    public abstract void initInject();

    protected ActivityMainComponent getActivityComponent() {
        return DaggerActivityMainComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityMainModule(getActivityModule())
                .build();
    }

    protected ActivityMainModule getActivityModule() {
        return new ActivityMainModule(this);
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    protected void mistakeLoadData() {
        mPresenter.getNetMistakeData();
    }

    @Override
    public synchronized void toLogin() {
        //如果需要到主页面的跳转,需要给userinfo给一个状态
       /* String token = PrefUtils.getString(Constants.SP_TOKEN, "");
        if (!TextUtils.isEmpty(token)) {
            PrefUtils.putString(Constants.SP_TOKEN, "");
            ActivityJumpUtils.ToOtherActivity(LoginStepOneActivity.class, this, null);
            ImExitUtils.exitIm();
        }*/
        PrefUtils.putString(Constants.SP_TOKEN, "");
        PrefUtils.putString(Constants.SP_ACCOUNT, "");
        PrefUtils.putString(Constants.SP_REALNAME, "");
        PrefUtils.putInt(Constants.SP_STATUS, -1);
        PrefUtils.putString(Constants.SP_HEADPORTRAIT, "");
        PrefUtils.putLong(Constants.SP_ID, -1);
        PrefUtils.putLong(Constants.SP_STOREID, -1);

       // ActivityUtils.switchTo(this, LoginActivity.class);
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(PrefUtils.getString(Constants.SP_TOKEN, ""));
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void onComplete() {

    }
}
