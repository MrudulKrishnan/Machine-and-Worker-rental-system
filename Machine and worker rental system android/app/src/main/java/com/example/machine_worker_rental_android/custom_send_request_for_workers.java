package com.example.machine_worker_rental_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class custom_send_request_for_workers extends BaseAdapter {
    private Context context;
    ArrayList<String>  worker_name_arr, worker_phone_arr, worker_field_arr, worker_experience_arr, worker_details_arr
            , requested_worker_id_arr;

    SharedPreferences sh;
    Button b1_worker_request;
    String url;
    public custom_send_request_for_workers(Context applicationContext, ArrayList<String> worker_name_arg,
                               ArrayList<String> worker_phone_arg, ArrayList<String> worker_field_arg, ArrayList<String> worker_experience_arg,
                               ArrayList<String> worker_details_arg, ArrayList<String> requested_worker_id_arg)
    {
        // TODO Auto-generated constructor stub
        this.context = applicationContext;
        this.worker_name_arr = worker_name_arg;
        this.worker_phone_arr = worker_phone_arg;
        this.worker_field_arr = worker_field_arg;
        this.worker_experience_arr = worker_experience_arg;
        this.worker_details_arr = worker_details_arg;
        this.requested_worker_id_arr = requested_worker_id_arg;

        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return worker_name_arr.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (convertview == null) {
            gridView = new View(context);
            gridView = inflator.inflate(R.layout.activity_custom_send_request_for_workers, null);

        } else {
            gridView = (View) convertview;

        }
        ///////////////////////
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /////////////////////////////////

        TextView tv1_worker_name = (TextView) gridView.findViewById(R.id.TV1_WorkerName);
        TextView tv2_worker_phone = (TextView) gridView.findViewById(R.id.TV3_RequestedProductStatus);
        TextView tv3_worker_experience = (TextView) gridView.findViewById(R.id.TV3_WorkerExperience);
        TextView tv4_worker_details = (TextView) gridView.findViewById(R.id.TV4_WorkerPlace);
        TextView tv5_worker_field = (TextView) gridView.findViewById(R.id.TV5_WorkerField);
        Button b1_worker_request = gridView.findViewById(R.id.B1_WorkerRequest);

        b1_worker_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(context, request_send_for_worker.class);
                ik.putExtra("wid",requested_worker_id_arr.get(position));
                context.startActivity(ik);
//                RequestQueue queue = Volley.newRequestQueue(context);
//                url = "http://" + sh.getString("ip", "") + ":5000/send_request_for_workers";
//                Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the response string.
//                        Log.d("+++++++++++++++++", response);
//                        try {
//                            JSONObject json = new JSONObject(response);
//                            String res = json.getString("task");
//
//                            if (res.equalsIgnoreCase("success")) {
////                                String lid = json.getString("id");     // getting login id
////                                SharedPreferences.Editor edp = sh.edit();
////                                edp.putString("lid", lid);
////                                edp.commit();
//                                Intent ik = new Intent(context, request_send_for_worker.class);
//                                ik.putExtra("wid",requested_worker_id_arr.get(position));
//                                context.startActivity(ik);
//                            } else {
//                                Toast.makeText(context, "Invalid request", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(context, "Error" + error, Toast.LENGTH_LONG).show();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
////                        params.put("w_lid", sh.getString("worker_lid", ""));
//                        params.put("user_lid", sh.getString("user_lid", ""));
//                        params.put("worker_lid", requested_worker_id_arr.get(position));
//                        return params;
//                    }
//                };
//                queue.add(stringRequest);

            }

        });

        tv1_worker_name.setText(worker_name_arr.get(position));
        tv2_worker_phone.setText(worker_phone_arr.get(position));
        tv3_worker_experience.setText(worker_experience_arr.get(position));
        tv4_worker_details.setText(worker_details_arr.get(position));
        tv5_worker_field.setText(worker_field_arr.get(position));

        tv1_worker_name.setTextColor(Color.BLACK);
        tv2_worker_phone.setTextColor(Color.BLACK);
        tv3_worker_experience.setTextColor(Color.BLACK);
        tv4_worker_details.setTextColor(Color.BLACK);
        tv5_worker_field.setTextColor(Color.BLACK);

        return gridView;


    }
}
