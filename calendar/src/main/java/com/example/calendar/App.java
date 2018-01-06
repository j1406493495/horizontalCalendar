package com.example.calendar;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by wong on 2018/1/6.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
