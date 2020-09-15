package com.example.tourism.di.component;


import com.example.tourism.di.ContextLife;
import com.example.tourism.di.module.AppModule;
import com.example.tourism.homeMoudle.module.HomeApiModule;
import com.lq.lianjibusiness.base_libary.App.App;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ccc
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    App getContext();  // 提供App的Context


    HomeApiModule getHomeApis();

}
