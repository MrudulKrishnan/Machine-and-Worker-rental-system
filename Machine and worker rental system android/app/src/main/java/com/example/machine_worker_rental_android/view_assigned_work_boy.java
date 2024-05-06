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

public class view_assigned_work_boy extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener{
    SharedPreferences sh;
    String url, assigned_id_str;
    SearchView sv1_search_assigned_work;
    ListView l1_view_assigned_work;
    ArrayList<String> product_arr, product_details_arr, username_arr, user_phone_arr, assign_id_arr,
            latitude_arr, longitude_arr, address_arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assigned_work_boy);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        b1_add_new_skill = findViewById(R.id.B1_AddNewSkill);
        l1_view_assigned_work = findViewById(R.id.List_Assignedwork);
        sv1_search_assigned_work = findViewById(R.id.SV1_SearchAssignedWork);


        sv1_search_assigned_work.setOnQueryTextListener(view_assigned_work_boy.this);


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

        url = "http://" + sh.getString("ip", "") + ":5000/view_assigned_work";
        RequestQueue queue = Volley.newRequestQueue(view_assigned_work_boy.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(view_assigned_work_boy.this, "eeeerr" + response, Toast.LENGTH_SHORT).show();

                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(view_assigned_work_boy.this, "" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    product_arr = new ArrayList<>();
                    product_details_arr = new ArrayList<>();
                    username_arr = new ArrayList<>();
                    user_phone_arr = new ArrayList<>();
                    assign_id_arr = new ArrayList();
                    latitude_arr = new ArrayList<>();
                    longitude_arr = new ArrayList<>();
                    address_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        product_arr.add(jo.getString("Product"));
                        product_details_arr.add(jo.getString("Details"));
                        username_arr.add(jo.getString("Name"));
                        user_phone_arr.add(jo.getString("Phone"));
                        assign_id_arr.add(jo.getString("Assign_id"));
                        latitude_arr.add(jo.getString("lat"));
                        longitude_arr.add(jo.getString("lon"));
                        address_arr.add(jo.getString("address"));

                    }
                    Toast.makeText(view_assigned_work_boy.this, "err" + response, Toast.LENGTH_SHORT).show();


                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_view_assigned_work.setAdapter(new custom_view_assigend_work_boy(view_assigned_work_boy.this,
                            product_arr, product_details_arr, username_arr, user_phone_arr, assign_id_arr, address_arr));
                    l1_view_assigned_work.setOnItemClickListener(view_assigned_work_boy.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_assigned_work_boy.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("boy_lid", ""));
                return params;
            }
        };
        queue.add(stringRequest);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        assigned_id_str=assign_id_arr.get(i);

        AlertDialog.Builder ald=new AlertDialog.Builder(view_assigned_work_boy.this);
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
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://maps.google.com?q="+latitude_arr.get(i)+","+longitude_arr.get(i)));
                        startActivity(intent);
                    }
                })
                .setNegativeButton(" cencel ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
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

        url = "http://" + sh.getString("ip", "") + ":5000/search_assigned_work";
        RequestQueue queue = Volley.newRequestQueue(view_assigned_work_boy.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(view_assigned_work_boy.this, "eeeerr" + response, Toast.LENGTH_SHORT).show();

                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(view_assigned_work_boy.this, "" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    product_arr = new ArrayList<>();
                    product_details_arr = new ArrayList<>();
                    username_arr = new ArrayList<>();
                    user_phone_arr = new ArrayList<>();
                    assign_id_arr = new ArrayList();
                    latitude_arr = new ArrayList<>();
                    longitude_arr = new ArrayList<>();
                    address_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        product_arr.add(jo.getString("Product"));
                        product_details_arr.add(jo.getString("Details"));
                        username_arr.add(jo.getString("Name"));
                        user_phone_arr.add(jo.getString("Phone"));
                        assign_id_arr.add(jo.getString("Assign_id"));
                        latitude_arr.add(jo.getString("lat"));
                        longitude_arr.add(jo.getString("lon"));
                        address_arr.add(jo.getString("address"));

                    }
                    Toast.makeText(view_assigned_work_boy.this, "err" + response, Toast.LENGTH_SHORT).show();


                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_view_assigned_work.setAdapter(new custom_view_assigend_work_boy(view_assigned_work_boy.this,
                            product_arr, product_details_arr, username_arr, user_phone_arr, assign_id_arr, address_arr));
                    l1_view_assigned_work.setOnItemClickListener(view_assigned_work_boy.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_assigned_work_boy.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("boy_lid", ""));
                params.put("search_work",s);
                return params;
            }
        };
        queue.add(stringRequest);



        return true;
    }
//    Intent intent=new Intent(Intent.ACTION_VIEW);
//intent.setData(Uri.parse("http://maps.google.com?q="+lat.get(i)+","+lon.get(i)));
//    startActivity(intent);
}