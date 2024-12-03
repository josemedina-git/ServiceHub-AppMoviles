package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ProProfessional extends AppCompatActivity {

    ImageButton back, reviews, tel, share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_professional);

        back = findViewById(R.id.backArrow_pro);
        reviews = findViewById(R.id.ibtn_reviews);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProProfessional.this, MainActivity.class);
                startActivity(intent);
            }
        });

        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProProfessional.this, Reviews.class);
                startActivity(intent);
            }
        });
    }
}