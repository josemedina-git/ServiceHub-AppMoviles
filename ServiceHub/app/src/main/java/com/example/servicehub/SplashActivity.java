package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Duración del splash (por ejemplo, 3 segundos)
        int SPLASH_TIME_OUT = 3000;

        // Usar Handler para retrasar el inicio de la LoginActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Iniciar la actividad LoginActivity
                Intent i = new Intent(SplashActivity.this, LogIn.class);
                startActivity(i);
                finish(); // Finaliza SplashActivity para que no vuelva al presionar atrás
            }
        }, SPLASH_TIME_OUT);
    }
}