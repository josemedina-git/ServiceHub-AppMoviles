package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MakeAgenda extends AppCompatActivity {

    Button viewProfileButton;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_agenda);

        viewProfileButton = findViewById(R.id.btn_viewProfile);
        back = findViewById(R.id.backButton_ma);

        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakeAgenda.this, ProProfessional.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakeAgenda.this, ProfileService.class);
                startActivity(intent);
            }
        });
    }
}