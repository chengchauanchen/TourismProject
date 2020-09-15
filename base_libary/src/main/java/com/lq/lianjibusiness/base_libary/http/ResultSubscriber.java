package com.lq.lianjibusiness.base_libary.http;

import android.text.TextUtils;
import android.util.Log;

import com.lq.lianjibusiness.base_libary.ui.base.BasePresenter;
import com.lq.lianjibusiness.base_libary.utils.ToastUtil;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by ccc on 2020/9/15.
 * 描述：
 */
public abstract class ResultSubscriber<T> extends ResourceSubscriber<HttpResult<T>> {
    private BasePresenter mPresenter;

    public ResultSubscriber(BasePresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        String status = tHttpResult.getCode();
        if (TextUtils.equals(status, "0")) {
            mPresenter.closeDialog();
            onAnalysisNext(tHttpResult.getData());
        } else if (TextUtils.equals(status, "-101")) {
            mPresenter.toLogin();
        } else if (TextUtils.equals(status, "-9")) {
            errorState(tHttpResult.getMsg(), tHttpResult.getCode());
            ToastUtil.showToast("网络开小差");
        } else {
            errorState(tHttpResult.getMsg(), tHttpResult.getCode());
        }
    }

    public void errorState(String message, String state) {
        mPresenter.showError(message,state);
    }

    public abstract void onAnalysisNext(T data);

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        mPresenter.closeDialog();
    }

    @Override
    public void onComplete() {
        mPresenter.closeDialog();
        mPresenter.complete();
    }
}
