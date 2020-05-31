package com.example.helloworld;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class MatchesRecyclerViewAdapter extends RecyclerView.Adapter<MatchesFragment.ViewHolder> {
    private List<MatchItem> matches;
    private LikedEventListener listener;

    private static final String TAG = MatchesRecyclerViewAdapter.class.getName();


    public MatchesRecyclerViewAdapter(List<MatchItem> mMatches, LikedEventListener mListener) {
        matches = mMatches;
        listener = mListener;
    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MatchesFragment.ViewHolder holder, int position) {
//        Resources res = holder.itemView.getContext().getResources();
//        String[] mDescription = res.getStringArray(R.array.matchDescArr);
//        MatchItem match = matches.get(position);
//
//        holder.matchName.setText(match.getName());
//        holder.matchDes.setText(mDescription[position]);
//        Picasso.get().load(match.getImageUrl()).into(holder.matchImage);
//
//        if (match.isLiked()) {
//            holder.like_btn.setImageResource(R.drawable.ic_liked_heart);
//        } else {
//            holder.like_btn.setImageResource(R.drawable.ic_unliked_heart);
//        }
//
//        holder.like_btn.setOnClickListener(view -> {
//            if (listener != null) {
//                if (match.isLiked()) {
//                    holder.like_btn.setImageResource(R.drawable.ic_liked_heart);
//                    StringBuilder likeMsg = new StringBuilder(res.getString(R.string.UNLIKED))
//                            .append(holder.matchName.getText());
//
//                    Toast.makeText(holder.itemView.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
//                    likeMsg.setLength(0);
//                } else {
//                    holder.like_btn.setImageResource(R.drawable.ic_liked_heart);
//                    StringBuilder likeMsg = new StringBuilder(res.getString(R.string.LIKED))
//                            .append(holder.matchName.getText());
//
//                    Toast.makeText(holder.itemView.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
//                    likeMsg.setLength(0);
//                }
//                listener.updateLiked(match);
//
//            }
//
//        });
//    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}