package com.androidtutorialpoint.androidlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTeam extends AppCompatActivity {

    private EditText expertiseIn,purpose,taskDescription ;
    private Button btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        expertiseIn = (EditText) findViewById(R.id.expertiseIn);
        purpose = (EditText) findViewById(R.id.purpose);
        taskDescription = (EditText) findViewById(R.id.taskDescription);
        btnsearch = (Button) findViewById(R.id.search);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchForMatch();
            }
        });

    }

    public void SearchForMatch(){
        Toast.makeText(getBaseContext(), "Searching for good matches", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(CreateTeam.this, MatchesFound.class);
        startActivity(intent);
    }
}
