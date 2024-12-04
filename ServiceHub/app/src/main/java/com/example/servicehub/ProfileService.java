package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ProfileService extends AppCompatActivity {

    Button agendar;
    ImageButton profileIButton, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_service);

        agendar = findViewById(R.id.btn_schedule);
        profileIButton = findViewById(R.id.ibtn_profile);
        back = findViewById(R.id.backArrow_ps);

        agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileService.this, MakeAgenda.class);
                startActivity(intent);
            }
        });

        profileIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileService.this, ProProfessional.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileService.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}