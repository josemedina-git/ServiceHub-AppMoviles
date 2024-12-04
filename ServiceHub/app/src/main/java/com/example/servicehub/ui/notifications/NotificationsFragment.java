package com.example.servicehub.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.servicehub.Agendas;
import com.example.servicehub.LogIn;
import com.example.servicehub.ProfileService;
import com.example.servicehub.R;
import com.example.servicehub.Suscription;
import com.example.servicehub.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    ImageButton logoutIButton, suscriptionIButton, agendaButton;
    Button saveChanges;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    EditText nameField, phoneField, addressField, emailField, passwordField;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        logoutIButton = root.findViewById(R.id.ibtn_logout);
        suscriptionIButton = root.findViewById(R.id.cameraIcon);
        agendaButton = root.findViewById(R.id.ibtn_editIcon);
        saveChanges = root.findViewById(R.id.btn_save);

        // Referencia a los campos de texto
        nameField = root.findViewById(R.id.nameField);
        phoneField = root.findViewById(R.id.phoneField);
        addressField = root.findViewById(R.id.addressField);
        emailField = root.findViewById(R.id.emailField);
        passwordField = root.findViewById(R.id.passwordField);

        // Obtener el usuario autenticado
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            emailField.setText(userEmail);  // Establecer el email en el campo

            // Obtener datos adicionales de Firestore
            DocumentReference userRef = db.collection("users").document(currentUser.getUid());
            userRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String fullName = documentSnapshot.getString("fullName");
                    String phone = documentSnapshot.getString("phoneNumber");
                    String address = documentSnapshot.getString("address");

                    // Mostrar los datos adicionales en los campos de texto
                    nameField.setText(fullName);
                    phoneField.setText(phone);
                    addressField.setText(address);
                }
            });
        }

        // Lógica para cerrar sesión
        logoutIButton.setOnClickListener(view -> {
            mAuth.signOut();
            Intent intent = new Intent(getActivity(), LogIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        // Lógica para guardar los cambios
        saveChanges.setOnClickListener(view -> {
            String fullName = nameField.getText().toString();
            String phone = phoneField.getText().toString();
            String address = addressField.getText().toString();

            // Almacenar los datos adicionales en Firestore
            if (currentUser != null) {
                DocumentReference userRef = db.collection("users").document(currentUser.getUid());
                userRef.set(new User(fullName, phone, address))
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(getActivity(), "Changes saved successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(getActivity(), "Error saving data", Toast.LENGTH_SHORT).show();
                        });
            }
        });

        suscriptionIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Suscription.class);
                startActivity(intent);
            }
        });

        agendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Agendas.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}