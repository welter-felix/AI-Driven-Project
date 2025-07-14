// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify;

import quizzify.service.StorageService;
import quizzify.service.QuizService;
import quizzify.ui.MainMenuUI;
import quizzify.ui.QuizUI;

public class QuizzifyApp {
    public static void main(String[] args) {
        StorageService storageService = new StorageService();
        QuizService quizService = new QuizService();
        QuizUI quizUI = new QuizUI(storageService, quizService);
        MainMenuUI mainMenuUI = new MainMenuUI(storageService, quizUI);
        mainMenuUI.display();
    }
} 