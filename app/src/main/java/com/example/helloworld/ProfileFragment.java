package com.example.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvAge = view.findViewById(R.id.tvAge);
        TextView tvOccupation = view.findViewById(R.id.tvOccupation);
        TextView tvDescription = view.findViewById(R.id.tvDescription);

        StringBuilder userDisplayName = new StringBuilder("");
        StringBuilder userAge = new StringBuilder("");
        StringBuilder userOccupation = new StringBuilder("");
        StringBuilder userDescription = new StringBuilder("");

        Bundle bundle = getArguments();

        String firstname = "";
        String profileAge = "";
        String occupation = "";
        String description = "";

        if (bundle != null) {
            firstname = bundle.getString(Constants.KEY_FIRSTNAME).trim();
            profileAge = bundle.getString(Constants.KEY_AGE).trim();
            occupation = bundle.getString(Constants.KEY_OCCUPATION).trim();
            description = bundle.getString(Constants.KEY_DESCRIPTION).trim();
        }

        userDisplayName.append(firstname);
        userAge.append(profileAge + " ").append(getString(R.string.YEARS_OLD));
        userOccupation.append(getString(R.string.OCCUPATION) + occupation);
        userDescription.append(getString(R.string.DESCRIPTION) + description);

        tvName.setText(userDisplayName);
        tvAge.setText(userAge);
        tvOccupation.setText(userOccupation);
        tvDescription.setText(userDescription);

        return view;
    }


}
