package com.android.x7dev.melnicoffvkontaktesound.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Account {

    public String access_token;
    public String user_id;

    public void save(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("access_token", access_token);
        editor.putString("user_id", user_id);
        editor.commit();
    }

    public void restore(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        access_token = prefs.getString("access_token", null);
        user_id = prefs.getString("user_id", null);
    }

}
