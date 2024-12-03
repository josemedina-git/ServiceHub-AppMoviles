package com.example.guayabi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText fullName = findViewById(R.id.editTextText);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText password = findViewById(R.id.editTextTextPassword);
        CheckBox termsCheckBox = findViewById(R.id.checkBox);
        Button createAccountButton = findViewById(R.id.button3);

        createAccountButton.setOnClickListener(v -> {
            String name = fullName.getText().toString().trim();
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            boolean termsAccepted = termsCheckBox.isChecked();

            if (name.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(registerActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!termsAccepted) {
                Toast.makeText(registerActivity.this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            } else if (passwordInput.length() < 8) {
                Toast.makeText(registerActivity.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            } else {
                // Aquí puedes manejar el registro, como guardar datos en una base de datos.
                Toast.makeText(registerActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                finish(); // Cierra esta actividad y regresa a la MainActivity
            }
        });
    }
}
