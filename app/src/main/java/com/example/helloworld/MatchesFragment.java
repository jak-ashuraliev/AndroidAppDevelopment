package com.example.helloworld;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MatchesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.card_recycler_view, container, false);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView matchImage;
        public TextView matchName;
        public TextView matchDes;
        public ImageButton like_btn;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.fragment_matches, parent, false));
            matchImage = (ImageView) itemView.findViewById(R.id.matchImg);
            matchName = (TextView) itemView.findViewById(R.id.matchName);
            matchDes = (TextView) itemView.findViewById(R.id.matchDesc);

        }
    }

    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

        private static final int LENGTH = 6;
        private final String[] matchNames;
        private final String[] matchDescriptions;
        private final Drawable[] matchImages;

        public ContentAdapter(Context context) {

            Resources resources = context.getResources();
            TypedArray a = resources.obtainTypedArray(R.array.matchImgArr);
            matchImages = new Drawable[a.length()];
            matchNames = resources.getStringArray(R.array.matchNamesArr);
            matchDescriptions = resources.getStringArray(R.array.matchDescArr);

            for (int i = 0; i < matchImages.length; i++) {
                matchImages[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.matchImage.setImageDrawable(matchImages[position % matchImages.length]);
            holder.matchName.setText(matchNames[position % matchNames.length]);
            holder.matchDes.setText(matchDescriptions[position % matchDescriptions.length]);

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }


}
