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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class send_request_for_workers extends AppCompatActivity {

    SharedPreferences sh;
    ListView l1_send_request_for_worker;
    EditText et1_search_worker;
    String url, search_worker_str;
    Button b1_search_workers;
    ArrayList<String>  worker_name_arr, worker_phone_arr, worker_field_arr, worker_experience_arr, worker_details_arr, worker_request_id_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request_for_workers);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1_send_request_for_worker = findViewById(R.id.L1_sendRequestForWorker);
        b1_search_workers = findViewById(R.id.B1_SearchWorker);
        et1_search_worker = findViewById(R.id.ET1_SearchWorker);

        b1_search_workers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(send_request_for_workers.this, "complaint toast ", Toast.LENGTH_SHORT).show();

                search_worker_str = et1_search_worker.getText().toString();

                if (search_worker_str.equalsIgnoreCase("")) {
                    et1_search_worker.setError("Enter Your complaint");
                }
                else {


                    ///////////////////////////////////////////////

                    search_worker_str=et1_search_worker.getText().toString();



                    url = "http://" + sh.getString("ip", "") + ":5000/search_worker";
                    RequestQueue queue = Volley.newRequestQueue(send_request_for_workers.this);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                Toast.makeText(send_request_for_workers.this, "err" + response, Toast.LENGTH_SHORT).show();
                                JSONArray ar = new JSONArray(response);

                                worker_name_arr = new ArrayList<>();
                                worker_phone_arr = new ArrayList<>();
                                worker_experience_arr = new ArrayList<>();
                                worker_details_arr = new ArrayList<>();
                                worker_field_arr = new ArrayList<>();
                                worker_request_id_arr = new ArrayList<>();

                                for (int i=0; i<ar.length(); i++) {
                                    JSONObject jo = ar.getJSONObject(i);
                                    worker_name_arr.add(jo.getString("worker_firstname")+ " " + jo.getString("worker_lastname"));
                                    worker_phone_arr.add(jo.getString("worker_phone"));
                                    worker_field_arr.add(jo.getString("worker_field"));
                                    worker_experience_arr.add(jo.getString("worker_experience"));
                                    worker_details_arr.add(jo.getString("worker_place")+ ", "
                                            + jo.getString("worker_post")+ ", "+ jo.getString("worker_pin"));
                                    worker_request_id_arr.add(jo.getString("requested_worker_id"));
                                }

                                // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                                //lv.setAdapter(ad);

                                l1_send_request_for_worker.setAdapter(new custom_send_request_for_workers(send_request_for_workers.this,
                                        worker_name_arr, worker_phone_arr, worker_field_arr, worker_experience_arr, worker_details_arr, worker_request_id_arr));
//                    l1_s.setOnItemClickListener(send_product_request.this);

                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                                Log.d("=========", e.toString());
                            }


                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(send_request_for_workers.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @NonNull
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("search_worker",search_worker_str );
                            return params;
                        }
                    };
                    queue.add(stringRequest);


                   ///////////////////////////////////////

                }
                }

        });

        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        url = "http://" + sh.getString("ip", "") + ":5000/send_request_for_worker_view";
        RequestQueue queue = Volley.newRequestQueue(send_request_for_workers.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(send_request_for_workers.this, "err viewww" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    worker_name_arr = new ArrayList<>();
                    worker_phone_arr = new ArrayList<>();
                    worker_experience_arr = new ArrayList<>();
                    worker_details_arr = new ArrayList<>();
                    worker_field_arr = new ArrayList<>();
                    worker_request_id_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        worker_name_arr.add(jo.getString("worker_firstname")+ " " + jo.getString("worker_lastname"));
                        worker_phone_arr.add(jo.getString("worker_phone"));
                        worker_field_arr.add(jo.getString("worker_field"));
                        worker_experience_arr.add(jo.getString("worker_experience"));
                        worker_details_arr.add(jo.getString("worker_place")+ ", "
                                + jo.getString("worker_post")+ ", "+ jo.getString("worker_pin"));
                        worker_request_id_arr.add(jo.getString("requested_worker_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_send_request_for_worker.setAdapter(new custom_send_request_for_workers(send_request_for_workers.this,
                            worker_name_arr, worker_phone_arr, worker_field_arr, worker_experience_arr, worker_details_arr, worker_request_id_arr));
//                    l1_s.setOnItemClickListener(send_product_request.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(send_request_for_workers.this, "err" + error, Toast.LENGTH_SHORT).show();
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