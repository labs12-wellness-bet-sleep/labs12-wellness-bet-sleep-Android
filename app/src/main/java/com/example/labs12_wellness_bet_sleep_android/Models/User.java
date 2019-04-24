package com.example.labs12_wellness_bet_sleep_android.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String password, username;

    public User(){

    }

    public User(JSONObject json){
        try {
            this.username = json.getString("username");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.password = json.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
