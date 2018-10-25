package com.GallowsGame.models;

public class WordDao {
    private long id;
    private String word;

    public WordDao() {
    }

    public WordDao(String word) {
        this.word = word;
    }

    public WordDao(long id, String word) {
        this.id = id;
        this.word = word;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
