package com.example.machine_worker_rental_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class view_worker_response extends AppCompatActivity{
    ListView l1_request;
    SharedPreferences sh;
    String url, worker_request_id_str;
    ArrayList<String> worker_name_arr, worker_phone_arr, date_arr, status_arr, work_details_arr, worker_request_id_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_worker_response);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        b1_add_new_skill = findViewById(R.id.B1_AddNewSkill);
        l1_request = findViewById(R.id.L1_WorkerResponse);


//        b1_add_new_skill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i2=new Intent(getApplicationContext(), add_new_skill.class);
//                startActivity(i2);
//
//            }
//        });
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        url = "http://" + sh.getString("ip", "") + ":5000/view_worker_response";
        RequestQueue queue = Volley.newRequestQueue(view_worker_response.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(view_worker_response.this, "" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    worker_name_arr = new ArrayList<>();
                    worker_phone_arr = new ArrayList<>();
                    date_arr = new ArrayList<>();
                    status_arr = new ArrayList<>();
                    work_details_arr = new ArrayList<>();
                    worker_request_id_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        worker_name_arr.add(jo.getString("Firstname")+ " " + jo.getString("Lastname"));
                        worker_phone_arr.add(jo.getString("Phone"));
                        date_arr.add(jo.getString("Date"));
                        status_arr.add(jo.getString("Status"));
                        work_details_arr.add(jo.getString("Work_details"));
                        worker_request_id_arr.add(jo.getString("WorkRequest_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_request.setAdapter(new custom_view_worker_response(view_worker_response.this,
                            worker_name_arr, worker_phone_arr, date_arr, status_arr, work_details_arr, worker_request_id_arr));
//                    l1_request.setOnItemClickListener(view_worker_response.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_worker_response.this, "err" + error, Toast.LENGTH_SHORT).show();
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