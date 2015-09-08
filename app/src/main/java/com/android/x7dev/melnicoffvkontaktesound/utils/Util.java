package com.android.x7dev.melnicoffvkontaktesound.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Util {

    public static boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
