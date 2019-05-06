package com.example.labs12_wellness_bet_sleep_android.Models;

public class ListItemRankings {

    private String profilePhoto;
    private String username;

    public ListItemRankings(String profilePhoto, String username) {
        this.profilePhoto = profilePhoto;
        this.username = username;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getUsername() {
        return username;
    }
}
