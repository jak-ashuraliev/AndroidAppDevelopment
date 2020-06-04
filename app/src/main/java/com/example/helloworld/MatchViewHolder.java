package com.example.helloworld;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchViewHolder extends RecyclerView.ViewHolder {

    ImageView matchImage;
    TextView matchName;
    TextView matchOccupation;
    TextView matchDescription;
    ImageView like_btn;

    MatchViewHolder(@NonNull View itemView) {
        super(itemView);
        matchImage = itemView.findViewById(R.id.matchImg);
        matchName = itemView.findViewById(R.id.matchName);
        matchOccupation = itemView.findViewById(R.id.matchOccup);
        matchDescription = itemView.findViewById(R.id.matchDesc);
        like_btn = itemView.findViewById(R.id.like_btn);
    }

}
