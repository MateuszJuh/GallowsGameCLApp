package com.GallowsGame.game;

import com.GallowsGame.models.GameState;
import com.GallowsGame.models.WordToGuess;
import com.GallowsGame.services.WordService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GallowsGameTest {
    private WordService wordServiceMock;
    private WordToGuess wordToGuess;
    private GallowsGame gallowsGame;

    @Before
    public void prepare(){
        gallowsGame = new GallowsGame();
        wordServiceMock = Mockito.mock(WordService.class);
        wordToGuess = new WordToGuess(new char[]{'r','a', 'n', 'd', 'o', 'm'});
        gallowsGame.setWordToGuess(wordToGuess);
        Mockito.when(wordServiceMock.getRandomWord()).thenReturn(wordToGuess);
        gallowsGame.setWordService(wordServiceMock);
    }

    @Test
    public void makeGuess_makeInvalidGuess_decreaseAttemptsLeft(){
        Assert.assertEquals(10, gallowsGame.getAttemptsLeft());
        gallowsGame.makeGuess('e');
        Assert.assertEquals(9, gallowsGame.getAttemptsLeft());
        Assert.assertEquals(GameState.IN_PROGRESS, gallowsGame.getGameState());
    }

    @Test
    public void makeGuess_makeValidGuess_changeIsCharGuessedInWordToGuessToTrue(){
        for (boolean b: gallowsGame.getWordToGuess().getIsCharGuessed()) {
            Assert.assertFalse(b);
        }
        gallowsGame.makeGuess('r');
        Assert.assertTrue(gallowsGame.getWordToGuess().getIsCharGuessed()[0]);
        for (int i = 1; i < 6; i++) {
            Assert.assertFalse(gallowsGame.getWordToGuess().getIsCharGuessed()[i]);
        }
        Assert.assertEquals(GameState.IN_PROGRESS, gallowsGame.getGameState());
        Assert.assertEquals(10, gallowsGame.getAttemptsLeft());
    }

    @Test
    public void makeGuess_guessAllLetters_changeGameStateToWon(){
        Assert.assertEquals(GameState.IN_PROGRESS, gallowsGame.getGameState());
        gallowsGame.makeGuess('r');
        gallowsGame.makeGuess('a');
        gallowsGame.makeGuess('n');
        Assert.assertEquals(GameState.IN_PROGRESS, gallowsGame.getGameState());
        Assert.assertEquals(10, gallowsGame.getAttemptsLeft());
        gallowsGame.makeGuess('d');
        gallowsGame.makeGuess('o');
        gallowsGame.makeGuess('m');
        Assert.assertEquals(GameState.WON, gallowsGame.getGameState());
        Assert.assertEquals(10, gallowsGame.getAttemptsLeft());
    }

    @Test
    public void makeGuess_useAllAttempts_changeGameStateToLost(){
        Assert.assertEquals(GameState.IN_PROGRESS, gallowsGame.getGameState());
        for (int i = 1; i <= 5; i++) {
            gallowsGame.makeGuess('b');
        }
        Assert.assertEquals(GameState.IN_PROGRESS, gallowsGame.getGameState());
        Assert.assertEquals(5,gallowsGame.getAttemptsLeft());
        for (int i = 6; i <= 10; i++) {
            gallowsGame.makeGuess('b');
        }
        Assert.assertEquals(GameState.LOST, gallowsGame.getGameState());
        Assert.assertEquals(0, gallowsGame.getAttemptsLeft());
    }

}