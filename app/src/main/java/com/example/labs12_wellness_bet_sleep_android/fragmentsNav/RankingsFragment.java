package com.example.labs12_wellness_bet_sleep_android.fragmentsNav;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.labs12_wellness_bet_sleep_android.Adapters.RecyclerViewAdapterRankings;
import com.example.labs12_wellness_bet_sleep_android.Models.ListItemRankings;
import com.example.labs12_wellness_bet_sleep_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Context context;

    private List<ListItemRankings> listItems;

    public RankingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_rankings, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_rankings);
        recyclerView.setHasFixedSize(false);        // not sure about this, experiement with true/false
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        listItems = new ArrayList<>();






        return view;
    }

}
