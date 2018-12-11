package com.GallowsGame.models;

public class Player {
    private int Score;
    private String username;
    private String passwordHash;

    public Player() {
    }

    public Player(int score, String username, String passwordHash) {
        Score = score;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
