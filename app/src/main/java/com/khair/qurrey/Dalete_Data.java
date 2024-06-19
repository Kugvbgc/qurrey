package com.khair.qurrey;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Dalete_Data extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dalete_data);

        listView = findViewById(R.id.listView);




        lodData();

    }
    //=================================================================================================
    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView=layoutInflater.inflate(R.layout.layout2,parent,false);
            TextView tv_name=myView.findViewById(R.id.tvName);
            TextView tv_mobile=myView.findViewById(R.id.tvMobile);
            TextView tv_email=myView.findViewById(R.id.tvEmail);
            Button delete=myView.findViewById(R.id.delete);




            HashMap<String,String> hashMap=arrayList.get(position);
            String name=hashMap.get("name");
            String mobile=hashMap.get("mobile");
            String email=hashMap.get("email");
            String idd=hashMap.get("id");


            tv_name.setText(name);
            tv_mobile.setText(mobile);
            tv_email.setText(email);

            //update.setOnClickListener(new View.OnClickListener() {
               // @Override
               // public void onClick(View v) {
                    //String namety =tv_namet.getText().toString();
                    //String mobileki =tv_mobilek.getText().toString();
                   // String emailer=tv_emailj.getText().toString();

                   //String url="https://abulk77912.000webhostapp.com/apps/update.php?n="+namety+"&m="+mobileki+
                           // "&e="+emailer+"&id="+idd;

                   // progressBar.setVisibility(View.VISIBLE);

                   // StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                      //  @Override
                       // public void onResponse(String s) {
                          //  progressBar.setVisibility(View.GONE);
                           // new AlertDialog.Builder(Dalete_Data.this)
                                //    .setMessage(s)
                                    //.setTitle("server response")
                                   // .show();

                           // lodData();

                       // }
                   // }, new Response.ErrorListener() {
                       // @Override
                       // public void onErrorResponse(VolleyError volleyError) {
                           // progressBar.setVisibility(View.GONE);
                          //  new AlertDialog.Builder(Dalete_Data.this)
                                   // .setMessage(volleyError.getMessage())
                                    //.setTitle("server response")
                                   // .show();

                      //  }
                   // });

                   // if (tv_namet.length()>0&&tv_mobilek.length()>0&&tv_emailj.length()>0){
                       // RequestQueue queue= Volley.newRequestQueue(Dalete_Data.this);
                       // queue.add(request);
                   // }else {
                      //  progressBar.setVisibility(View.GONE);
                       // tv_emailj.setError("please enter email");
                       // tv_namet.setError("please enter Name ");
                       // tv_mobilek.setError("please enter Number");
                   // }

               // }
            //});

              delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
             String erl="https://abulk77912.000webhostapp.com/apps/DELETE.php?id="+idd;
            StringRequest request=new StringRequest(Request.Method.GET, erl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

             new AlertDialog.Builder(Dalete_Data.this)
              .setMessage(s)
            .setTitle("server response")
             .show();

             lodData();

            }
             }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError volleyError) {

             new AlertDialog.Builder(Dalete_Data.this)
             .setMessage(volleyError.getMessage())
             .setTitle("server response")
            .show();

             }
            });

            RequestQueue queue= Volley.newRequestQueue(Dalete_Data.this);
            queue.add(request);



             }
             });








            return myView;
        }
    }
    ///=================================================================================================
    public void lodData(){
        arrayList=new ArrayList<>();
        String url="https://abulk77912.000webhostapp.com/apps/view.php";

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                try {
                    for ( int x=0;x<jsonArray.length();x++){
                        JSONObject jsonObject=jsonArray.getJSONObject(x);
                        String name=jsonObject.getString("name");
                        String mobile=jsonObject.getString("mobile");
                        String email=jsonObject.getString("email");
                        String id=jsonObject.getString("id");

                        hashMap=new HashMap<>();
                        hashMap.put("name",name);
                        hashMap.put("mobile",mobile);
                        hashMap.put("email",email);
                        hashMap.put("id",id);
                        arrayList.add(hashMap);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

                if (arrayList.size()>0){
                    MyAdapter myAdapter=new MyAdapter();
                    listView.setAdapter(myAdapter);
                }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {



            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(Dalete_Data.this);
        requestQueue.add(jsonArrayRequest);

    }
///=
}