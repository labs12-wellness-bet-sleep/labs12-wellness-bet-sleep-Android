package com.example.labs12_wellness_bet_sleep_android.fragmentsNav;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.labs12_wellness_bet_sleep_android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApprovedFragment extends Fragment {


    public ApprovedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_approved, container, false);
    }

}
