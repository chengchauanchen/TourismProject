package com.example.tourism.homeMoudle.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tourism.R;
import com.example.tourism.base.BaseLazyFragment;
import com.example.tourism.homeMoudle.contact.HomeFgContact;
import com.example.tourism.homeMoudle.present.HomeFgPresenter;

import butterknife.BindView;


public class HomeFragment extends BaseLazyFragment<HomeFgPresenter> implements HomeFgContact.View {
    @BindView(R.id.tv_mall_back)
    TextView tvMallBack;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = (View) super.onCreateView(inflater, container, savedInstanceState);
        return (View) rootView;
    }


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
