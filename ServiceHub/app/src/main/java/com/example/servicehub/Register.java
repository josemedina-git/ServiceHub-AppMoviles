package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText fullName, email, password;
    CheckBox termsCheckBox;
    Button createAccountButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.et_name_reg);
        email = findViewById(R.id.et_email_reg);
        password = findViewById(R.id.et_password_reg);
        termsCheckBox = findViewById(R.id.cb_agree_reg);
        createAccountButton = findViewById(R.id.btn_create_reg);
        loginButton = findViewById(R.id.btn_login_reg);

        createAccountButton.setOnClickListener(v -> {
            String name = fullName.getText().toString().trim();
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            boolean termsAccepted = termsCheckBox.isChecked();

            if (name.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!termsAccepted) {
                Toast.makeText(Register.this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            } else if (passwordInput.length() < 8) {
                Toast.makeText(Register.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Register.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LogIn.class);
                startActivity(intent);
            }
        });
    }
}