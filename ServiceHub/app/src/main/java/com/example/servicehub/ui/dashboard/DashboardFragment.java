package com.example.servicehub.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servicehub.R;
import com.example.servicehub.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private RecyclerView recyclerView;
    private ServiceAdapter serviceAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Lista de datos simulados
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(new Service("Plumbing", "Emily Thompson", 5.0));
        serviceList.add(new Service("Plumbing", "Michael Johnson", 4.9));
        serviceList.add(new Service("Plumbing", "Sarah Carter", 4.9));
        serviceList.add(new Service("Designer", "David Miller", 4.9));
        serviceList.add(new Service("Designer", "Jessica Roberts", 4.8));
        serviceList.add(new Service("Carpenter", "Daniel Wilson", 4.8));
        serviceList.add(new Service("Carpenter", "Ashley Turner", 4.5));
        serviceList.add(new Service("Moving", "James Baker", 4.5));

        // Configuración del adaptador
        serviceAdapter = new ServiceAdapter(serviceList);
        recyclerView.setAdapter(serviceAdapter);

        //final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}