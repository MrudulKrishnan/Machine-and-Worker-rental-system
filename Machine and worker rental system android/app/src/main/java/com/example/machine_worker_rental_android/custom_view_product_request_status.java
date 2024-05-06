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

class custom_view_product_request_status extends BaseAdapter {
    private Context context;
    ArrayList<String> worker_name_arr, worker_phone_arr, date_arr, status_arr, work_details_arr, worker_request_id_arr;
    Button send_request;
    SharedPreferences sh;
    public custom_view_product_request_status(Context applicationContext, ArrayList<String> worker_name_arg,
                                       ArrayList<String> worker_phone_arg, ArrayList<String> date_arg,
                                       ArrayList<String> status_arg, ArrayList<String> worker_request_id_arg)
    {
        // TODO Auto-generated constructor stub
        this.context = applicationContext;
        this.worker_name_arr = worker_name_arg;
        this.worker_phone_arr = worker_phone_arg;
        this.date_arr = date_arg;
        this.status_arr = status_arg;

        this.worker_request_id_arr = worker_request_id_arg;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_view_product_request_status,null);

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

        TextView tv1_worker_name = (TextView)gridView.findViewById(R.id.TV1_RequestedProductName);
        TextView tv2_worker_phone = (TextView)gridView.findViewById(R.id.TV2_RequestedProductDate);
        TextView tv3_date = (TextView)gridView.findViewById(R.id.TV3_RequestedProductStatus);
        TextView tv4_status = (TextView)gridView.findViewById(R.id.TV4_RequestedProductReturnDate);
//        Button b1_assign = gridView.findViewById(R.id.B1_Assign);

//        b1_assign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent ik = new Intent(context, assign_product_user.class);
//                ik.putExtra("product_request_id",worker_request_id_arr.get(position));
//                context.startActivity(ik);
//
//            }
//
//        });



//        java.net.URL thumb_u;
//        try {
//
//            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");
//
//            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+product_image_str.get(position));
//            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
//            im_product_image.setImageDrawable(thumb_d);
//        }
//        catch (Exception e)
//        {
//            Log.d("errsssssssssssss",""+e);
//        }

        tv1_worker_name.setText(worker_name_arr.get(position));
        tv2_worker_phone.setText(worker_phone_arr.get(position));
        tv3_date.setText(date_arr.get(position));
        tv4_status.setText(status_arr.get(position));
//        tv5_work_details.setText(work_details_arr.get(position));

        tv1_worker_name.setTextColor(Color.BLACK);
        tv2_worker_phone.setTextColor(Color.BLACK);
        tv3_date.setTextColor(Color.BLACK);
        tv4_status.setTextColor(Color.BLACK);
//        tv5_work_details.setTextColor(Color.BLACK);

        return gridView;

    }

}
