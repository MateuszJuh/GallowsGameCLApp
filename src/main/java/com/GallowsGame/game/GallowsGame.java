package com.GallowsGame.game;

import com.GallowsGame.models.GameState;
import com.GallowsGame.models.WordToGuess;
import com.GallowsGame.services.WordService;

public class GallowsGame {//TODO GallowsGame
    private int attemptsLeft;
    private WordToGuess wordToGuess;
    private WordService wordService;
    GameState gameState;

    public GallowsGame() {
        wordService = new WordService();
        attemptsLeft = 10;
        gameState = GameState.IN_PROGRES;
    }

    public void startGame(){
        wordToGuess = wordService.getRandomWord();
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public WordToGuess getWordToGuess() {
        return wordToGuess;
    }

    public void setWordToGuess(WordToGuess wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public WordService getWordService() {
        return wordService;
    }

    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}