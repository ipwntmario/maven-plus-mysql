package com.example;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    NoteDao noteDao;
    Scanner scanner;
    boolean active = true;

    public CLI(Scanner scanner) {
        this.noteDao = new NoteDaoImpl();
        this.scanner = scanner;
        this.active = true;
    }

    public void promptForActions() throws IOException {
        try (Scanner inputScanner = new Scanner(System.in)) {
            while (active) {

                System.out.println("What would you like to do?" +
                "\n 1: Try to add a note item." +
                "\n 2: View a note by ID." +
                "\n 3: View a truncated list of all notes." +
                "\n 4: Do a binary calculation" +
                "\n 5: Quit\n");

                // System.out.println(System.in.available());
                int inputSelection = inputScanner.nextInt();
                inputScanner.nextLine();

                switch (inputSelection) {
                    case 1:
                        addNote();
                        break;
                    case 2:
                        viewNote();
                        break;
                    case 3:
                        viewAllNotes();
                        break;
                    case 4:
                        binaryCalculator();
                        break;
                    case 5:
                        active = false;
                        break;
                    default:
                        break;
                }
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
            if (!
                (priority.equalsIgnoreCase("high") || 
                priority.equalsIgnoreCase("medium") || 
                priority.equalsIgnoreCase("low"))) {
                
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'binaryCalculator'");
    }
}
