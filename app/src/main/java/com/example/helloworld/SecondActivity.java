package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    TextView tvReceiveData3;
    TextView tvReceiveData1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvReceiveData1 = findViewById(R.id.tvReceiveData1);
        tvReceiveData3 = findViewById(R.id.tvReceiveData3);

        StringBuilder userInfo = new StringBuilder();
        StringBuilder userGreet = new StringBuilder("Hello, ");

        Bundle bundle = getIntent().getExtras();

        String username = "";
        String fullname = "";
        String email = "";
        String password = "";

        if (bundle != null) {
           username = bundle.getString(Constants.KEY_USERNAME);
           fullname = bundle.getString(Constants.KEY_FULLNAME);
           email = bundle.getString(Constants.KEY_EMAIL);
           password = bundle.getString(Constants.KEY_PASS);
        }

        userInfo.append(getString(R.string.USERNAME) + username).append("\n");
        userInfo.append(getString(R.string.FULL_NAME) + fullname).append("\n");
        userGreet.append(fullname);
        userInfo.append(getString(R.string.EMAIL) + email).append("\n");
        userInfo.append(getString(R.string.PASSWORD) + password).append("\n");

        tvReceiveData3.setText(userInfo);
        tvReceiveData1.setText(userGreet + getString(R.string.exclamation));
    }


    public void goToMainActivity(View view) {
        finish();
    }

}
