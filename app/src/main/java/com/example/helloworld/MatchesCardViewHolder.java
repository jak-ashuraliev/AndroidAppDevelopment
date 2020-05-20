//package com.example.helloworld;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class MatchesCardViewHolder extends RecyclerView.ViewHolder {
//
//    ImageView matchImage;
//    TextView matchName;
//    TextView matchDesc;
//    private ImageView like_btn;
//
//
//    MatchesCardViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        matchImage = itemView.findViewById(R.id.matchImg);
//        matchName = itemView.findViewById(R.id.matchName);
//        matchDesc = itemView.findViewById(R.id.matchDesc);
//        like_btn = itemView.findViewById(R.id.like_btn);
//
//        like_btn.setOnClickListener(view -> {
//            if (like_btn.getTag().equals("Unliked")) {
//                StringBuilder msg = new StringBuilder("You Liked").append(matchName.getText());
//                like_btn.setTag("Liked");
//                Toast.makeText(itemView.getContext(), msg, Toast.LENGTH_SHORT).show();
//                msg.setLength(0);
//            } else {
//                StringBuilder msg = new StringBuilder("You liked").append(matchName.getText());
//                like_btn.setTag("Unliked");
//                Toast.makeText(itemView.getContext(), msg, Toast.LENGTH_SHORT).show();
//                msg.setLength(0);
//            }
//        });
//
//    }
//}
