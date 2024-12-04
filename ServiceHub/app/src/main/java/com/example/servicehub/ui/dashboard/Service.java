package com.example.servicehub.ui.dashboard;

public class Service {
    private String serviceName, workerName, title, description, followers, rating;

    public Service(String serviceName, String workerName, String title, String description, String followers, String rating) {
        this.serviceName = serviceName;
        this.workerName = workerName;
        this.title = title;
        this.description = description;
        this.followers = followers;
        this.rating = rating;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFollowers() {
        return followers;
    }
    public String getRating() {
        return rating;
    }
}

