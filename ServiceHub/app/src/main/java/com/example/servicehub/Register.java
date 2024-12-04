package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText email, password;
    CheckBox termsCheckBox;
    Button createAccountButton, loginButton;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.et_email_reg);
        password = findViewById(R.id.et_password_reg);
        termsCheckBox = findViewById(R.id.cb_agree_reg);
        createAccountButton = findViewById(R.id.btn_create_reg);
        loginButton = findViewById(R.id.btn_login_reg);

        firebaseAuth = FirebaseAuth.getInstance();

        createAccountButton.setOnClickListener(v -> {
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            boolean termsAccepted = termsCheckBox.isChecked();

            if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!termsAccepted) {
                Toast.makeText(Register.this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            } else if (passwordInput.length() < 8) {
                Toast.makeText(Register.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(emailInput, passwordInput);
            }
        });

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(Register.this, LogIn.class);
            startActivity(intent);
        });
    }

    private void registerUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(Register.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                        // Redirigir al usuario a otra actividad o cerrar el registro
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Register.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
