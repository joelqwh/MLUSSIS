package com.logicuniv.mlussis.Backend;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

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

    public static boolean checkConnection() {
        boolean result = false;
        String response;

        try {
            response = JSONParser.postStream(
                    App.WCFServer + "test",
                    "").trim();

            if (response.equals("test"))
            {
                result = true;
            }
        } catch (Exception e)
        {
            Log.e("CheckConnection", e.getMessage());
            result = false;
        }

        return result;
    }
}