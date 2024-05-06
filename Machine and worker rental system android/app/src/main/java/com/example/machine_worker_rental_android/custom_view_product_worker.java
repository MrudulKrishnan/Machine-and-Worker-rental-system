package com.example.machine_worker_rental_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class custom_view_product_worker extends BaseAdapter {
    private Context context;
    ArrayList<String> product_name_str;
    ArrayList<String> product_type_str;
    ArrayList<String> product_details_str;
    ArrayList<String> price_per_day_str;
    ArrayList<String> product_image_str;
    ArrayList<String> product_id_str;
    SharedPreferences sh;
    String url;
    public custom_view_product_worker(Context applicationContext, ArrayList<String> product_name_arg,
                               ArrayList<String> product_type_arg, ArrayList<String> product_details_arg,
                               ArrayList<String> price_per_day_arg, ArrayList<String> product_image_arg,
                               ArrayList<String> product_id_arg)
    {
        // TODO Auto-generated constructor stub
        this.context = applicationContext;
        this.product_name_str = product_name_arg;
        this.product_type_str = product_type_arg;
        this.product_details_str = product_details_arg;
        this.price_per_day_str = price_per_day_arg;
        this.product_image_str = product_image_arg;
        this.product_id_str = product_id_arg;

        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return product_name_str.size();
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_view_product_worker,null);

        }
        else
        {
            gridView=(View)convertview;

        }
        ///////////////////////
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /////////////////////////////////

        TextView tv1_product_name = (TextView)gridView.findViewById(R.id.TV1_WorkerName);
        TextView tv2_product_type = (TextView)gridView.findViewById(R.id.TV3_RequestedProductStatus);
        TextView tv3_product_details = (TextView)gridView.findViewById(R.id.TV3_WorkerExperience);
        TextView tv4_product_price = (TextView)gridView.findViewById(R.id.TV4_WorkerPlace);
        ImageView im_product_image = (ImageView) gridView.findViewById(R.id.Product_image);
        Button b1_product_request = gridView.findViewById(R.id.B1_WorkerRequest);

        b1_product_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(context);
                url = "http://"+sh.getString("ip","")+":5000/product_request_send_worker";
                Toast.makeText(context,url , Toast.LENGTH_SHORT).show();
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
//                                String lid = json.getString("id");     // getting login id
//                                SharedPreferences.Editor edp = sh.edit();
//                                edp.putString("lid", lid);
//                                edp.commit();
                                Intent ik = new Intent(context, send_product_request.class);
                                context.startActivity(ik);
                            }
                            else
                            {
                                Toast.makeText(context, "Invalid request", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(context, "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("w_lid", sh.getString("worker_lid", ""));
//                        params.put("u_lid", sh.getString("user_lid", ""));
                        params.put("product_id",product_id_str.get(position));
                        return params;
                    }
                };
                queue.add(stringRequest);

            }
        });

        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+product_image_str.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            im_product_image.setImageDrawable(thumb_d);
        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }

        tv1_product_name.setText(product_name_str.get(position));
        tv2_product_type.setText(product_type_str.get(position));
        tv3_product_details.setText(product_details_str.get(position));
        tv4_product_price.setText(price_per_day_str.get(position));

        tv1_product_name.setTextColor(Color.BLACK);
        tv2_product_type.setTextColor(Color.BLACK);
        tv3_product_details.setTextColor(Color.BLACK);
        tv4_product_price.setTextColor(Color.BLACK);

        return gridView;

    }

}
