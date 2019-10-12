package com.alick.objectboxdemo;

import android.app.Application;

/**
 * @author 崔兴旺
 * @createTime 2019/10/12 14:19
 * @description
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DataManager.getInstance().init(this);
    }



}
