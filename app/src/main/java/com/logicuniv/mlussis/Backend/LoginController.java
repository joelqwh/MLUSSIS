package com.logicuniv.mlussis.Backend;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.logicuniv.mlussis.R;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.logicuniv.mlussis.Backend.JSONParser.readStream;

/**
 * Created by xavie on 24-01-2018.
 */

public class LoginController {

    // To get the unique id we should not specify json
    private static String postStream(String url, String data) {

        InputStream is = null;
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "application/json");
            conn.setFixedLengthStreamingMode(data.getBytes().length);
            conn.connect();
            OutputStream os = new BufferedOutputStream(conn.getOutputStream());
            os.write(data.getBytes());
            os.flush();
            is = conn.getInputStream();
        } catch (UnsupportedEncodingException e) {
            Log.e("LoginController", e.getMessage());
        } catch (Exception e) {
            Log.e("LoginController", e.getMessage());
        }
        return readStream(is);
    }

    public static boolean AuthenticateCredentials(Context context, String username, String password) {
        boolean result = false;

        Log.d("LoginController", App.LoginServer + "android.aspx");
        Log.d("LoginController", "user=****&pass=****");
        String response = postStream(
                App.LoginServer + "android.aspx",
                "user=" + username + "&pass=" + password).trim();


        if (response.length() > 2) {
            setSessionID(context, response);
            result = true;
            Log.d("LoginController", "Session ID Sucessfully Accquired");
        } else {
            Log.d("LoginController", "Could not Get Session ID");
        }

        return result;
    }

    public static boolean IsCurrentSessionValid() {
        boolean result = false;
        JSONObject jsonObject = new JSONObject();
        String response;

        try {
            jsonObject.put("sessionID", getSessionID(App.getAppContext()));

            Log.d("LoginController", App.WCFServer + "checkSession");
            Log.d("LoginController", jsonObject.toString());

            response = JSONParser.postStream(
                    App.WCFServer + "checkSession",
                    jsonObject.toString()).trim();

            Log.d("LoginController",response);

            result = response.equals("true");
        } catch (Exception e) {
            Log.e("LoginController", e.getMessage());
        }

        return result;
    }

    private static void setSessionID(Context context, String sessionID) {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        Log.d("LoginController", "Setting Session ID to :" + sessionID);
        editor.putString("SessionID", sessionID);
        editor.apply();
    }

    public static String getSessionID(Context context) {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);
        Log.d("LoginController", "Reading Session ID :'" + pref.getString("SessionID", "0") + "'");
        return pref.getString("SessionID", "0");
    }
}
