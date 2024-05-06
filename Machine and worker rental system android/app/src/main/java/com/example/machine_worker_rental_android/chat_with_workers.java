package com.example.machine_worker_rental_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class chat_with_workers extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView l1_chat_user;
    SharedPreferences sh;
    ArrayList<String> name, worker_id;
    String url;
    String worker_id_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_workers);
        l1_chat_user=findViewById(R.id.L1_ChatUser);
        l1_chat_user.setOnItemClickListener(chat_with_workers.this);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/view_workers";
        RequestQueue queue = Volley.newRequestQueue(chat_with_workers.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    worker_id= new ArrayList<>();
                    name= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        worker_id.add(jo.getString("worker_id"));
                        name.add(jo.getString("first_name")+ "" + jo.getString("last_name"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(chat_with_workers.this,android.R.layout.simple_list_item_1,name);
                    l1_chat_user.setAdapter(ad);

//                    L1.setAdapter(new custom2(chatwithfriends.this,name));


                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(chat_with_workers.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid",sh.getString("user_lid",""));

                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        worker_id_str=worker_id.get(position);
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("uid", worker_id_str);
        edp.commit();

        Intent i=new Intent(getApplicationContext(), Test.class);
        i.putExtra("toid",worker_id.get(position));
        i.putExtra("name",name.get(position));

        startActivity(i);


    }

    @Override
    public void onBackPressed() {
        Intent ik =new Intent(getApplicationContext(), user_home.class);
        startActivity(ik);
    }

}