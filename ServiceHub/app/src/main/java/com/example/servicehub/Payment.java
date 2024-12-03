package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    ImageButton back;
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        back = findViewById(R.id.ibtn_back_pay);
        pay = findViewById(R.id.btn_pay_now);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this, Suscription.class);
                startActivity(intent);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this, MainActivity.class);
                Toast.makeText(Payment.this, "Thanks for you pay. You are now part of the family", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}