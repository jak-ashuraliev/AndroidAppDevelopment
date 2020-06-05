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

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView tvBirthdate;
    private EditText etUsername;
    private EditText etFirstname;
    private EditText etLastname;
    private EditText etOccupation;
    private EditText etDescription;
    private EditText etEmail;
    private int age;
    private TextView errorMsg;
    String profileAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorMsg = findViewById(R.id.errorMsg);
        etUsername = findViewById(R.id.etUsername);
        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        etEmail = findViewById(R.id.etEmail);
        tvBirthdate = findViewById(R.id.tvBirthdate);
        etOccupation = findViewById(R.id.etOccupation);
        etDescription = findViewById(R.id.etDescription);
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

        profileAge = String.valueOf(age);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        errorMsg.setText(Constants.KEY_EMPTY);
        etUsername.setText(Constants.KEY_EMPTY);
        etFirstname.setText(Constants.KEY_EMPTY);
        etLastname.setText(Constants.KEY_EMPTY);
        etEmail.setText(Constants.KEY_EMPTY);
        tvBirthdate.setText((Constants.KEY_EMPTY));
        etOccupation.setText(Constants.KEY_EMPTY);
        etDescription.setText(Constants.KEY_EMPTY);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_USERNAME, etUsername.getText().toString());
        outState.putString(Constants.KEY_FIRSTNAME, etFirstname.getText().toString());
        outState.putString(Constants.KEY_LASTNAME, etLastname.getText().toString());
        outState.putString(Constants.KEY_EMAIL, etEmail.getText().toString());
        if (!tvBirthdate.getText().toString().isEmpty()) {
            outState.putString(Constants.KEY_DOB, tvBirthdate.getText().toString());
        }
        outState.putString(Constants.KEY_OCCUPATION, etOccupation.getText().toString());
        outState.putString(Constants.KEY_DESCRIPTION, etDescription.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.containsKey(Constants.KEY_USERNAME)) {
            etUsername.setText( (String) savedInstanceState.get(Constants.KEY_USERNAME));
        }
        if(savedInstanceState.containsKey(Constants.KEY_FIRSTNAME)) {
            etFirstname.setText((String) savedInstanceState.get(Constants.KEY_FIRSTNAME));
        }
        if(savedInstanceState.containsKey(Constants.KEY_LASTNAME)) {
            etLastname.setText((String) savedInstanceState.get(Constants.KEY_LASTNAME));
        }
        if(savedInstanceState.containsKey(Constants.KEY_EMAIL)) {
            etEmail.setText((String) savedInstanceState.get(Constants.KEY_EMAIL));
        }
        if (savedInstanceState.containsKey(Constants.KEY_DOB)) {
            tvBirthdate.setText(savedInstanceState.getString(Constants.KEY_DOB));
        }
        if(savedInstanceState.containsKey(Constants.KEY_OCCUPATION)) {
            etOccupation.setText((String) savedInstanceState.get(Constants.KEY_OCCUPATION));
        }
        if(savedInstanceState.containsKey(Constants.KEY_DESCRIPTION)) {
            etDescription.setText((String) savedInstanceState.get(Constants.KEY_DESCRIPTION));
        }
    }

    private boolean isFormValid() {

        boolean isValid = true;
        StringBuilder throwMsg = new StringBuilder();
        errorMsg.setText(R.string.EMPTY_MSG);

        if (etUsername.getText().toString().isEmpty() || (etFirstname.getText().toString().isEmpty() ||
                        (etLastname.getText().toString().isEmpty() || (etEmail.getText().toString().isEmpty() ||
                                (tvBirthdate.getText().toString().isEmpty() || (etOccupation.getText().toString().isEmpty() ||
                                        (etDescription.getText().toString().isEmpty() ))))))) {
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
        else if (etOccupation.getText().toString().isEmpty()) {
            isValid = false;
            Toast.makeText(this, R.string.enterOccupation, Toast.LENGTH_SHORT).show();
            throwMsg.append(getString(R.string.ERROR_MSG_OCCUPATION));
        }
        else if (etDescription.getText().toString().isEmpty()) {
            isValid = false;
            Toast.makeText(this, R.string.enterDescription, Toast.LENGTH_SHORT).show();
            throwMsg.append(getString(R.string.ERROR_MSG_DESCRIPTION));
        }
        else {
            return true;
        }
        return isValid;
    }

    public void goToSecondActivity(View view) {
        if(isFormValid()){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(Constants.KEY_USERNAME, etUsername.getText().toString());
            intent.putExtra(Constants.KEY_FIRSTNAME, etFirstname.getText().toString());
            intent.putExtra(Constants.KEY_LASTNAME, etLastname.getText().toString());
            intent.putExtra(Constants.KEY_EMAIL, etEmail.getText().toString());
            intent.putExtra(Constants.KEY_DOB, tvBirthdate.getText().toString());
            intent.putExtra(Constants.KEY_AGE, profileAge);
            intent.putExtra(Constants.KEY_OCCUPATION, etOccupation.getText().toString());
            intent.putExtra(Constants.KEY_DESCRIPTION, etDescription.getText().toString());
            startActivity(intent);
        }
    }

} // MainActivity
