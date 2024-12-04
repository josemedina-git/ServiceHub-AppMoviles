package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MakeAgenda extends AppCompatActivity {

    EditText noteField;
    CalendarView calendarView;
    Button scheduleButton, viewProfileButton;
    ImageButton back;

    String selectedDate = ""; // Almacena la fecha seleccionada
    String professionalName = "Professional name"; // Nombre predeterminado del profesional

    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_agenda);

        // Inicializar Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Vinculación de vistas
        noteField = findViewById(R.id.noteField);
        calendarView = findViewById(R.id.calendarView);
        scheduleButton = findViewById(R.id.btn_scheduleButton);
        viewProfileButton = findViewById(R.id.btn_viewProfile);
        back = findViewById(R.id.backButton_ma);

        // Listener para capturar la fecha seleccionada
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Formatear la fecha seleccionada
            selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
        });

        // Botón para programar el servicio
        scheduleButton.setOnClickListener(v -> {
            String note = noteField.getText().toString().trim();

            if (selectedDate.isEmpty()) {
                Toast.makeText(MakeAgenda.this, "Please select a date", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar la nota (opcional)
            if (note.isEmpty()) {
                note = "No note provided";
            }

            // Guardar en Firestore
            saveToFirestore(professionalName, selectedDate, note);
        });

        // Botón para ver el perfil
        viewProfileButton.setOnClickListener(view -> {
            Intent intent = new Intent(MakeAgenda.this, ProProfessional.class);
            startActivity(intent);
        });

        // Botón de retroceso
        back.setOnClickListener(view -> {
            Intent intent = new Intent(MakeAgenda.this, ProfileService.class);
            startActivity(intent);
        });
    }

    private void saveToFirestore(String professionalName, String date, String note) {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = user.getUid(); // Obtener UID del usuario

        // Crear un objeto con los datos
        Map<String, Object> agendaData = new HashMap<>();
        agendaData.put("professionalName", professionalName);
        agendaData.put("date", date);
        agendaData.put("note", note);

        // Guardar los datos en Firestore en la colección "agenda" bajo el UID del usuario
        db.collection("agenda")
                .document(userId) // Nodo de usuario
                .collection("user_agenda") // Subcolección de servicios agendados
                .add(agendaData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MakeAgenda.this, "Service scheduled successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MakeAgenda.this, Agendas.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MakeAgenda.this, "Failed to schedule service", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
