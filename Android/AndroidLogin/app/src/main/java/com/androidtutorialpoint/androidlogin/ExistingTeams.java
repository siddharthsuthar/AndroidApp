package com.androidtutorialpoint.androidlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ExistingTeams extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_teams);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RAdapter radapter = new RAdapter(this);
        recyclerView.setAdapter(radapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //val recyclerView:RecyclerView = findViewById(R.id.recyclerview);
        //recyclerView.layoutManager  = LinearLayoutManager(this);


    }
}
