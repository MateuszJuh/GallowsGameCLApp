package com.GallowsGame;

import com.GallowsGame.commandLineInterface.CommandLineInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandLineInterface commandLineInterface = new CommandLineInterface(scanner);
        commandLineInterface.printWelcome();
        scanner.close();
    }
}
