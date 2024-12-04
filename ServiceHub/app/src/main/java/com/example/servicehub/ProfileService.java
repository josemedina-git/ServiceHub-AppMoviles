package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileService extends AppCompatActivity {

    Button agendar, favoriteButton;
    ImageButton profileIButton, back, favoriteIButton;

    TextView userName, userService, userTitle, followers, aboutDescription;
    RatingBar ratingBar;

    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_service);

        agendar = findViewById(R.id.btn_schedule);
        profileIButton = findViewById(R.id.ibtn_profile);
        back = findViewById(R.id.backArrow_ps);
        favoriteButton = findViewById(R.id.addFavoritesButton);
        favoriteIButton = findViewById(R.id.favoriteButton);
        userName = findViewById(R.id.userName);
        userService = findViewById(R.id.userService);
        userTitle = findViewById(R.id.userTitle);
        followers = findViewById(R.id.followers);
        aboutDescription = findViewById(R.id.aboutDescription);
        ratingBar = findViewById(R.id.ratingBar);

        Intent intent = getIntent();
        String serviceName = intent.getStringExtra("serviceName");
        String workerName = intent.getStringExtra("workerName");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String followersCount = intent.getStringExtra("followers");
        String rating = intent.getStringExtra("rating");

        // Asignar valores a las vistas
        userName.setText(workerName);
        userService.setText(serviceName);
        userTitle.setText(title);
        followers.setText(String.format("%s followers", followersCount));
        aboutDescription.setText(description);

        // Convertir calificación de String a float para la barra de calificación
        if (rating != null && !rating.isEmpty()) {
            ratingBar.setRating(Float.parseFloat(rating));
        } else {
            ratingBar.setRating(0); // Valor por defecto
        }

        // Botones de acción
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileService.this, MainActivity.class);
                startActivity(intent);
            }
        });

        favoriteButton.setOnClickListener(v -> {
            // Alternar el estado del favorito
            isFavorite = !isFavorite;

            // Cambiar el icono según el estado
            if (isFavorite) {
                favoriteIButton.setImageResource(R.drawable.ic_star_filled); // Icono cuando está marcado
            } else {
                favoriteIButton.setImageResource(R.drawable.ic_star_empty); // Icono cuando está desmarcado
            }
        });

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
    }
}