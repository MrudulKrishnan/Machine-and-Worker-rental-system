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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class send_product_request extends AppCompatActivity {

    SharedPreferences sh;
    ListView l1_send_product_request;
    EditText et1_search_product;
    Button b1_search_product_user;
    String product_id_str, product_type_str;
    String url;
    ArrayList<String> product_name_arr, product_type_arr, details_arr, image_arr, price_per_day_arr,
            product_id_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_product_request);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1_send_product_request = findViewById(R.id.L1_SendProductRequest);
        b1_search_product_user = findViewById(R.id.B1_SearchProductUser);
        et1_search_product = findViewById(R.id.ET1_SearchProductUser);
        b1_search_product_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_type_str=et1_search_product.getText().toString();


                url = "http://" + sh.getString("ip", "") + ":5000/search_product_user";
                RequestQueue queue = Volley.newRequestQueue(send_product_request.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            Toast.makeText(send_product_request.this, "err" + response, Toast.LENGTH_SHORT).show();
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

                            l1_send_product_request.setAdapter(new custom_view_product(send_product_request.this,
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

                        Toast.makeText(send_product_request.this, "err" + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("search_product",product_type_str );
                        return params;
                    }
                };
                queue.add(stringRequest);



            }
        });

        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        url = "http://" + sh.getString("ip", "") + ":5000/view_products_send_request";
        RequestQueue queue = Volley.newRequestQueue(send_product_request.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(send_product_request.this, "err" + response, Toast.LENGTH_SHORT).show();
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

                    l1_send_product_request.setAdapter(new custom_view_product(send_product_request.this,
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

                Toast.makeText(send_product_request.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("user_lid", ""));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent ik =new Intent(getApplicationContext(), user_home.class);
        startActivity(ik);
    }

}