package com.GallowsGame.commandLineInterface;

import com.GallowsGame.game.GallowsGame;
import com.GallowsGame.models.GameState;

import java.util.Scanner;

public class CommandLineInterface {//TODO CommandLineInterface
    private GallowsGame gallowsGame;
    private final Scanner scanner = new Scanner(System.in);

    public CommandLineInterface() {
        gallowsGame = new GallowsGame();
    }

    public void printWelcome() {
        System.out.println("Welcome to gallows game.\n s - to start game\n q - to quit");
        String line = scanner.next();
        char symbol = line.charAt(0);
        switch (symbol) {
            case 's':
                startGame();
                break;
            case 'q':
                scanner.close();
                break;
            default:
                break;
        }
    }

    private void startGame() {
        gallowsGame.startGame();
        while (gallowsGame.getGameState() == GameState.IN_PROGRES) {
            printGameInterface();
            getSingleLetter();
            gallowsGame.setGameState(GameState.WON);
            //gallowsGame.validateChar(getSingleLetter());
        }

    }

    private void printGameInterface() {
        System.out.println("Attempts left: " + gallowsGame.getAttemptsLeft());
        for (int i = 0; i < gallowsGame.getWordToGuess().getChars().length; i++) {
            if (gallowsGame.getWordToGuess().getIsCharGuessed()[i]) {
                System.out.print(gallowsGame.getWordToGuess().getChars()[i]);
            } else {
                System.out.print("_");
            }
        }
        System.out.println("\nGuess another letter");
    }

    private char getSingleLetter() {
        char guessedLetter = ' ';
        boolean isSingle = false;
        do {
            String next = scanner.next();
            if (next.length() == 1) {
                guessedLetter = next.charAt(0);
                isSingle = true;
            } else {
                System.out.println("Too many letters!");
            }
        } while (!isSingle);
        return guessedLetter;
    }


}
