package com.example.servicehub;

public class Review {
    private String username;
    private String commentText;
    private String time;
    private int avatarUrl;

    public Review(String username, String commentText, String time, int avatarUrl) {
        this.username = username;
        this.commentText = commentText;
        this.time = time;
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getTime() {
        return time;
    }

    public int getAvatarUrl() {
        return avatarUrl;
    }
}
