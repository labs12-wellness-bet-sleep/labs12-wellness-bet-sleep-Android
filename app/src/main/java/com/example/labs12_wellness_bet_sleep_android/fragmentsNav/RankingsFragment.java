package com.example.labs12_wellness_bet_sleep_android.fragmentsNav;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.labs12_wellness_bet_sleep_android.Adapters.RecyclerViewAdapterRankings;
import com.example.labs12_wellness_bet_sleep_android.Interface.GetDataServiceRetrofit;
import com.example.labs12_wellness_bet_sleep_android.Models.Participant;
import com.example.labs12_wellness_bet_sleep_android.Models.ParticipantResponse;
import com.example.labs12_wellness_bet_sleep_android.Network.RetrofitClientInstance;
import com.example.labs12_wellness_bet_sleep_android.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingsFragment extends Fragment {

    RecyclerViewAdapterRankings adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDoalog;


    public RankingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rankings, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.custom_recycler_view_rankings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GetDataServiceRetrofit service = RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceRetrofit.class);
        Call<ParticipantResponse> call = service.getAllParticipant();

        call.enqueue(new Callback<ParticipantResponse>() {
            @Override
            public void onResponse(Call<ParticipantResponse> call, Response<ParticipantResponse> response) {
                if(response.isSuccessful()){
                    generateDataList(response.body().participant);
                }

            }

            @Override
            public void onFailure(Call<ParticipantResponse> call, Throwable t) {
                Log.e(RankingsFragment.class.getSimpleName(),t.getLocalizedMessage());
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Participant> participants) {
        adapter = new RecyclerViewAdapterRankings(participants);
        recyclerView.setAdapter(adapter);
    }
}