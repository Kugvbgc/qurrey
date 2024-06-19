package com.khair.qurrey;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Insert_Data extends AppCompatActivity {

    TextInputEditText edName,edEmail,edMobile;
    ProgressBar progressBar;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        edName=findViewById(R.id.edName);
        edEmail=findViewById(R.id.edEmail);
        edMobile=findViewById(R.id.edNumber);
        progressBar=findViewById(R.id.ProgressBar);
        buttonSubmit=findViewById(R.id.Upload_button);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String name=edName.getText().toString();
              String mobile=edMobile.getText().toString();
              String email=edEmail.getText().toString();


                String url="https://abulk77912.000webhostapp.com/apps/hello.php?"+"n="+name
                        +"&m="+mobile+"&e="+email;
                progressBar.setVisibility(View.VISIBLE);

                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String string) {
                        progressBar.setVisibility(View.GONE);

                        new AlertDialog.Builder(Insert_Data.this)
                                .setTitle("saver Response")
                                .setMessage(string)
                                .show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressBar.setVisibility(View.GONE);


                    }
                });

                if (edName.length()>0&&edEmail.length()>0&&edMobile.length()>0){
                    RequestQueue requestQueue= Volley.newRequestQueue(Insert_Data.this);
                    requestQueue.add(stringRequest);

                }else {
                    edName.setError("please enter your name");
                    edEmail.setError("please enter your number");
                    edMobile.setError("please enter your email");
                    progressBar.setVisibility(View.GONE);

                }





            }
        });

    }
}