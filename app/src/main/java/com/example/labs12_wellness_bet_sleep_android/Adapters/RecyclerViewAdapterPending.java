package com.example.labs12_wellness_bet_sleep_android.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labs12_wellness_bet_sleep_android.Models.Participant;
import com.example.labs12_wellness_bet_sleep_android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapterPending extends RecyclerView.Adapter<RecyclerViewAdapterPending.CustomViewHolder> {

    private List<Participant> dataList;

    public RecyclerViewAdapterPending(List<Participant> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row_pending, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int position) {

        Participant participant = dataList.get(position);
        customViewHolder.rank.setText(""+(position+1));
        customViewHolder.username.setText(participant.fullName);
        Picasso.get().load("https://i.imgur.com/YcP0tik.jpg").into(customViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView rank;
        TextView username;
        ImageView image;

        CustomViewHolder(View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            username = itemView.findViewById(R.id.user_name_rankings);
            image = itemView.findViewById(R.id.profile);
        }
    }

}