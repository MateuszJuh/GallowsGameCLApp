package com.GallowsGame.models;

public class Player {
    private long id;
    private int Score;
    private String username;

    public Player() {
    }

    public Player(int score, String username) {
        Score = score;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}
