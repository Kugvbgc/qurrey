package com.khair.qurrey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;

public class SplashScreenActivity extends AppCompatActivity {

    TextView textView;
    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView=findViewById(R.id.textView2);
        gridView=findViewById(R.id.gridview);

        lodData();
        MyAdapter myAdapter=new MyAdapter();
        gridView.setAdapter(myAdapter);






        String url="https://abulk77912.000webhostapp.com/apps/item_insert.php";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                textView.setText(s);
                Toast.makeText(SplashScreenActivity.this, ""+s, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue queue= Volley.newRequestQueue(SplashScreenActivity.this);
        queue.add(stringRequest);



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
           View myView=layoutInflater.inflate(R.layout.grid_layout,parent,false);

           TextView textView1=myView.findViewById(R.id.textView3);
           LinearLayout linearLayout=myView.findViewById(R.id.list_item);

           HashMap<String,String>hashMap=arrayList.get(position);
           String tittle=hashMap.get("tittle");
           textView1.setText(tittle);

           linearLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  if (position==0){
                      startActivity(new Intent(SplashScreenActivity.this, Insert_Data.class));
                  }else if (position==1){
                      startActivity(new Intent(SplashScreenActivity.this, View_Data.class));
                  }else if (position==2){
                      startActivity(new Intent(SplashScreenActivity.this, update_Data.class));
                  }else if (position==3){
                      startActivity(new Intent(SplashScreenActivity.this, Dalete_Data.class));
                  }else if (position==4){
                      startActivity(new Intent(SplashScreenActivity.this, Query_Data.class));
                  }
               }
           });


           return myView;
       }
   }

  //**********************************************************************************************
  public void lodData(){

        hashMap=new HashMap<>();
        hashMap.put("tittle","Insert Data");
        arrayList.add(hashMap);

      hashMap=new HashMap<>();
      hashMap.put("tittle","View Data");
      arrayList.add(hashMap);


      hashMap=new HashMap<>();
      hashMap.put("tittle","update Data");
      arrayList.add(hashMap);

      hashMap=new HashMap<>();
      hashMap.put("tittle","Delete Data");
      arrayList.add(hashMap);

      hashMap=new HashMap<>();
      hashMap.put("tittle","Query Data");
      arrayList.add(hashMap);




  }



 //**********************************************************************************************

}