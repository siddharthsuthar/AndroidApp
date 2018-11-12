package com.androidtutorialpoint.androidlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class CreateTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
    }

    public void SearchForMatch(){
        Toast.makeText(getBaseContext(), "Searching for good matches", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(CreateTeam.this, MatchesFound.class);
        startActivity(intent);
    }
}
