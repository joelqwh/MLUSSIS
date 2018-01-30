package com.logicuniv.mlussis.Backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by xavie on 30-01-2018.
 */

public class SharedPrefController {
    public static void setValue(Context context, String name, String value) {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public static String getValue(Context context, String name) {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(name, null);
    }
}
