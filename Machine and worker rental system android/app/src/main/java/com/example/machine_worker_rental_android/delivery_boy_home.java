package com.example.machine_worker_rental_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class delivery_boy_home extends AppCompatActivity {
    Button b1_view_assigned_work, b3_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_boy_home);

        b1_view_assigned_work = findViewById(R.id.B1_ViewAssignedWork);
        b3_logout = findViewById(R.id.B3_LogoutBoy);


        b1_view_assigned_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), view_assigned_work_boy.class);
                startActivity(ik);

            }
        });


        b3_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent logout = new Intent(getApplicationContext(), login.class);
                startActivity(logout);


            }
        });
    }
}