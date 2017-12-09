package com.haoxueren.library.base;

import android.app.Application;

import com.haoxueren.library.ContextHelper;

/**
 * Init Library in onCreate()
 * Created by Haoxueren on 2017/12/9.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHelper.initLibrary(this); // init library
    }
}
