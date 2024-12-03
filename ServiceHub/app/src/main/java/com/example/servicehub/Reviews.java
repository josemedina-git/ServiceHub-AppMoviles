package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Reviews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private List<Review> itemList;

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        back = findViewById(R.id.backArrow);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Usa un LinearLayoutManager para listas verticales

        // Inicializa los datos
        itemList = new ArrayList<>();
        itemList.add(new Review("Cleotilde González", "Excelente servicio", "17h", R.drawable.user1));
        itemList.add(new Review("Vannevar Bush", "Excelente servicio", "5d", R.drawable.user2));
        itemList.add(new Review("Antonieta de las Nieves", "Excelente servicio", "17w", R.drawable.user3));
        // Agrega más elementos si es necesario

        // Inicializa el Adapter y asignarlo al RecyclerView
        adapter = new ReviewAdapter(itemList);
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reviews.this, ProProfessional.class);
                startActivity(intent);
            }
        });
    }
}