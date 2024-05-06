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
import android.widget.SearchView;
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

public class view_request_update_response extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener{
    ListView l1_request;
    SharedPreferences sh;
    String url, worker_request_id_str;
    SearchView sv1_search_work_request;
    ArrayList<String> username_arr, date_arr, status_arr, work_details_arr, worker_request_id_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_update_response);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        b1_add_new_skill = findViewById(R.id.B1_AddNewSkill);
        l1_request = findViewById(R.id.L1_Request);
        sv1_search_work_request = findViewById(R.id.SV1_SearchWorkRequest);

        sv1_search_work_request.setOnQueryTextListener(view_request_update_response.this);

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

        url = "http://" + sh.getString("ip", "") + ":5000/view_work_request_response";
        RequestQueue queue = Volley.newRequestQueue(view_request_update_response.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(view_request_update_response.this, "" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    username_arr = new ArrayList<>();
                    date_arr = new ArrayList<>();
                    status_arr = new ArrayList<>();
                    work_details_arr = new ArrayList<>();
                    worker_request_id_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        username_arr.add(jo.getString("Firstname")+ "" + jo.getString("Lastname"));
                        date_arr.add(jo.getString("Date"));
                        status_arr.add(jo.getString("Status"));
                        work_details_arr.add(jo.getString("Work_details"));
                        worker_request_id_arr.add(jo.getString("WorkRequest_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_request.setAdapter(new custom_view_request_update_response(view_request_update_response.this, username_arr, date_arr, status_arr, work_details_arr));
                    l1_request.setOnItemClickListener(view_request_update_response.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_request_update_response.this, "err" + error, Toast.LENGTH_SHORT).show();
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        worker_request_id_str=worker_request_id_arr.get(i);

        AlertDialog.Builder ald=new AlertDialog.Builder(view_request_update_response.this);
        ald.setTitle("title")
                .setPositiveButton(" accept ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RequestQueue queue = Volley.newRequestQueue(view_request_update_response.this);
                        url = "http://"+sh.getString("ip","")+":5000/accept_work_request";
                        Toast.makeText(view_request_update_response.this,url , Toast.LENGTH_SHORT).show();

                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                // Display the response string.
                                Log.d("+++++++++++++++++", response);
                                try
                                {
                                    JSONObject json = new JSONObject(response);
                                    String res = json.getString("task");

                                    if (res.equalsIgnoreCase("success"))
                                    {
//                                        String lid = json.getString("id");     // getting login id
//                                        SharedPreferences.Editor edp = sh.edit();
//                                        edp.putString("lid", lid);
//                                        edp.commit();
                                        Intent ik = new Intent(getApplicationContext(), view_request_update_response.class);
                                        startActivity(ik);
                                    }
                                    else
                                    {
                                        Toast.makeText(view_request_update_response.this, "success", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        },new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("work_request_id",worker_request_id_arr.get(i));
//                                params.put("lid",sh.getString("lid",""));
//                                params.put("uname", username);
//                                params.put("pass", password);

                                return params;
                            }
                        };
//                        i.putExtra("imgid", fid.get(pos));
//                        startActivity(i);
                        queue.add(stringRequest);

                    }
                })
                .setNegativeButton(" reject ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {

                        RequestQueue queue = Volley.newRequestQueue(view_request_update_response.this);
                        url = "http://"+sh.getString("ip","")+":5000/reject_work_request";
                        Toast.makeText(view_request_update_response.this,url , Toast.LENGTH_SHORT).show();

                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                // Display the response string.
                                Log.d("+++++++++++++++++", response);
                                try
                                {
                                    JSONObject json = new JSONObject(response);
                                    String res = json.getString("task");

                                    if (res.equalsIgnoreCase("success"))
                                    {
//                                        String lid = json.getString("id");     // getting login id
//                                        SharedPreferences.Editor edp = sh.edit();
//                                        edp.putString("lid", lid);
//                                        edp.commit();
                                        Intent ik = new Intent(getApplicationContext(), view_request_update_response.class);
                                        startActivity(ik);
                                    }
                                    else
                                    {
                                        Toast.makeText(view_request_update_response.this, "success", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        },new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("work_request_id",worker_request_id_arr.get(i));
//                                params.put("lid",sh.getString("lid",""));
//                                params.put("uname", username);
//                                params.put("pass", password);

                                return params;
                            }
                        };
//                        i.putExtra("imgid", fid.get(pos));
//                        startActivity(i);
                        queue.add(stringRequest);
                    }

                });
        AlertDialog al=ald.create();
        al.show();

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {


        url = "http://" + sh.getString("ip", "") + ":5000/search_work_request_response";
        RequestQueue queue = Volley.newRequestQueue(view_request_update_response.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(view_request_update_response.this, "" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    username_arr = new ArrayList<>();
                    date_arr = new ArrayList<>();
                    status_arr = new ArrayList<>();
                    work_details_arr = new ArrayList<>();
                    worker_request_id_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        username_arr.add(jo.getString("Firstname")+ "" + jo.getString("Lastname"));
                        date_arr.add(jo.getString("Date"));
                        status_arr.add(jo.getString("Status"));
                        work_details_arr.add(jo.getString("Work_details"));
                        worker_request_id_arr.add(jo.getString("WorkRequest_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_request.setAdapter(new custom_view_request_update_response(view_request_update_response.this, username_arr, date_arr, status_arr, work_details_arr));
                    l1_request.setOnItemClickListener(view_request_update_response.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_request_update_response.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("worker_lid", ""));
                params.put("search_date",s);
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