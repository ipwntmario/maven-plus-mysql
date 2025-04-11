package com.example;

import java.io.IOException;
import java.util.Scanner;

/**
 * App is an app that can insert and query from an SQL table of notes.
 */
public final class App {
    // Private constructor to prevent instantiation
    private App() {
        throw new UnsupportedOperationException(
            "Utility class should not be instantiated.");
    }

    /**
     * Start of the program.
     * @param args does nothing.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        CLI cli = new CLI(scanner);

        System.out.println("Welcome to Macrosoft Robert!");
        cli.promptForActions();

        scanner.close();
    }
}
