package com.example.labs12_wellness_bet_sleep_android.fragmentsNav;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.labs12_wellness_bet_sleep_android.R;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.innerActivity.joinCode;

public class roomsFragment extends Fragment {

    public roomsFragment(){

        // Required empty public constructor

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_create_join, container, false);

        ImageView joinGroup = (ImageView)view.findViewById(R.id.imageView_join);

   joinGroup.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           Intent i = new Intent(getActivity(), joinCode.class);
           startActivity(i);

       }
   });

       return view;

    }

}

