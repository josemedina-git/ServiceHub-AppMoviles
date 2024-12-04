package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
        tel = findViewById(R.id.ibtn_tel);
        share = findViewById(R.id.ibtn_share);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProProfessional.this, ProfileService.class);
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

        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "4492768717"));
                startActivity(intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
                intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"joseign03mr@gmail.com"});

                startActivity(intent);
            }
        });
    }
}