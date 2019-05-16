package com.example.labs12_wellness_bet_sleep_android.Network;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedTokens {

    private static SharedTokens store;
    private SharedPreferences sharedPrefs;
    private static String TOKEN_KEY = "SharedTokens";
    public static int theme = 1;

    private SharedTokens(Context context){
        sharedPrefs = context.getApplicationContext().getSharedPreferences(TOKEN_KEY, 0);
    }

    public static SharedTokens getInstance(Context context){
        if (store == null){
            store = new SharedTokens(context);
        }
        return store;
    }

    public String getString(String key){
        return sharedPrefs.getString(key, null);
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
