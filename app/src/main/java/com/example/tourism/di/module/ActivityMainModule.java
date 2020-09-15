package com.example.tourism.di.module;

import android.app.Activity;


import com.example.tourism.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ccc
 */

@Module
public class ActivityMainModule {
    private Activity mActivity;

    public ActivityMainModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
