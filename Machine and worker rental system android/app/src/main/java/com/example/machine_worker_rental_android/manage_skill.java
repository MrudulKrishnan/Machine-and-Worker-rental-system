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
import android.widget.Button;
import android.widget.EditText;
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

public class manage_skill extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {
    Button b1_add_new_skill, b2_search_skill;
    EditText et1_search_skill;
    ListView l1_skill;
    SharedPreferences sh;
    String url, skill_id_str,search_view_str;
    ArrayList<String> skill_arr, type_arr, date_arr, exp_arr, skill_id_arr;
    SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_skill);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1_add_new_skill = findViewById(R.id.B1_AddNewSkill);

        l1_skill = findViewById(R.id.L1_Skill);
        sv=findViewById(R.id.SV);
        sv.setOnQueryTextListener(manage_skill.this);

//        b2_search_skill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                url = "http://" + sh.getString("ip", "") + ":5000/manage_skill";
//                RequestQueue queue = Volley.newRequestQueue(manage_skill.this);
//
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the response string.
//                        Log.d("+++++++++++++++++", response);
//                        try {
//                            Toast.makeText(manage_skill.this, "err" + response, Toast.LENGTH_SHORT).show();
//                            JSONArray ar = new JSONArray(response);
//
//                            skill_arr = new ArrayList<>();
//                            type_arr = new ArrayList<>();
//                            date_arr = new ArrayList<>();
//                            exp_arr = new ArrayList<>();
//                            skill_id_arr = new ArrayList<>();
//
//
//                            for (int i=0; i<ar.length(); i++) {
//                                JSONObject jo = ar.getJSONObject(i);
//                                skill_arr.add(jo.getString("Skill"));
//                                type_arr.add(jo.getString("Type"));
//                                date_arr.add(jo.getString("Date"));
//                                exp_arr.add(jo.getString("Exp"));
//                                skill_id_arr.add(jo.getString("skill_id"));
//
//                            }
//
//                            // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
//                            //lv.setAdapter(ad);
//
//                            l1_skill.setAdapter(new custom_manage_skill(manage_skill.this, skill_arr, type_arr, date_arr, exp_arr));
//                            l1_skill.setOnItemClickListener(manage_skill.this);
//
//                        } catch (Exception e) {
//                            Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
//                            Log.d("=========", e.toString());
//                        }
//
//
//                    }
//
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Toast.makeText(manage_skill.this, "err" + error, Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//                    @NonNull
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<>();
//                        params.put("lid", sh.getString("lid", ""));
//                        return params;
//                    }
//                };
//                queue.add(stringRequest);
//
//            }
//        });


        b1_add_new_skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(getApplicationContext(), add_new_skill.class);
                startActivity(i2);

            }
        });
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        url = "http://" + sh.getString("ip", "") + ":5000/manage_skill";
        RequestQueue queue = Volley.newRequestQueue(manage_skill.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
//                    Toast.makeText(manage_skill.this, "err" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    skill_arr = new ArrayList<>();
                    type_arr = new ArrayList<>();
                    date_arr = new ArrayList<>();
                    exp_arr = new ArrayList<>();
                    skill_id_arr = new ArrayList<>();


                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        skill_arr.add(jo.getString("Skill"));
                        type_arr.add(jo.getString("Type"));
                        date_arr.add(jo.getString("Date"));
                        exp_arr.add(jo.getString("Exp"));
                        skill_id_arr.add(jo.getString("skill_id"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_skill.setAdapter(new custom_manage_skill(manage_skill.this, skill_arr, type_arr, date_arr, exp_arr));
                    l1_skill.setOnItemClickListener(manage_skill.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(manage_skill.this, "err" + error, Toast.LENGTH_SHORT).show();
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

        skill_id_str=skill_id_arr.get(i);

        AlertDialog.Builder ald=new AlertDialog.Builder(manage_skill.this);
        ald.setTitle("title")
//                .setPositiveButton(" Edit ", new DialogInterface.OnClickListener()
//                {
//
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        try
//                        {
//                            Intent i=new Intent(getApplicationContext(),edit_product.class);
//                            i.putExtra("p_id",product_id_str);
//                            startActivity(i);
//
////                            SharedPreferences.Editor ed=sh.edit();
////                            ed.putString("orginal",fname.get(pos));
////                            ed.commit();
////
////                            startDownload(fname.get(pos));
//
//                        }
//                        catch(Exception e)
//                        {
//                            Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                })
                .setNegativeButton(" Delete ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
//                        Toast.makeText(manage_skill.this, "inside$$$$", Toast.LENGTH_SHORT).show();

                        RequestQueue queue = Volley.newRequestQueue(manage_skill.this);
                        url = "http://"+sh.getString("ip","")+":5000/delete_skill";
                        Toast.makeText(manage_skill.this,url , Toast.LENGTH_SHORT).show();

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
                                        Intent ik = new Intent(getApplicationContext(), manage_skill.class);
                                        startActivity(ik);
                                    }
                                    else
                                    {
                                        Toast.makeText(manage_skill.this, "success", Toast.LENGTH_SHORT).show();
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
                                params.put("skill_id",skill_id_arr.get(i));
//                                params.put("lid",sh.getString("lid",""));
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
        url = "http://" + sh.getString("ip", "") + ":5000/search_skill";
        RequestQueue queue = Volley.newRequestQueue(manage_skill.this);

        search_view_str = sv.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
//                    Toast.makeText(manage_skill.this, "err" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    skill_arr = new ArrayList<>();
                    type_arr = new ArrayList<>();
                    date_arr = new ArrayList<>();
                    exp_arr = new ArrayList<>();
                    skill_id_arr = new ArrayList<>();


                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        skill_arr.add(jo.getString("Skill"));
                        type_arr.add(jo.getString("Type"));
                        date_arr.add(jo.getString("Date"));
                        exp_arr.add(jo.getString("Exp"));
                        skill_id_arr.add(jo.getString("skill_id"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_skill.setAdapter(new custom_manage_skill(manage_skill.this, skill_arr, type_arr, date_arr, exp_arr));
//                    l1_skill.setOnItemClickListener(manage_skill.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "=========" + e, Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(manage_skill.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("worker_lid", ""));
                params.put("product_type", s);
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