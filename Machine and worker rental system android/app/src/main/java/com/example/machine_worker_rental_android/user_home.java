package com.example.machine_worker_rental_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_home extends AppCompatActivity {

    Button b1_search_workers, b2_send_request_worker, b3_chat_workers, b4_send_request_product,
            b5_view_worker_request_status, b6_track_order, b7_send_rating_review, b8_send_complaint,
            b9_logout, b10_view_product_request_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

//        b1_search_workers = findViewById(R.id.B1_SearchWorkers);
        b2_send_request_worker = findViewById(R.id.B2_SendRequestWorkers);
        b3_chat_workers = findViewById(R.id.B3_UserChatWithWorkers);
        b4_send_request_product = findViewById(R.id.B4_UserSendRequestForProduct);
        b5_view_worker_request_status = findViewById(R.id.B5_UserViewWorkRequestStatus);
        b7_send_rating_review = findViewById(R.id.B7_UserSendRatingReview);
        b8_send_complaint = findViewById(R.id.B8_UserSendComplaintViewReply);
        b9_logout = findViewById(R.id.B9_UserLogout);
        b10_view_product_request_status = findViewById(R.id.B10_ViewProductRequestStatus);


//        b1_search_workers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
        b2_send_request_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), send_request_for_workers.class);
                startActivity(ik);


            }
        });


        b3_chat_workers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), chat_with_workers.class);
                startActivity(ik);

            }
        });

        b4_send_request_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), send_product_request.class);
                        startActivity(ik);

            }
        });

        b5_view_worker_request_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(getApplicationContext(), view_worker_response.class);
                startActivity(ik);


            }
        });

//        b6_track_order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        b7_send_rating_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), send_rating_review_user.class);
                startActivity(ik);

            }
        });

        b8_send_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), user_sent_complaints_view_response.class);
                startActivity(ik);


            }
        });

        b9_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(getApplicationContext(), login.class);
                startActivity(ik);

            }
        });

        b10_view_product_request_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), view_product_request_status.class);
                startActivity(ik);


            }
        });
    }
}