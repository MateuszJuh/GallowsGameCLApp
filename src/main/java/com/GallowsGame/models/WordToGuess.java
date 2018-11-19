package com.GallowsGame.models;

public class WordToGuess {
    private char[] chars;
    private boolean[] isCharGuessed;

    public WordToGuess() {
    }

    public WordToGuess(char[] chars) {
        this.chars = chars;
        isCharGuessed = new boolean[chars.length];
        for(int i =0; i<chars.length; i++){
            isCharGuessed[i]=false;
        }
    }

    public char[] getChars() {
        return chars;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }

    public boolean[] getIsCharGuessed() {
        return isCharGuessed;
    }

    public void setIsCharGuessed(boolean[] isCharGuessed) {
        this.isCharGuessed = isCharGuessed;
    }
}
