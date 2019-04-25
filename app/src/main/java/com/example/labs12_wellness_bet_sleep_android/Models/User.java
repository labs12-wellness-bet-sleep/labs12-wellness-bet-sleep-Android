package com.example.labs12_wellness_bet_sleep_android.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

public class User {
    private String password, username, email, role, photoUrl;

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
        try {
            this.email = json.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.role = json.getString("role");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.role = json.getString("photoUrl");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
