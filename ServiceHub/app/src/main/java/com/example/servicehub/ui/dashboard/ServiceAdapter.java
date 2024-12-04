package com.example.servicehub.ui.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servicehub.ProfileService;
import com.example.servicehub.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;

    public ServiceAdapter(List<Service> serviceList) {
        this.serviceList = serviceList;
    }


    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        holder.serviceNameTextView.setText(service.getServiceName());
        holder.workerNameTextView.setText(service.getWorkerName());
        holder.ratingTextView.setText(service.getRating()); // Rating como String

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileService.class);
            intent.putExtra("serviceName", service.getServiceName());
            intent.putExtra("workerName", service.getWorkerName());
            intent.putExtra("title", service.getTitle());
            intent.putExtra("description", service.getDescription());
            intent.putExtra("followers", service.getFollowers());
            intent.putExtra("rating", service.getRating());
            holder.itemView.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView serviceNameTextView;
        TextView workerNameTextView;
        TextView ratingTextView;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceNameTextView = itemView.findViewById(R.id.serviceTextView);
            workerNameTextView = itemView.findViewById(R.id.workerTextView);
            ratingTextView = itemView.findViewById(R.id.starsTextView);
        }
    }
}

