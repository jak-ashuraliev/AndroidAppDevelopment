package com.example.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MatchesFragment extends Fragment {

    private List<MatchItem> mMatches;
    private MatchesFragment.LikedClickListener mListener;
    private double latitude;
    private double longitude;

    public MatchesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMatches = getArguments().getParcelableArrayList(Constants.COLLECTION_MATCHES);
        latitude = getArguments().getDouble(Constants.collection_latitude);
        longitude = getArguments().getDouble(Constants.collection_longitude);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MatchContentAdapter(mMatches, mListener));

        return recyclerView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (LikedClickListener) context;
    }

    public interface LikedClickListener {
        void LikedClickListener(MatchItem item);
    }

}
