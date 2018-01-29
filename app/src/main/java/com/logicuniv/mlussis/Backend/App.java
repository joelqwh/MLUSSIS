package com.logicuniv.mlussis.Backend;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by xavie on 24-01-2018.
 */

@SuppressLint("Registered")
public class App extends Activity {

    //Xavier
    //public static final String WCFServer = "http://172.17.255.135/LUSSIS/Service.svc/";

    //Joel
    //public static final String LoginServer = "http://172.17.252.67/";
    //public static final String WCFServer = "http://172.17.252.67/LUSSIS/Service.svc/";
    public static final String WCFServer = "http://172.17.253.140/LUSSIS/Service.svc/";
    /*Junyi
    public static final String LoginServer = "http://172.17.253.140/";

     */

    private static Context appContext;

    public static void setAppContext(Context c) {
        appContext = c;

        if(appContext != null) {
            Log.e("App", "App Context not being placed properly!");
        }
    }

    public static Context getAppContext() {
        if(appContext != null) {
            Log.e("App", "App Context is missing!");
        }

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