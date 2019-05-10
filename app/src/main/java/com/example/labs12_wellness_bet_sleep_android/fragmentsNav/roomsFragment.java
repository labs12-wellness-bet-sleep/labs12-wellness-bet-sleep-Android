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
import com.example.labs12_wellness_bet_sleep_android.SignUp.BottomNavigationActivity;
import com.example.labs12_wellness_bet_sleep_android.SignUp.CreateAccount;
import com.example.labs12_wellness_bet_sleep_android.SignUp.LogInActivity;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.innerActivity.joinCode;


public class roomsFragment extends Fragment {




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_rooms, container, false);

      ImageView joinGroup = (ImageView)view.findViewById(R.id.imageView_join);

      Intent in = new Intent(getActivity(), joinCode.class);
      in.putExtra("some", "some data");
      startActivity(in);

        joinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return view;

    }
}
