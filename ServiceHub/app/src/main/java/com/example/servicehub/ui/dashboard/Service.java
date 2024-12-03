package com.example.servicehub.ui.dashboard;

public class Service {
    private String serviceName;
    private String workerName;
    private double rating;

    public Service(String serviceName, String workerName, double rating) {
        this.serviceName = serviceName;
        this.workerName = workerName;
        this.rating = rating;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public double getRating() {
        return rating;
    }
}

