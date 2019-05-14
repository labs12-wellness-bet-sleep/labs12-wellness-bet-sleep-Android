package com.example.labs12_wellness_bet_sleep_android.Adapters;

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

public class RecyclerViewAdapterRankings extends RecyclerView.Adapter<RecyclerViewAdapterRankings.CustomViewHolder> {

    private List<Participant> dataList;

    public RecyclerViewAdapterRankings(List<Participant> dataList){
        this.dataList = dataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row_rankings, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Participant participant = dataList.get(position);
        holder.rank.setText(""+(position+1));
        holder.username.setText(participant.username);
        Picasso.get().load("https://i.imgur.com/YcP0tik.jpg").into(holder.image);
        // TODO This link from the participant is not working. http://lorempixel.com/640/480/
        // Glide.with(holder.itemView).load("https://i.imgur.com/YcP0tik.jpg").into(holder.image);

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