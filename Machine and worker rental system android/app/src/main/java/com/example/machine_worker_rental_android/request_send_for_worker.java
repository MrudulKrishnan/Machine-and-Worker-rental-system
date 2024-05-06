package com.example.machine_worker_rental_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class request_send_for_worker extends AppCompatActivity {
    EditText et1_work_details, et2_work_date;
    Button b1_send_request;
    String work_details_str, work_date_str, url, title, worker_id;
    SharedPreferences sh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_send_for_worker);

        et1_work_details = findViewById(R.id.ET1_WorkDetailsForWorkerRequest);
        et2_work_date = findViewById(R.id.ET2_DateForWorkerRequest);
        b1_send_request = findViewById((R.id.B1_SendWorkerRequest));
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1_send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                work_details_str = et1_work_details.getText().toString();
                work_date_str = et2_work_date.getText().toString();

                if (work_details_str.equalsIgnoreCase("")) {
                    et1_work_details.setError("Enter Your firstName");
                } else if (work_date_str.equalsIgnoreCase("")) {
                    et2_work_date.setError("Enter date");
                } else {
                    uploadBitmap(title);
                }
            }
        });
    }

    ProgressDialog pd;
    private void uploadBitmap(final String title)
    {
        Toast.makeText(getApplicationContext(), "IIIIIIIIIIIIIIIIIIIII", Toast.LENGTH_LONG).show();
        RequestQueue queue = Volley.newRequestQueue(request_send_for_worker.this); pd=new ProgressDialog(request_send_for_worker.this);

        url = "http://" + sh.getString("ip","") + ":5000/add_worker_request";
        pd.setMessage("Uploading....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response1) {
                        pd.dismiss();
                        String x=new String(response1.data);
                        try {
                            JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                            if (obj.getString("task").equalsIgnoreCase("success")) {

                                Toast.makeText(request_send_for_worker.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),user_home.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("work_details", work_details_str);
                params.put("work_date", work_date_str);
                params.put("worker_id", getIntent().getStringExtra("wid"));
                params.put("user_id", sh.getString("user_lid",""));
                return params;
            }

//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                long imagename = System.currentTimeMillis();
//                params.put("proof", new DataPart(PathHolder , filedt ));
//                return params;
//            }
        };
        Volley.newRequestQueue(request_send_for_worker.this).add(volleyMultipartRequest);
    }
}