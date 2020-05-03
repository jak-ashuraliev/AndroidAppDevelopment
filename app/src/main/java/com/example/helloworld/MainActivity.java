package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                   // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{5,}" +               //at least 5 characters
                    "$");

    DatePickerDialog.OnDateSetListener setListener;

    private TextView tvBirthdate;
    private EditText etUsername;
    private EditText etFullname;
    private EditText etEmail;
    private EditText etPassword;
    private int age;
    private Button btnSelectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBirthdate = findViewById(R.id.tvBirthdate);
        etUsername = findViewById(R.id.etUsername);
        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSelectDate = findViewById(R.id.btnSelectDate);

        btnSelectDate.setOnClickListener(new View.OnClickListener(){
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
            public void onDateSet(DatePicker view, int year, int month, int day) { Calendar c = Calendar.getInstance();
            int currentYear = c.get(Calendar.YEAR);
            int currentMonth = c.get(Calendar.MONTH);
            int currentDay = c.get(Calendar.DAY_OF_MONTH);

            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String format = new SimpleDateFormat(Constants.KEY_DOB).format(c.getTime());

            age = currentYear - year;
            if ((month > currentMonth) || (month == currentMonth && day > currentDay)) {
                age--;
            }
            if (age < 1) {
                age = 0;
            }
            tvBirthdate.setText(format);
            }
        };

    } // onCreate

    @Override
    protected void onRestart() {
        super.onRestart();
        etUsername.setText(Constants.KEY_EMPTY);
        etFullname.setText(Constants.KEY_EMPTY);
        etEmail.setText(Constants.KEY_EMPTY);
        tvBirthdate.setText(Constants.KEY_EMPTY);
        etPassword.setText(Constants.KEY_EMPTY);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_USERNAME, etUsername.getText().toString());
        outState.putString(Constants.KEY_FULLNAME, etFullname.getText().toString());
        outState.putString(Constants.KEY_EMAIL, etEmail.getText().toString());
        outState.putString(Constants.KEY_DOB, tvBirthdate.getText().toString());
        outState.putString(Constants.KEY_PASS, etPassword.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.containsKey(Constants.KEY_USERNAME)) {
            etUsername.setText( (String) savedInstanceState.get(Constants.KEY_USERNAME));
        }

        if(savedInstanceState.containsKey(Constants.KEY_FULLNAME)) {
            etFullname.setText((String) savedInstanceState.get(Constants.KEY_FULLNAME));
        }

        if(savedInstanceState.containsKey(Constants.KEY_EMAIL)) {
            etEmail.setText((String) savedInstanceState.get(Constants.KEY_EMAIL));
        }

        if(savedInstanceState.containsKey(Constants.KEY_DOB)) {
            tvBirthdate.setText((String) savedInstanceState.get(Constants.KEY_DOB));
        }

        if(savedInstanceState.containsKey(Constants.KEY_PASS)) {
            etPassword.setText((String) savedInstanceState.get(Constants.KEY_PASS));
        }
    }

    private boolean isFormValid() {

        boolean isValid = true;
        String passwordInput = etPassword.getText().toString().trim();

        if (etUsername.getText().toString().isEmpty() ||
                (etFullname.getText().toString().isEmpty() ||
                        (etEmail.getText().toString().isEmpty() ||
                                (tvBirthdate.getText().toString().isEmpty() ||
                                        (etPassword.getText().toString().isEmpty() ))))) {
            isValid = false;
            Toast.makeText(this, R.string.allFieldsRequired, Toast.LENGTH_SHORT).show();
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            isValid = false;
            Toast.makeText(this, R.string.enterValidEmail, Toast.LENGTH_SHORT).show();
        }
        else if (tvBirthdate.getText().toString().isEmpty()) {
            isValid = false;
            Toast.makeText(this, R.string.selectDOB, Toast.LENGTH_SHORT).show();
        }
        else if (age < 18) {
            isValid = false;
            Toast.makeText(this, R.string.tooYoung, Toast.LENGTH_SHORT).show();
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
           isValid = false;
            Toast.makeText(this, R.string.passwordWeak, Toast.LENGTH_SHORT).show();
        }
        else {
            return true;
        }
        return isValid;
    }


    public void goToNextActivity(View view) {
        if(isFormValid()){
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(Constants.KEY_USERNAME, etUsername.getText().toString());
            intent.putExtra(Constants.KEY_FULLNAME, etFullname.getText().toString());
            intent.putExtra(Constants.KEY_EMAIL, etEmail.getText().toString());
            intent.putExtra(Constants.KEY_DOB, tvBirthdate.getText().toString());
            intent.putExtra(Constants.KEY_PASS, etPassword.getText().toString());
            startActivity(intent);
        }
    }


} // MainActivity
