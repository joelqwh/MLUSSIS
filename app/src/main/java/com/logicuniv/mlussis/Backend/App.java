package com.logicuniv.mlussis.Backend;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by xavie on 24-01-2018.
 */

@SuppressLint("Registered")
public class App extends Application {

    public static final String LoginServer = "http://172.17.252.67/";
    public static final String WCFServer = "http://172.17.252.67/LussisWcfService/Service.svc/";

    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
    public static Context getAppContext() {
        return appContext;
    }
}