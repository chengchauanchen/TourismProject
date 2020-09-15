package com.example.tourism.di.component;

import android.app.Activity;

import com.example.tourism.di.ActivityScope;
import com.example.tourism.di.module.ActivityMainModule;
import com.example.tourism.homeMoudle.activity.MainActivity;

import dagger.Component;


/**
 * Created by ccc
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityMainModule.class)
public interface ActivityMainComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

}
