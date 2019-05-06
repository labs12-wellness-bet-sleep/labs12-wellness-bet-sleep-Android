package com.example.labs12_wellness_bet_sleep_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labs12_wellness_bet_sleep_android.Models.ListItemRankings;
import com.example.labs12_wellness_bet_sleep_android.R;

import java.util.List;

public class RecyclerViewAdapterRankings extends RecyclerView.Adapter<RecyclerViewAdapterRankings.ViewHolder> {

    private List<ListItemRankings> listItemRankings;
    private Context context;

    public RecyclerViewAdapterRankings(List<ListItemRankings> listItemRankings, Context context) {
        this.listItemRankings = listItemRankings;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterRankings.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rankings, parent, false);
    return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterRankings.ViewHolder holder, int position) {

        ListItemRankings listItem = listItemRankings.get(position);

        holder.textViewHeadImages.setText(listItem.getProfilePhoto());
        holder.textViewHeadParticipant.setText(listItem.getUsername());

    }

    @Override
    public int getItemCount() {
        return listItemRankings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHeadImages;
        public TextView textViewHeadParticipant;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewHeadImages = itemView.findViewById(R.id.text_view_head_images);
            textViewHeadParticipant = itemView.findViewById(R.id.text_view_head_participant);
        }
    }
}
