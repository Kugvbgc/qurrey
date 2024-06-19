package com.khair.qurrey;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class View_Data extends AppCompatActivity {

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;
    ListView listView;

    ProgressBar progressBar;
    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        listView=findViewById(R.id.listView);
        progressBar=findViewById(R.id.ProgressBar);
        actionButton=findViewById(R.id.fade);

        lodData();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lodData();
            }
        });



    }
    //**********************************************************************************************
    public class MyAdapter extends BaseAdapter{

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
            View myView=layoutInflater.inflate(R.layout.view_xml,parent,false);

            TextView tv_name1=myView.findViewById(R.id.tv_name);
            TextView tv_mobile=myView.findViewById(R.id.tv_phone);
            TextView tv_email1=myView.findViewById(R.id.tv_email);

            HashMap<String,String>hashMap=arrayList.get(position);
            tv_mobile.setText(hashMap.get("mobile"));
            tv_email1.setText(hashMap.get("email"));
            tv_name1.setText(hashMap.get("name"));



            return myView;
        }
    }


    public void lodData(){
        arrayList=new ArrayList<>();
        String url="https://abulk77912.000webhostapp.com/apps/view.php";
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressBar.setVisibility(View.GONE);
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
                progressBar.setVisibility(View.GONE);


            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(View_Data.this);
        requestQueue.add(jsonArrayRequest);

    }



 //**************************************************************************************************
}