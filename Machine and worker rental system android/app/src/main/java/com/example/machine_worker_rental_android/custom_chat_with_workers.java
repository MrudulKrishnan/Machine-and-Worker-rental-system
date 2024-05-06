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

class custom_chat_with_workers extends BaseAdapter {
    private Context context;
    ArrayList<String> name_arr, rid_arr;

    Button chat_user;
    SharedPreferences sh;
    public custom_chat_with_workers(Context applicationContext, ArrayList<String> name_arg,
                                    ArrayList<String> worker_id_arg)
    {
        // TODO Auto-generated constructor stub
        this.context = applicationContext;
        this.name_arr = name_arg;
        this.rid_arr = worker_id_arg;
        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name_arr.size();
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
            gridView=inflator.inflate(R.layout.activity_custom_chat_with_user,null);

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

        TextView tv1_name = (TextView)gridView.findViewById(R.id.TV1_RequestedProductName);
        TextView tv2_phone = (TextView)gridView.findViewById(R.id.TV2_ChatWorkerPhone);
        TextView tv3_field = (TextView)gridView.findViewById(R.id.TV3_ChatWorkerField);

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

        tv1_name.setText(name_arr.get(position));


        tv1_name.setTextColor(Color.BLACK);
        tv2_phone.setTextColor(Color.BLACK);
        tv3_field.setTextColor(Color.BLACK);

        return gridView;

    }

}
