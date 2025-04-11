package com.example;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CLI {
    NoteDao noteDao;
    Scanner scanner;
    boolean active = true;

    public CLI(Scanner scanner) {
        this.noteDao = new NoteDaoImpl();
        this.scanner = scanner;
        this.active = true;
    }

    public void promptForActions() {
        try (Scanner inputScanner = new Scanner(System.in)) {
            Map<Integer, Runnable> actions = Map.of(
                1, () -> addNote(),
                2, () -> viewNote(),
                3, () -> viewAllNotes(),
                4, () -> binaryCalculator(),
                5, () -> active = false
            );

            while (active) {
                System.out.println("What would you like to do?" +
                    "\n 1: Try to add a note item." +
                    "\n 2: View a note by ID." +
                    "\n 3: View a truncated list of all notes." +
                    "\n 4: Do a binary calculation" +
                    "\n 5: Quit\n");

                int inputSelection = inputScanner.nextInt();
                inputScanner.nextLine();

                actions.getOrDefault(inputSelection, () -> {
                    System.out.println("Invalid selection.");
                }).run();
            }
        }
    }

    private void addNote() {
        String content;
        String priority = null;

        System.out.println("\nNote Contents:");
        content = scanner.nextLine();

        while (priority == null) {
            System.out.println("\nPriority (high/medium/low):");
            priority = scanner.nextLine();
            Set<String> validPriorities = Set.of("high", "medium", "low");
            if (!validPriorities.contains(priority.toLowerCase())) {
                System.out.println("\nPlease enter high, medium or low:");
                priority = null;
            }
        }

        if (noteDao.addNote(content, priority.toLowerCase()) != 0) {
            System.out.println("\nNote added successfully.\n");
        }
    }

    private void viewNote() {
        System.out.println("\nNote ID:");
        int id = scanner.nextInt();

        Note note = noteDao.getNoteById(id);
        if (note != null) {
            System.out.println("\nNote #" + id + 
                "        Priority: " + note.getPriority());
            System.out.println(note.getContent() + "\n");
        } else {
            System.out.println("\nCouldn't find that note.\n");
        }
    }

    private void viewAllNotes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'viewAllNotes'");
    }

    private void binaryCalculator() {
        System.out.println("\nValue 1:");
        int value1 = scanner.nextInt();
        System.out.println("\nValue 2:");
        int value2 = scanner.nextInt();

        printBinaryResult(value1, value2, (a, b) -> a + b);
        printBinaryResult(value1, value2, (a, b) -> a * b);
    }

    private static void printBinaryResult(int a, int b, BinaryCalculator func) {
        // perform operation, print result
        int result = func.binaryOperation(a, b);
        System.out.println(result);
    }
}
