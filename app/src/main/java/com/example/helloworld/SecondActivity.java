package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    // variables
    TextView tvReceiveData3;
    TextView tvReceiveData1;
    TextView testDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // get textview1 and textview3
        tvReceiveData1 = findViewById(R.id.tvReceiveData1);
        tvReceiveData3 = findViewById(R.id.tvReceiveData3);

        Button btn_singnout = findViewById(R.id.signout_btn);

        // StringBuilder to display userInfo
        StringBuilder userInfo = new StringBuilder();

        // StringBuilder to display userGreet
        StringBuilder userGreet = new StringBuilder("Hello, ");

        Bundle bundle = getIntent().getExtras();

        // empty strings and int
        String username = "";
        String fullname = "";
        String email = "";
        String password = "";

        // check if bundle is not empty
        if (bundle != null) {
           username = bundle.getString("user_name");
           fullname = bundle.getString("full_name");
           email = bundle.getString("email");
           password = bundle.getString("password");
        }

        // append values
        userInfo.append("USERNAME: \t\t" + username).append("\n");
//        Log.i(TAG, new StringBuilder().append(username).toString());

        userInfo.append("FULL NAME: \t" + fullname).append("\n");
//        Log.i(TAG, new StringBuilder().append(fullname).toString());
        userGreet.append(fullname);

        userInfo.append("EMAIL: \t\t\t\t\t\t\t" + email).append("\n");
//        Log.i(TAG, new StringBuilder().append(email).toString());

        userInfo.append("PASSWORD: \t" + password).append("\n");
//        Log.i(TAG, new StringBuilder().append(password).toString());


        // display userInfo and userGreet on textviews
        tvReceiveData3.setText(userInfo);
        tvReceiveData1.setText(userGreet + "!");


        // SIGN OUT Button
        btn_singnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

}
