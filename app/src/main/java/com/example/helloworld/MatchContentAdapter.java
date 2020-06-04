package com.example.helloworld;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MatchContentAdapter extends RecyclerView.Adapter<MatchViewHolder> {

    private static final String TAG = MatchContentAdapter.class.getName();
    private List<MatchItem> matches;
    private MatchesFragment.LikedClickListener listener;

    MatchContentAdapter(List<MatchItem> mMatches, MatchesFragment.LikedClickListener mListener) {
        matches = mMatches;
        listener = mListener;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        return new MatchViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {

        Resources res = holder.itemView.getContext().getResources();
        MatchItem match = matches.get(position);

        Picasso.get().load(match.imageUrl).into(holder.matchImage);
        holder.matchName.setText(match.name);
        holder.matchOccupation.setText(match.occupation);
        holder.matchDescription.setText(match.description);

        if (match.liked) {
            holder.like_btn.setImageResource(R.drawable.ic_liked_heart);
        } else {
            holder.like_btn.setImageResource(R.drawable.ic_unliked_heart);
        }

        holder.like_btn.setOnClickListener(view -> {
            if (listener != null) {
                if (match.liked) {
                    holder.like_btn.setImageResource(R.drawable.ic_unliked_heart);
                    StringBuilder onClickLikeMsg = new StringBuilder(res.getString(R.string.UNLIKED)).append(holder.matchName.getText());
                    Toast.makeText(holder.itemView.getContext(), onClickLikeMsg, Toast.LENGTH_SHORT).show();
                    onClickLikeMsg.setLength(0);
                } else {
                    holder.like_btn.setImageResource(R.drawable.ic_liked_heart);
                    StringBuilder onClickLikeMsg = new StringBuilder(res.getString(R.string.LIKED)).append(holder.matchName.getText());
                    Toast.makeText(holder.itemView.getContext(), onClickLikeMsg, Toast.LENGTH_SHORT).show();
                    onClickLikeMsg.setLength(0);
                }
                listener.LikedClickListener(match);
            }
        });

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

}
