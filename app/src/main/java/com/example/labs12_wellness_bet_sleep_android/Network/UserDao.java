package com.example.labs12_wellness_bet_sleep_android.Network;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
    public static final String BASE_URL = "whateverurl";
    public static final String USER_URL = BASE_URL + "/user";
    public static final String CLIENT_ID = "lambda-client";
    public static final String CLIENT_SECRET = "lambda-secret";
    public static final String CLIENT_ID_SECRET = CLIENT_ID + ":" + CLIENT_SECRET;

    public static Map<String, String> headerProperties;

    public static Map<String, String> logIn(String username, String password) {
        String token = "";
        String auth = Base64.encodeToString("lambda-client:lambda-secret".getBytes(), Base64.DEFAULT);

        headerProperties = new HashMap<>();
        headerProperties.put("Authorization", "Basic " + auth);


        String tokenRequest = null;
        try {
            tokenRequest = NetworkAdapter.httpRequest(
                    "http://192.168.1.14:2019/oauth/token?grant_type=password&username="
                            + username + "&password="
                            + password + "&scope=",
                    "POST", null, headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("TokenRequest", tokenRequest);
        try {
            token = new JSONObject(tokenRequest).getString("access_token");

            headerProperties.clear();
            headerProperties.put("Authorization", "Bearer " + token);


            String result = null;
            try {
                result = NetworkAdapter.httpRequest("http://192.168.1.14:2019/restaurants/all", "GET", null, headerProperties);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Log.i("Authentication", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Log.i("HeaderProperties", headerProperties.toString());
        return headerProperties;
    }

}
