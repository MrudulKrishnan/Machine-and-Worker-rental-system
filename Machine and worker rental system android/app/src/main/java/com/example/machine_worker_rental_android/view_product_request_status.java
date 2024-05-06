package com.example.machine_worker_rental_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class view_product_request_status extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView l1_request;
    SharedPreferences sh;
    String url, user_request_id_str;
    ArrayList<String> worker_name_arr, worker_phone_arr, date_arr, status_arr, work_details_arr,
            worker_request_id_arr, latitude_arr, longitude_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_request_status);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        b1_add_new_skill = findViewById(R.id.B1_AddNewSkill);
        l1_request = findViewById(R.id.L1_WorkerProductRequestStatus);


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

        url = "http://" + sh.getString("ip", "") + ":5000/view_product_response";
        RequestQueue queue = Volley.newRequestQueue(view_product_request_status.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(view_product_request_status.this, "" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    worker_name_arr = new ArrayList<>();
                    worker_phone_arr = new ArrayList<>();
                    date_arr = new ArrayList<>();
                    status_arr = new ArrayList<>();
                    work_details_arr = new ArrayList<>();
                    worker_request_id_arr = new ArrayList<>();
                    latitude_arr = new ArrayList<>();
                    longitude_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        worker_name_arr.add(jo.getString("Firstname"));
                        worker_phone_arr.add(jo.getString("Phone"));
                        date_arr.add(jo.getString("Date"));
                        status_arr.add(jo.getString("Status"));
                        worker_request_id_arr.add(jo.getString("productRequest_id"));
                        latitude_arr.add(jo.getString("latitude"));
                        longitude_arr.add(jo.getString("longitude"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_request.setAdapter(new custom_view_product_request_status(view_product_request_status.this,
                            worker_name_arr, worker_phone_arr, date_arr, status_arr, worker_request_id_arr));
                    l1_request.setOnItemClickListener(view_product_request_status.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_product_request_status.this, "err" + error, Toast.LENGTH_SHORT).show();
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            user_request_id_str=worker_request_id_arr.get(i);
        if(status_arr.get(i).equalsIgnoreCase("pending")) {
            Toast.makeText(view_product_request_status.this, "waiting for shop response", Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(view_product_request_status.this, " shop response",Toast.LENGTH_SHORT).show();

            AlertDialog.Builder ald = new AlertDialog.Builder(view_product_request_status.this);
            ald.setTitle("title")
                    .setPositiveButton(" Track ", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
//                        RequestQueue queue = Volley.newRequestQueue(view_assigned_work_boy.this);
//                        url = "http://"+sh.getString("ip","")+":5000/accept_delivery";
//                        Toast.makeText(view_assigned_work_boy.this,url , Toast.LENGTH_SHORT).show();
//
//                        // Request a string response from the provided URL.
//                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
//                        {
//                            @Override
//                            public void onResponse(String response)
//                            {
//                                // Display the response string.
//                                Log.d("+++++++++++++++++", response);
//                                try
//                                {
//                                    JSONObject json = new JSONObject(response);
//                                    String res = json.getString("task");
//
//                                    if (res.equalsIgnoreCase("success"))
//                                    {
////                                        String lid = json.getString("id");     // getting login id
////                                        SharedPreferences.Editor edp = sh.edit();
////                                        edp.putString("lid", lid);
////                                        edp.commit();
//                                        Intent ik = new Intent(getApplicationContext(), delivery_boy_home.class);
//                                        startActivity(ik);
//                                    }
//                                    else
//                                    {
//                                        Toast.makeText(view_assigned_work_boy.this, "success", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                catch (JSONException e)
//                                {
//                                    e.printStackTrace();
//                                }
//                            }
//                        },new Response.ErrorListener()
//                        {
//                            @Override
//                            public void onErrorResponse(VolleyError error)
//                            {
//                                Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
//                            }
//                        }) {
//                            @Override
//                            protected Map<String, String> getParams()
//                            {
//                                Map<String, String> params = new HashMap<String, String>();
//                                params.put("assign_id",assign_id_arr.get(i));
////                                params.put("lid",sh.getString("lid",""));
////                                params.put("uname", username);
////                                params.put("pass", password);
//
//                                return params;
//                            }
//                        };
////                        i.putExtra("imgid", fid.get(pos));
////                        startActivity(i);
//                        queue.add(stringRequest);
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://maps.google.com?q=" + latitude_arr.get(i) + "," + longitude_arr.get(i)));
                            startActivity(intent);
                        }

                    })
                    .setNegativeButton(" cencel ", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }

                    });
            AlertDialog al = ald.create();
            al.show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent ik =new Intent(getApplicationContext(), user_home.class);
        startActivity(ik);
    }


}