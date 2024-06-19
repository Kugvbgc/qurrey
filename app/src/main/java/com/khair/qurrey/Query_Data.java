package com.khair.qurrey;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Query_Data extends AppCompatActivity {

    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;
    ListView listView;
    ProgressBar progressBar;
    MyAdapter myAdapter=new MyAdapter(arrayList);

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_data);
        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progress);
        searchView = findViewById(R.id.SearchView);


        details();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);

                return true;
            }
        });








    }

    private void details() {
        arrayList=new ArrayList<>();

        String erl = ("https://abulk77912.000webhostapp.com/apps/view.php");
        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, erl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressBar.setVisibility(View.GONE);

                try {
                    for (int x = 0; x < jsonArray.length(); x++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
                        String name = jsonObject.getString("name");
                        String mobile = jsonObject.getString("mobile");
                        String email = jsonObject.getString("email");

                        hashMap = new HashMap<>();
                        hashMap.put("name", name);
                        hashMap.put("mobile", mobile);
                        hashMap.put("email", email);
                        arrayList.add(hashMap);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (arrayList.size()>0){
                    listView.setAdapter(myAdapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);

                new AlertDialog.Builder(Query_Data.this)
                        .setTitle("server response")
                        .setMessage(volleyError.getMessage())
                        .show();


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Query_Data.this);
        requestQueue.add(jsonArrayRequest);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    private void fileList(String newText) {
        ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
        for (HashMap<String, String> detailsItem : arrayList) {
            if (detailsItem.get("name").toLowerCase().contains(newText.toLowerCase())) {
                arrayList1.add(detailsItem);
            }
        }
        if (arrayList1.isEmpty()) {
            Toast.makeText(Query_Data.this, "No Data Found", Toast.LENGTH_SHORT).show();
        } else {
            myAdapter.setFilteredList(arrayList1);
        }
    }

    public class MyAdapter extends BaseAdapter {
        ArrayList<HashMap<String,String>>myarrayList;

        public MyAdapter(ArrayList<HashMap<String, String>> arrayList1) {
            this.myarrayList = arrayList1;
        }
        public void setFilteredList(ArrayList<HashMap<String, String>> filteredList) {
            this.myarrayList = filteredList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return myarrayList.size();
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
            View myView=layoutInflater.inflate(R.layout.layout,parent,false);

            TextView tv_name1=myView.findViewById(R.id.tv_name);
            TextView tv_mobile=myView.findViewById(R.id.tv_phone);
            TextView tv_email1=myView.findViewById(R.id.tv_email);

            HashMap<String,String>hashMap=myarrayList.get(position);
            tv_mobile.setText(hashMap.get("mobile"));
            tv_email1.setText(hashMap.get("email"));
            tv_name1.setText(hashMap.get("name"));



            return myView;
        }


        ///=======================================================================================





    ///++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }












}