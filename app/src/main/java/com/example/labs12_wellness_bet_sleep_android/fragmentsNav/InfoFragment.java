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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        View view = inflater.inflate(R.layout.fragment_info, container, false);
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
                    ParticipantResponse participantResponse = response.body();
                    groupNameTextView.setText(participantResponse.groupName);
                    adminTextView.setText("Admin: TODO");
                    buyInTextView.setText("Buy in:$"+participantResponse.buyInAmt);
                    currentPotTextView.setText("Current Pot Total:$ TODO");
                    startDateTextView.setText("Start:"+parseDate(participantResponse.startDate));
                    endDateTextView.setText("End:"+parseDate(participantResponse.endDate));
                    messageTextview.setText(participantResponse.groupMessage);
                }
            }

            @Override
            public void onFailure(Call<ParticipantResponse> call, Throwable t) {

            }
        });
    }

    /**
     *
     * @param input should be in yyyy-MM-dd'T'HH:mm:ss.SSSZ format
     *              Ex: 2018-01-08T00:00:00.000Z
     *                  2015-04-28T14:23:38.521Z
     * @return
     */
    String parseDate(String input){
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);

        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            Date date = inputFormat.parse(input);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // cannot format the input
        return input;
    }
}

