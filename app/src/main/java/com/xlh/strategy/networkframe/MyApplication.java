package com.xlh.strategy.networkframe;

import android.app.Application;

import com.xlh.strategy.networkframe.http.HttpHelper;
import com.xlh.strategy.networkframe.processor.OkHttpProcessor;
import com.xlh.strategy.networkframe.processor.VolleyProcessor;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
//        HttpHelper.init(new VolleyProcessor(this));
        HttpHelper.init(new OkHttpProcessor());
    }
}
