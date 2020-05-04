package com.example.helloworld;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                   // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{5,}" +               //at least 5 characters
                    "$");

    private TextView tvBirthdate;
    private EditText etUsername;
    private EditText etFullname;
    private EditText etEmail;
    private EditText etPassword;
    private int age;
    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorMsg = findViewById(R.id.errorMsg);
        etUsername = findViewById(R.id.etUsername);
        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        tvBirthdate = findViewById(R.id.tvBirthdate);
        etPassword = findViewById(R.id.etPassword);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        tvBirthdate = findViewById(R.id.tvBirthdate);
        StringBuilder date = new StringBuilder();
        month = month + 1;
        date.append(month).append('/').append(dayOfMonth).append('/').append(year);
        tvBirthdate.setText(date);

        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        int currentDay = c.get(Calendar.DAY_OF_MONTH);

        age = currentYear - year;
        if ((month > currentMonth) || (month == currentMonth && dayOfMonth > currentDay)) {
            age--;
        }
        if (age < 1) {
            age = 0;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        etUsername.setText(Constants.KEY_EMPTY);
        etFullname.setText(Constants.KEY_EMPTY);
        etEmail.setText(Constants.KEY_EMPTY);
        tvBirthdate.setText((Constants.KEY_DOB));
        tvBirthdate.setText(Constants.KEY_EMPTY);
        etPassword.setText(Constants.KEY_EMPTY);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_USERNAME, etUsername.getText().toString());
        outState.putString(Constants.KEY_FULLNAME, etFullname.getText().toString());
        outState.putString(Constants.KEY_EMAIL, etEmail.getText().toString());
        if (!tvBirthdate.getText().toString().isEmpty()) {
            outState.putString(Constants.KEY_DOB, tvBirthdate.getText().toString());
        }
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
        if (savedInstanceState.containsKey(Constants.KEY_DOB)) {
            tvBirthdate.setText(savedInstanceState.getString(Constants.KEY_DOB));
        }
        if(savedInstanceState.containsKey(Constants.KEY_PASS)) {
            etPassword.setText((String) savedInstanceState.get(Constants.KEY_PASS));
        }
    }

    private boolean isFormValid() {

        boolean isValid = true;
        String passwordInput = etPassword.getText().toString().trim();
        StringBuilder throwMsg = new StringBuilder();
        errorMsg.setText(R.string.EMPTY_MSG);

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
            throwMsg.append(getString(R.string.ERROR_MSG_EMAIL));
        }
        else if (tvBirthdate.getText().toString().isEmpty()) {
            isValid = false;
            Toast.makeText(this, R.string.selectDOB, Toast.LENGTH_SHORT).show();
            throwMsg.append(getString(R.string.ERROR_MSG_DOB));
        }
        else if (age < 18) {
            isValid = false;
            Toast.makeText(this, R.string.tooYoung, Toast.LENGTH_SHORT).show();
            throwMsg.append(getString(R.string.ERROR_MSG_DOB));
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
           isValid = false;
            Toast.makeText(this, R.string.passwordWeak, Toast.LENGTH_SHORT).show();
            throwMsg.append(getString(R.string.ERROR_MSG_PASSWORD));
        }
        else {
            return true;
        }
        return isValid;
    }

    public void goToSecondActivity(View view) {
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
