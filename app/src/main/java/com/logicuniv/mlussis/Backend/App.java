package com.logicuniv.mlussis.Backend;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by xavie on 24-01-2018.
 */

@SuppressLint("Registered")
public class App extends Application {

    //Xavier
    public static final String WCFServer = "http://172.17.255.135/LUSSIS/Service.svc/";

    /*Joel
    public static final String LoginServer = "http://172.17.252.67/";
    public static final String WCFServer = "http://172.17.252.67/LussisWcfService/Service.svc/";
    */

    /*Junyi
    public static final String LoginServer = "http://172.17.253.140/";
    public static final String WCFServer = "http://172.17.253.140/LussisWcfService/Service.svc/";
     */

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