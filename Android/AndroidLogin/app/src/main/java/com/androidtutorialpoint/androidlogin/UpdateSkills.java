package com.androidtutorialpoint.androidlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateSkills extends AppCompatActivity {

    private EditText ProgrammingLanguages,Tools,FrameWorks,Databases,description;
    private Button UpdateButton;
    private static final String TAG = "UpdateSkills";
    private static final String URL_FOR_REGISTRATION = "http://10.0.0.43:8888/android_login_example/register.php"; //change it to update.php
    ProgressDialog progressDialog;
    private myDbAdapter helper ;
    private String emailTest ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_skills);

        Bundle bundle = getIntent().getExtras();
        emailTest = bundle.getString("email");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        description = (EditText) findViewById(R.id.description);
        ProgrammingLanguages = (EditText) findViewById(R.id.programming_languages);
        Tools = (EditText) findViewById(R.id.tools);
        FrameWorks = (EditText) findViewById(R.id.frameworks);
        Databases = (EditText) findViewById(R.id.databases);
        UpdateButton = (Button) findViewById(R.id.btn_update);
        helper = new myDbAdapter(this);

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


    }


    private void submitForm() {
        Log.d("CREATION" , "inside submit form");
        helper.insertSkills(emailTest,description.getText().toString(),ProgrammingLanguages.getText().toString(),
                Tools.getText().toString(),FrameWorks.getText().toString(), Databases.getText().toString());

        helper.getDataSkills();

        Toast.makeText(getApplicationContext(),
                "Skills Succesfully inserted", Toast.LENGTH_LONG).show();

       // updateSkills(ProgrammingLanguages.getText().toString(),
             //   Tools.getText().toString(),
              //  FrameWorks.getText().toString(),
               // Databases.getText().toString());


    }

    private void updateSkills(final String programmingLanguages , final String tools,
                              final String FrameWorks,
                              final String Databases){
        progressDialog.setMessage("Updating Skills ...");
        showDialog();
        String cancel_req_tag = "register";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_REGISTRATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String user = jObj.getJSONObject("user").getString("name");
                        Toast.makeText(getApplicationContext(), "Hi " + user +", You have successfully Updated your skills!", Toast.LENGTH_SHORT).show();

                        // Launch login activity
//                        Intent intent = new Intent(
//                                UpdateSkills.this,
//                                LoginActivity.class);
//                        startActivity(intent);
//                        finish();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Update Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",emailTest);
                params.put("programmingLanguages", programmingLanguages);
                params.put("tools", tools);
                params.put("FrameWorks", FrameWorks);
                params.put("Databases", Databases);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);

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
