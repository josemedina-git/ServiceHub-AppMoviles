package com.example.servicehub;
public class Agenda {
    private String serviceName, note, date;

    public Agenda(String serviceName, String note, String date) {
        this.serviceName = serviceName;
        this.note = note;
        this.date = date;
    }

    public String getAgendaName() {
        return serviceName;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }
}