package com.example.tourism.homeMoudle.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.R;
import com.example.tourism.base.BaseActivity;
import com.example.tourism.homeMoudle.contact.HomeContact;
import com.example.tourism.homeMoudle.present.HomePresenter;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContact.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }
}
