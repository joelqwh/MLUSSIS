package com.logicuniv.mlussis.Backend;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by xavie on 24-01-2018.
 */

@SuppressLint("Registered")
public class App extends Application {

    public static final String WCFServer = "http://172.17.255.135/LUSSIS/Service.svc/";

    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static void setAppContext(Context c) {appContext = c;}

    public static Context getAppContext() {
        return appContext;
    }
}