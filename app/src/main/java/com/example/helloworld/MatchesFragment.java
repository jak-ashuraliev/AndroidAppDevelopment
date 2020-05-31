package com.example.helloworld;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {

    FirebaseViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.card_recycler_view, container, false);

        viewModel = new FirebaseViewModel();

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView matchImage;
        public TextView matchName;
        public TextView matchDes;
        public TextView matchTvAction;
        public ImageButton like_btn;
        public Context context;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent, FirebaseViewModel viewModel) {
            super(inflater.inflate(R.layout.fragment_matches, parent, false));

            matchImage = itemView.findViewById(R.id.matchImg);
            matchName = itemView.findViewById(R.id.matchName);
            matchDes = itemView.findViewById(R.id.matchDesc);
            matchTvAction = itemView.findViewById(R.id.tvAction);
            like_btn = itemView.findViewById(R.id.like_btn);

            Resources res = itemView.getResources();
        }

    }

    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

        private int LENGTH;
        private ArrayList<MatchItem> matches;
        private FirebaseViewModel viewModel;

        public ContentAdapter(Context context, FirebaseViewModel viewModel) {
            this.viewModel = viewModel;
            context.getResources();
            viewModel = new FirebaseViewModel();
            viewModel.getMatchItems((fb_matches -> {
                this.matches = fb_matches;
                LENGTH = matches.size();
                notifyDataSetChanged();
            }));
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, viewModel);
        }


        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            if (this.matches != null) {
                MatchItem match = this.matches.get(position % this.matches.size());
                Picasso.get().load(match.getImageUrl()).into(holder.matchImage);
                holder.matchName.setText(match.getName());


                holder.like_btn.setOnClickListener(view -> {
                    if (!match.isLiked()) {
                        match.setLiked(true);
                        Toast.makeText(holder.itemView.getContext(), holder.context.getString(R.string.LIKED) + holder.matchName.getText(), Toast.LENGTH_SHORT).show();
                    } else {

                        match.setLiked(false);
                        Toast.makeText(holder.itemView.getContext(), holder.context.getString(R.string.UNLIKED) + holder.matchName.getText(), Toast.LENGTH_SHORT).show();
                    }
                    viewModel.updateLiked(match);
                });
            }
    }


    @Override
        public int getItemCount () {
            return LENGTH;
        }
    }

}



