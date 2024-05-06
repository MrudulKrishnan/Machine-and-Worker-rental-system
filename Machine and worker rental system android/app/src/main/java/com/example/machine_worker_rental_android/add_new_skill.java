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

public class add_new_skill extends AppCompatActivity {
    EditText ed1_skill, ed2_type, ed3_exp;
    Button b1_add_skill;
    String skill_str, type_str, exp_str,title,url;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_skill);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed1_skill = findViewById(R.id.ET1_Skill);
        ed2_type = findViewById(R.id.ET2_Type);
        ed3_exp = findViewById(R.id.ET3_Exp);
        b1_add_skill = findViewById(R.id.B1_AddSkill);

        b1_add_skill.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                skill_str = ed1_skill.getText().toString();
                type_str = ed2_type.getText().toString();
                exp_str = ed3_exp.getText().toString();
                if (skill_str.equalsIgnoreCase("")) {
                    ed1_skill.setError("Enter your product name");
                } else if (type_str.equalsIgnoreCase("")) {
                    ed2_type.setError("Enter your product type");
                } else if (exp_str.equalsIgnoreCase("")) {
                    ed3_exp.setError("Enter product_details");
                }
                else {
                    uploadBitmap(title);
                }
            }
        });
    }
    ProgressDialog pd;
    private void uploadBitmap(final String title)
    {
//        Toast.makeText(getApplicationContext(), "IIIIIIIIIIIIIIIIIIIII", Toast.LENGTH_LONG).show();
        RequestQueue queue = Volley.newRequestQueue(add_new_skill.this);
        url = "http://" + sh.getString("ip","") + ":5000/add_new_skill";
        pd=new ProgressDialog(add_new_skill.this);
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

                                Toast.makeText(add_new_skill.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),manage_skill.class);
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

                params.put("Skill",skill_str);
                params.put("Type",type_str);
                params.put("Exp",exp_str);
                params.put("lid",sh.getString("worker_lid",""));

                return params;
            }
        };
        Volley.newRequestQueue(add_new_skill.this).add(volleyMultipartRequest);
    }

    @Override
    public void onBackPressed() {
        Intent ik =new Intent(getApplicationContext(), manage_skill.class);
        startActivity(ik);
    }
}