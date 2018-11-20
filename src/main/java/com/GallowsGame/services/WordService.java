package com.GallowsGame.services;

import com.GallowsGame.models.WordToGuess;

public class WordService {//TODO WordService
    public WordToGuess getRandomWord() {
        return new WordToGuess(new char[]{'r', 'a', 'n', 'd', 'o', 'm'});
    }
}
