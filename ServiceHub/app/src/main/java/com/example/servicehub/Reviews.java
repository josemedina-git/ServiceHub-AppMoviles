package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Reviews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private List<Review> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

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
    }
}