package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                   // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    DatePickerDialog.OnDateSetListener setListener;
    long date;

    // Variable declarations
    private EditText birthdate;
    private EditText etUsername;
    private EditText etFullname;
    private EditText etEmail;
    private EditText etPassword;

    // onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birthdate = findViewById(R.id.birthdate);
        etUsername = findViewById(R.id.etUsername);
        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        Button btnSelectDatea = findViewById(R.id.btnSelectDate);
        Button btnSignup = findViewById(R.id.signup_btn);


        // Select Date Button and DatePicker
        btnSelectDatea.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);
                String format = new SimpleDateFormat("dd.mm.yyyy").format(c.getTime());
                birthdate.setText(format);

                calculateAge(c.getTimeInMillis());
            }
        };


        // Sing Up Button
        btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if ( validateUsername(etUsername) && validateFullname(etFullname) && validateEmail(etEmail) && calculateAge(date) && validatePassword(etPassword) ) {

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("user_name", etUsername.getText().toString());
                    intent.putExtra("full_name", etFullname.getText().toString());
                    intent.putExtra("email", etEmail.getText().toString());
                    intent.putExtra("password", etPassword.getText().toString());

                    startActivity(intent);
                    finish();
                }
            }
        });

    } // onCreate


    // Calculate Age
    boolean calculateAge(long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        String birthdateInput = birthdate.getText().toString().trim();

        if (birthdateInput.isEmpty()) {
            birthdate.setError("Field can't be empty.");
            return false;
        }
        else if (age < 18) {
            birthdate.setError("You are too young to signup.");
            return false;
        }
        else {
            birthdate.setError(null);
            return true;
        }
    }


    // Validate Username
    boolean validateUsername(EditText etUsername) {

        String usernameInput = etUsername.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            etUsername.setError("Field can't be empty.");
            return false;
        }
        else if (usernameInput.length() > 15) {
            etUsername.setError("Username is too long.");
            return false;
        }
        else {
            etUsername.setError(null);
            return true;
        }
    }


    // Validate Email
    boolean validateEmail(EditText etEmail) {

        String emailInput = etEmail.getText().toString().trim();

        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//            Toast.makeText(this, "Email Validated Successfully!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (emailInput.isEmpty())
        {
            etEmail.setError("Field can't be empty.");
            return false;
        } else {
//            Toast.makeText(this, "Invalid email address!", Toast.LENGTH_SHORT).show();
            etEmail.setError("Invalid Email Address.");
            return false;
        }
    }


    // Validate Fullname
    boolean validateFullname(EditText etFullname) {

        String fullnameInput = etFullname.getText().toString().trim();

        if (fullnameInput.isEmpty()) {
            etFullname.setError("Field can't be empty.");
            return false;
        }
        else if (fullnameInput.length() > 30) {
            etFullname.setError("Your name is too long.");
            return false;
        }
        else {
            etFullname.setError(null);
            return true;
        }
    }


    // Validate Password
    boolean validatePassword(EditText etPassword) {

        String passwordInput = etPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            etPassword.setError("Field can't be empty.");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            etPassword.setError("Password too weak.");
            return false;
        }
        else {
            etPassword.setError(null);
            return true;
        }
    }


} // MainActivity
