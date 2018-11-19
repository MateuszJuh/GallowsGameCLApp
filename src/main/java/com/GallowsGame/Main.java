package com.GallowsGame;

import com.GallowsGame.commandLineInterface.CommandLineInterface;

public class Main {
    public static void main(String[] args) {
        CommandLineInterface commandLineInterface = new CommandLineInterface();
        commandLineInterface.printWelcome();
    }
}
