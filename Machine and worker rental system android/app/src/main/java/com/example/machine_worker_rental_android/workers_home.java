package com.example.machine_worker_rental_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class workers_home extends AppCompatActivity {
    Button b1_manage_skills, b2_view_request_response, b3_chat_with_user, b4_send_request_product,
            b5_worker_view_product_request, b6_send_rating_review, b7_send_complaint_response, b8_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_home);

        b1_manage_skills = findViewById(R.id.B1_ManageSkill);
        b2_view_request_response = findViewById(R.id.B2_ViewRequestUpdate);
        b3_chat_with_user = findViewById(R.id.B3_ChatWithUser);
        b4_send_request_product = findViewById(R.id.B4_SendRequestProduct);
        b5_worker_view_product_request = findViewById(R.id.B5_WorkerViewProductRequestStatus);
        b6_send_rating_review = findViewById(R.id.B6_SendRatingReview);
        b7_send_complaint_response = findViewById(R.id.B7_SendComplaintResponse);
        b8_logout = findViewById(R.id.B8_Logout);


        b1_manage_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(getApplicationContext(), manage_skill.class);
                startActivity(ik);

            }
        });

        b2_view_request_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(getApplicationContext(), view_request_update_response.class);
                startActivity(ik);

            }
        });

        b3_chat_with_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), chat_with_users.class);
                startActivity(ik);


            }
        });

        b4_send_request_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), send_product_request_worker.class);
                startActivity(ik);

            }
        });

        b5_worker_view_product_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), worker_view_product_request_status.class);
                startActivity(ik);

            }
        });

        b6_send_rating_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), send_rating_review.class);
                startActivity(ik);


            }
        });

        b7_send_complaint_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), sent_complaints_view_response.class);
                startActivity(ik);



            }
        });

        b8_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(getApplicationContext(), login.class);
                startActivity(ik);

            }
        });

    }

}