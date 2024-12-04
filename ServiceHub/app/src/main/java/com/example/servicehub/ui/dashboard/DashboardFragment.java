package com.example.servicehub.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servicehub.R;
import com.example.servicehub.databinding.FragmentDashboardBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private RecyclerView recyclerView;
    private ServiceAdapter serviceAdapter;

    private FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Inicializa Firestore
        db = FirebaseFirestore.getInstance();

        // Lista para almacenar los servicios obtenidos de Firestore
        List<Service> serviceList = new ArrayList<>();

        // Realiza la consulta a la colección de servicios
        db.collection("servicios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Limpiar la lista antes de agregar los nuevos datos
                        serviceList.clear();

                        // Iterar sobre los documentos y obtener los datos
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String nombreProfesional = document.getString("nombreProfesional");
                            String titulo = document.getString("titulo");
                            String servicio = document.getString("servicio");
                            String descripcion = document.getString("descripcion");
                            String followers = document.getString("followers");  // Convierte si es necesario
                            String ratingPromedio = document.getString("ratingPromedio");  // Convierte si es necesario

                            // Crear el objeto Service y agregarlo a la lista
                            Service service = new Service(servicio, nombreProfesional, titulo, descripcion, followers, ratingPromedio); // Puedes hacer conversiones si es necesario
                            serviceList.add(service);
                        }

                        // Actualiza el adaptador con los nuevos datos
                        serviceAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Error al obtener los servicios", Toast.LENGTH_SHORT).show();
                    }
                });

        // Configura el adaptador con la lista obtenida
        serviceAdapter = new ServiceAdapter(serviceList);
        recyclerView.setAdapter(serviceAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
