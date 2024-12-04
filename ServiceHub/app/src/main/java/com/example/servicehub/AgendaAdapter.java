package com.example.servicehub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder> {

    private List<Agenda> agendaList;

    public AgendaAdapter(List<Agenda> agendaList) {
        this.agendaList = agendaList;
    }


    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, parent, false);
        return new AgendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position) {
        Agenda agenda = agendaList.get(position);
        holder.serviceNameTextView.setText(agenda.getAgendaName());
        holder.noteTextView.setText(agenda.getNote());
        holder.dateTextView.setText(agenda.getDate());
    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    static class AgendaViewHolder extends RecyclerView.ViewHolder {
        TextView serviceNameTextView;
        TextView noteTextView;
        TextView dateTextView;

        public AgendaViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceNameTextView = itemView.findViewById(R.id.userName_ag);
            noteTextView = itemView.findViewById(R.id.note_ag);
            dateTextView = itemView.findViewById(R.id.date_ag);
        }
    }
}


