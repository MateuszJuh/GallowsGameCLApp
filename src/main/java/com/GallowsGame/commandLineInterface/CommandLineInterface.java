package com.GallowsGame.commandLineInterface;

import com.GallowsGame.game.GallowsGame;
import com.GallowsGame.models.GameState;
import com.GallowsGame.models.Player;
import com.GallowsGame.models.UserData;
import com.GallowsGame.services.PlayerService;

import java.util.Scanner;

public class CommandLineInterface {
    private GallowsGame gallowsGame;
    private Scanner scanner;
    private Player player;
    private boolean isLogged = false;
    private PlayerService playerService;

    public CommandLineInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printWelcome() {
        System.out.println("Welcome to gallows game.");
        boolean doAgain = true;
        while(doAgain){
            System.out.println("s - to start game\nq - to quit");
            String next = scanner.nextLine();
            char symbol = next.charAt(0);
            switch (symbol) {
                case 's':
                    startGame();
                    break;
                case 'q':
                    doAgain = false;
                    break;
                case 'l':
                    login();
                    break;
                default:
                    System.out.println("Invalid symbol");
                    break;
            }
        }
    }

    private void startGame() {
        gallowsGame = new GallowsGame();
        gallowsGame.startGame();
        while (gallowsGame.getGameState() == GameState.IN_PROGRESS) {
            printGameInterface();
            gallowsGame.makeGuess(getSingleLetter());
        }
        if(gallowsGame.getGameState() == GameState.WON){
            System.out.println("Win");
        }else {
            System.out.println("lost");
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
        System.out.println("\nGuess letter");
    }

    private char getSingleLetter() {
        char guessedLetter = ' ';
        boolean isSingle = false;
        while(!isSingle) {
            String next = scanner.nextLine();
            next.replaceAll(" ", "");
            if (next.length() == 1) {
                guessedLetter = next.charAt(0);
                isSingle = true;
            } else {
                System.out.println("Too many letters!");
            }
        }
        return guessedLetter;
    }

    private void login(){
        if(playerService == null){
            playerService = new PlayerService();
        }
        playerService.login(loadUsernameAndPasswordToHash());
    }

    private UserData loadUsernameAndPasswordToHash() {
        UserData userData = new UserData();
        System.out.println("insert username:");
        userData.setUsername(scanner.nextLine());
        System.out.println("insert password");
        userData.setPassword(scanner.nextLine());
        return userData;
    }


}
