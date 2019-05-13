package com.example.labs12_wellness_bet_sleep_android.fragmentsNav;


import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labs12_wellness_bet_sleep_android.Interface.GetDataServiceRetrofit;
import com.example.labs12_wellness_bet_sleep_android.Models.ParticipantResponse;
import com.example.labs12_wellness_bet_sleep_android.Network.RetrofitClientInstance;
import com.example.labs12_wellness_bet_sleep_android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    ImageView profilePicImageView;
    TextView groupNameTextView;
    TextView adminTextView;
    TextView buyInTextView;
    TextView currentPotTextView;
    TextView startDateTextView;
    TextView endDateTextView;
    TextView messageTextview;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rankings, container, false);
        return view;

        //placeHOlder for Image Display
        /**
         *
         * picasso.load(url)
         *     .placeholder( R.drawable.place_holder )
         *     .into(imageView);
         *
         *     **/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profilePicImageView = view.findViewById(R.id.info_fragment_profile_pic_imageview);
        groupNameTextView = view.findViewById(R.id.info_fragment_group_name_textview);
        adminTextView = view.findViewById(R.id.info_fragment_admin_textview);
        buyInTextView = view.findViewById(R.id.info_fragment_buyin_textview);
        currentPotTextView = view.findViewById(R.id.info_fragment_currentpot_textview);
        startDateTextView = view.findViewById(R.id.info_fragment_startdate_textview);
        endDateTextView = view.findViewById(R.id.info_fragment_enddate_textview);
        messageTextview = view.findViewById(R.id.info_fragment_message_textview);

        GetDataServiceRetrofit serviceInfo = RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceRetrofit.class);
        Call<ParticipantResponse> call = serviceInfo.getAllParticipant();

        call.enqueue(new Callback<ParticipantResponse>() {
            @Override
            public void onResponse(Call<ParticipantResponse> call, Response<ParticipantResponse> response) {
                if(response.isSuccessful()){
//                    generateDataList(response.body().participant);
                }
            }

            @Override
            public void onFailure(Call<ParticipantResponse> call, Throwable t) {

            }
        });
    }
}
