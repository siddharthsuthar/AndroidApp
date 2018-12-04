package com.androidtutorialpoint.androidlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class ProfileActivity extends AppCompatActivity {

    private String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle bundle = getIntent().getExtras();
        emailText = bundle.getString("email");

        Toast.makeText(getApplicationContext(), emailText, Toast.LENGTH_LONG).show();

    }
}
