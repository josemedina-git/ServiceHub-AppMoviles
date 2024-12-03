package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Suscription extends AppCompatActivity {

    ImageButton back;
    Button lestGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscription);

        back = findViewById(R.id.ibtn_back_sus);
        lestGo = findViewById(R.id.btn_lets_go);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Suscription.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lestGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Suscription.this, Payment.class);
                startActivity(intent);
            }
        });

    }
}