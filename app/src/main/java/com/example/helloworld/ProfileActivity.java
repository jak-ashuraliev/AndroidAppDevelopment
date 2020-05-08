package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();

    TextView tvName;
    TextView tvAge;
    TextView tvOccupation;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvOccupation = findViewById(R.id.tvOccupation);
        tvDescription = findViewById(R.id.tvDescription);

        StringBuilder userDisplayName = new StringBuilder("");
        StringBuilder userAge = new StringBuilder("");
        StringBuilder userOccupation = new StringBuilder("");
        StringBuilder userDescription = new StringBuilder("");

        Bundle bundle = getIntent().getExtras();

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
    }

    public void goToMainActivity(View view) {
        finish();
    }

}
