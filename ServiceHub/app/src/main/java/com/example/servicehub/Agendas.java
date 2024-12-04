package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Agendas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AgendaAdapter agendaAdapter;
    private List<Agenda> agendaList;
    private Button newButton;
    private ImageButton back;

    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendas);

        // Inicializar Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        newButton = findViewById(R.id.btn_edit_ag);
        back = findViewById(R.id.backArrow_ag);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        agendaList = new ArrayList<>();

        // Inicializar el adaptador y configurarlo con el listener para eliminar
        agendaAdapter = new AgendaAdapter(agendaList, position -> {
            deleteAgendaFromFirestore(position);
        });

        recyclerView.setAdapter(agendaAdapter);

        // Cargar datos desde Firestore
        loadAgendasFromFirestore();

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendas.this, MakeAgenda.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendas.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadAgendasFromFirestore() {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = user.getUid();

        db.collection("agenda")
                .document(userId)
                .collection("user_agenda")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        agendaList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String professionalName = document.getString("professionalName");
                            String date = document.getString("date");
                            String note = document.getString("note");

                            Agenda agenda = new Agenda(professionalName, note, date);
                            agendaList.add(agenda);
                        }
                        agendaAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(Agendas.this, "Failed to load agendas", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deleteAgendaFromFirestore(int position) {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = user.getUid();
        Agenda agendaToDelete = agendaList.get(position);

        // Aquí necesitamos eliminar el servicio de la base de datos
        db.collection("agenda")
                .document(userId)
                .collection("user_agenda")
                .whereEqualTo("professionalName", agendaToDelete.getAgendaName()) // Suponiendo que el nombre del profesional es único
                .whereEqualTo("date", agendaToDelete.getDate())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Si encontramos el documento, lo eliminamos
                        String docId = task.getResult().getDocuments().get(0).getId();
                        db.collection("agenda")
                                .document(userId)
                                .collection("user_agenda")
                                .document(docId)
                                .delete()
                                .addOnSuccessListener(aVoid -> {
                                    // Actualizar la lista de la interfaz
                                    agendaList.remove(position);
                                    agendaAdapter.notifyItemRemoved(position);
                                    Toast.makeText(Agendas.this, "Service deleted", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> Toast.makeText(Agendas.this, "Failed to delete", Toast.LENGTH_SHORT).show());
                    }
                });
    }
}