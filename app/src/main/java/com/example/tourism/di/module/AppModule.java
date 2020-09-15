package com.example.tourism.di.module;


import com.example.tourism.di.ContextLife;
import com.example.tourism.homeMoudle.module.HomeApiModule;
import com.lq.lianjibusiness.base_libary.App.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ccc
 */


@Module
public class AppModule {

    private App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    App provideApplicationContext() {
        return application;
    }



    @Provides
    @Singleton
    HomeApiModule provideHomeApis() {
        return new HomeApiModule();
    }


}
