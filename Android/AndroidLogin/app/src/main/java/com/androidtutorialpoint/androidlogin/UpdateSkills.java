package com.androidtutorialpoint.androidlogin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateSkills extends AppCompatActivity {

    private EditText ProgrammingLanguages , Tools,FrameWorks,Databases;
    private Button UpdateButton ;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_skills);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        ProgrammingLanguages = (EditText) findViewById(R.id.programming_languages);
        Tools = (EditText) findViewById(R.id.tools);
        FrameWorks = (EditText) findViewById(R.id.frameworks);
        Databases = (EditText) findViewById(R.id.databases);
        UpdateButton = (Button) findViewById(R.id.btn_update);
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

    }


    private void submitForm() {

        updateSkills(ProgrammingLanguages.getText().toString(),
                Tools.getText().toString(),
                FrameWorks.getText().toString(),
                Databases.getText().toString());
    }

    private void updateSkills(final String programmingLanguages , final String tools,
                              final String FrameWorks,
                              final String Databases){
        progressDialog.setMessage("Updating Skills ...");
        showDialog();

        

    }


    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
