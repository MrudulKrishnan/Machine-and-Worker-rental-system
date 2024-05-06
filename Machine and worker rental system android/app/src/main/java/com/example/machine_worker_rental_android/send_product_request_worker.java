package com.example.machine_worker_rental_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class send_product_request_worker extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SharedPreferences sh;
    ListView l1_send_product_request;
    SearchView sv1_search_product_worker;
    String url;
    ArrayList<String> product_name_arr, product_type_arr, details_arr, image_arr, price_per_day_arr,
            product_id_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_product_request_worker);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1_send_product_request = findViewById(R.id.L1_SendProductRequest);
        sv1_search_product_worker = findViewById(R.id.SV1_SearchProductWorker);

        sv1_search_product_worker.setOnQueryTextListener(send_product_request_worker.this);

        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        url = "http://" + sh.getString("ip", "") + ":5000/view_products_send_request";
        RequestQueue queue = Volley.newRequestQueue(send_product_request_worker.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(send_product_request_worker.this, "err_javapage" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    product_name_arr = new ArrayList<>();
                    product_type_arr = new ArrayList<>();
                    details_arr = new ArrayList<>();
                    image_arr = new ArrayList<>();
                    price_per_day_arr = new ArrayList<>();
                    product_id_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        product_name_arr.add(jo.getString("Product"));
                        product_type_arr.add(jo.getString("Type"));
                        details_arr.add(jo.getString("Details"));
                        image_arr.add(jo.getString("Image"));
                        price_per_day_arr.add(jo.getString("Price"));
                        product_id_arr.add(jo.getString("product_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_send_product_request.setAdapter(new custom_view_product_worker(send_product_request_worker.this,
                            product_name_arr, product_type_arr,details_arr, price_per_day_arr, image_arr, product_id_arr));
//                    l1_send_product_request.setOnItemClickListener(send_product_request.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(send_product_request_worker.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("worker_lid", ""));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {


        url = "http://" + sh.getString("ip", "") + ":5000/search_products_send_request";
        RequestQueue queue = Volley.newRequestQueue(send_product_request_worker.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(send_product_request_worker.this, "err_javapage" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    product_name_arr = new ArrayList<>();
                    product_type_arr = new ArrayList<>();
                    details_arr = new ArrayList<>();
                    image_arr = new ArrayList<>();
                    price_per_day_arr = new ArrayList<>();
                    product_id_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        product_name_arr.add(jo.getString("Product"));
                        product_type_arr.add(jo.getString("Type"));
                        details_arr.add(jo.getString("Details"));
                        image_arr.add(jo.getString("Image"));
                        price_per_day_arr.add(jo.getString("Price"));
                        product_id_arr.add(jo.getString("product_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_send_product_request.setAdapter(new custom_view_product_worker(send_product_request_worker.this,
                            product_name_arr, product_type_arr,details_arr, price_per_day_arr, image_arr, product_id_arr));
//                    l1_send_product_request.setOnItemClickListener(send_product_request.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(send_product_request_worker.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("worker_lid", ""));
                params.put("search_product", s);
                return params;
            }
        };
        queue.add(stringRequest);
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent ik =new Intent(getApplicationContext(), workers_home.class);
        startActivity(ik);
    }
}