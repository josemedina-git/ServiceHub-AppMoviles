package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Agendas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AgendaAdapter agendaAdapter;

    ImageButton backMain;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendas);

        backMain = findViewById(R.id.backArrow_ag);
        editButton = findViewById(R.id.btn_edit_ag);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Agendas.this));

        // Lista de datos simulados
        List<Agenda> agendaList = new ArrayList<>();
        agendaList.add(new Agenda("Emily Thompson", "Emily Thompson", "12/12/24"));
        agendaList.add(new Agenda("Michael Johnson", "Michael Johnson", "12/12/24"));
        agendaList.add(new Agenda("Sarah Carter", "Sarah Carter", "12/12/24"));
        agendaList.add(new Agenda("David Miller", "David Miller", "12/12/24"));
        agendaList.add(new Agenda("Jessica Roberts", "Jessica Roberts", "12/12/24"));
        agendaList.add(new Agenda("Daniel Wilson", "Daniel Wilson", "12/12/24"));
        agendaList.add(new Agenda("Ashley Turner", "Ashley Turner", "12/12/24"));
        agendaList.add(new Agenda("James Baker", "James Baker", "12/12/24"));

        // Configuración del adaptador
        agendaAdapter = new AgendaAdapter(agendaList);
        recyclerView.setAdapter(agendaAdapter);

        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendas.this, MainActivity.class);
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendas.this, MakeAgenda.class);
                startActivity(intent);
            }
        });
    }
}