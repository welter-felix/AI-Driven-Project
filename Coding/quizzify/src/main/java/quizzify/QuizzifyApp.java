// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify;

import javafx.application.Application;
import javafx.stage.Stage;
import quizzify.service.StorageService;
import quizzify.service.QuizService;
import quizzify.controller.QuizManager;
import quizzify.controller.ResultsController;
import quizzify.controller.QuizController;
import quizzify.ui.MainMenuUI;

public class QuizzifyApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        StorageService storageService = new StorageService();
        QuizService quizService = new QuizService();
        QuizManager quizManager = new QuizManager(storageService);
        ResultsController resultsController = new ResultsController(quizService);
        QuizController quizController = new QuizController(quizManager, resultsController);
        MainMenuUI mainMenu = new MainMenuUI(quizController);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(350);
        mainMenu.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}