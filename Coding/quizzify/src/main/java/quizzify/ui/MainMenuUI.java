// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.ui;

import quizzify.service.StorageService;
import java.util.Scanner;

public class MainMenuUI {
    private final StorageService storageService;
    private final QuizUI quizUI;
    private final Scanner scanner;

    public MainMenuUI(StorageService storageService, QuizUI quizUI) {
        this.storageService = storageService;
        this.quizUI = quizUI;
        this.scanner = new Scanner(System.in);
    }
    
    public void display() {
        while (true) {
            System.out.println("\n=== Quizzify Main Menu ===");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Take a quiz");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    quizUI.createQuiz();
                    break;
                case "2":
                    quizUI.takeQuiz();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
} 