package com.example.tourism.di.component;

import android.app.Activity;


import com.example.tourism.di.FragmentScope;
import com.example.tourism.di.module.FragmentMainModule;
import com.example.tourism.homeMoudle.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by ccc
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentMainModule.class)
public interface FragmentMainComponent {

    Activity getActivity();

    void inject(HomeFragment homeFragment);



}
