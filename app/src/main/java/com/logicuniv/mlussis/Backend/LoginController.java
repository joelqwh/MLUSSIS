package com.logicuniv.mlussis.Backend;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.logicuniv.mlussis.R;

/**
 * Created by xavie on 24-01-2018.
 */

public class LoginController {

    public static boolean AuthenticateCredentials(Context context, String username, String password)
    {
        boolean result = false;

        String response = JSONParser.postStream(
                App.LoginServer+"android.aspx",
                "user="+username+"&pass="+password);

        if(response.length() > 2)
        {
            setSessionID(context, response);
            result = true;
        }

        return result;
    }

    private static void setSessionID(Context context, String sessionID)
    {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("SessionID", sessionID);
        editor.apply();
    }

    public static String getSessionID(Context context)
    {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString("SessionID", "0");
    }
}
