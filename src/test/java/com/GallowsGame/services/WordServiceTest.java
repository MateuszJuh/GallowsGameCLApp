package com.GallowsGame.services;

import com.GallowsGame.models.WordToGuess;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class WordServiceTest {
    private WordToGuess wordToGuess;
    private WordService wordService;

    @Before
    public void prepare(){
        wordToGuess = new WordToGuess(new char[]{'r', 'a', 'n', 'd', 'o', 'm'});
        wordService = Mockito.mock(WordService.class);
        Mockito.when(wordService.getRandomWordJson()).thenReturn("{ \"id\":1,\"word\":\"random\"}");
        Mockito.when(wordService.getRandomWord()).thenCallRealMethod();
    }

    @Test
    public void getRandomWord_gettingRandomWordFromAPI_WordToGuessObject(){
        WordToGuess wordToGuess = wordService.getRandomWord();
        for (int i = 0; i < wordToGuess.getChars().length; i++) {
            Assert.assertEquals(this.wordToGuess.getChars()[i], wordToGuess.getChars()[i]);
        }
        for (int i = 0; i < this.wordToGuess.getChars().length; i++) {
            Assert.assertEquals(this.wordToGuess.getChars()[i], wordToGuess.getChars()[i]);
        }
    }
}