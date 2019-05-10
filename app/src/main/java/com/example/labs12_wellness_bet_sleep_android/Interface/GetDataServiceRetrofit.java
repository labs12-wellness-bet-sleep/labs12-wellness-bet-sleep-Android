package com.example.labs12_wellness_bet_sleep_android.Interface;

import com.example.labs12_wellness_bet_sleep_android.Models.ParticipantResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataServiceRetrofit {
    // https://sleep-bet.herokuapp.com/api/groups/3/participant
    @GET("api/groups/3/participant")
    Call<ParticipantResponse> getAllParticipant();
}

