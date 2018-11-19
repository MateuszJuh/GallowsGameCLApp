package com.GallowsGame.services;

import com.GallowsGame.models.WordToGuess;

public class WordService {
    public WordToGuess getRandomWord() {
        return new WordToGuess(new char[]{'r', 'a', 'n', 'd'});
    }//TODO WordService
}
