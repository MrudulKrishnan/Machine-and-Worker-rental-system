package com.example.machine_worker_rental_android;

import android.content.Context;
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

class custom_manage_skill extends BaseAdapter {
    private Context context;
    ArrayList<String> skill_arr, type_arr, date_arr, exp_arr;

    Button send_request;
    SharedPreferences sh;
    public custom_manage_skill(Context applicationContext, ArrayList<String> skill_arg,
                          ArrayList<String> type_arg, ArrayList<String> date_arg,
                          ArrayList<String> exp_arg)
    {
        // TODO Auto-generated constructor stub
        this.context = applicationContext;
        this.skill_arr = skill_arg;
        this.type_arr = type_arg;
        this.date_arr = date_arg;
        this.exp_arr = exp_arg;
        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return skill_arr.size();
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
            gridView=inflator.inflate(R.layout.activity_custom_manage_skill,null);

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

        TextView tv1_skill = (TextView)gridView.findViewById(R.id.TV1_RequestedProductName);
        TextView tv2_type = (TextView)gridView.findViewById(R.id.TV3_RequestedProductStatus);
        TextView tv3_date = (TextView)gridView.findViewById(R.id.TV4_RequestedProductReturnDate);
        TextView tv4_exp = (TextView)gridView.findViewById(R.id.TV5_ViewWorkerResponseDetails);

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

        tv1_skill.setText(skill_arr.get(position));
        tv2_type.setText(type_arr.get(position));
        tv3_date.setText(date_arr.get(position));
        tv4_exp.setText(exp_arr.get(position));

        tv1_skill.setTextColor(Color.BLACK);
        tv2_type.setTextColor(Color.BLACK);
        tv3_date.setTextColor(Color.BLACK);
        tv4_exp.setTextColor(Color.BLACK);

        return gridView;

    }

}
