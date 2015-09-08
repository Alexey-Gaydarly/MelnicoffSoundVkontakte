package com.android.x7dev.melnicoffvkontaktesound.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String extractPattern(String string, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(string);
        if (m.find())
            return m.toMatchResult().group(1);
        return null;
    }

    public static boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
