package com.GallowsGame.commandLineInterface;

import com.GallowsGame.game.GallowsGame;
import com.GallowsGame.models.GameState;
import com.GallowsGame.models.UserData;
import com.GallowsGame.services.PlayerService;

import java.util.Scanner;

public class CommandLineInterface {
    private GallowsGame gallowsGame;
    private Scanner scanner;
    private boolean isLogged = false;
    private PlayerService playerService;

    public CommandLineInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printWelcome() {
        System.out.println("Welcome to gallows game.\nyou can login with - l\nand register - r");
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
                case 'r':
                    register();
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
            increaseScoreIfLogged();
        }else {
            System.out.println("lost");
        }
    }

    private void increaseScoreIfLogged() {
        if(isLogged){
            playerService.increaseScore();
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
        initPlayerService();
        boolean login = playerService.login(getUsernameAndPasswordToHash());
        if(login){
            isLogged = true;
            System.out.println("logged in");
        }else {
            System.out.println("authentication failed");
        }
    }

    private void register() {
        initPlayerService();
        if(isLogged){
            System.out.println("can not register while logged in");
        }else {
            boolean doAgain=true;
            UserData userData = getDataToRegistration();
            if(playerService.register(userData)){
                System.out.println("registration success, now you can log in to new account");
            }else{
                System.out.println("registration failed, try another username");
            }
        }
    }

    private UserData getDataToRegistration() {
        boolean doAgain=true;
        UserData userData = new UserData();
        while (doAgain){
            userData = getUsernameAndPasswordToHash();
            System.out.println("insert password again:");
            String s = scanner.nextLine();
            if(!s.equals(userData.getPassword())){
                System.out.println("passwords are different");
            }else {
                doAgain = false;
            }
        }
        return userData;
    }

    private void initPlayerService(){
        if(playerService==null){
            playerService = new PlayerService();
        }
    }

    private UserData getUsernameAndPasswordToHash() {
        UserData userData = new UserData();
        System.out.println("insert username:");
        userData.setUsername(scanner.nextLine());
        System.out.println("insert password:");
        userData.setPassword(scanner.nextLine());
        return userData;
    }

}
