// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quizzify.controller.QuizController;

public class MainMenuUI {
    private final QuizController quizController;

    public MainMenuUI(QuizController quizController) {
        this.quizController = quizController;
    }

    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Quizzify");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #1e293b; -fx-padding: 0 0 20 0;");
        Button createQuizBtn = new Button("Create Quiz");
        Button takeQuizBtn = new Button("Take Quiz");
        Button manageQuizBtn = new Button("Manage Quizzes");
        Button exitBtn = new Button("Exit");

        createQuizBtn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 16px; -fx-padding: 10 30 10 30;");
        takeQuizBtn.setStyle("-fx-background-color: #38bdf8; -fx-text-fill: #1e293b; -fx-background-radius: 8; -fx-font-size: 16px; -fx-padding: 10 30 10 30;");
        manageQuizBtn.setStyle("-fx-background-color: #fbbf24; -fx-text-fill: #1e293b; -fx-background-radius: 8; -fx-font-size: 16px; -fx-padding: 10 30 10 30;");
        exitBtn.setStyle("-fx-background-color: #e5e7eb; -fx-text-fill: #111827; -fx-background-radius: 8; -fx-font-size: 16px; -fx-padding: 10 30 10 30;");

        createQuizBtn.setOnAction(e -> new QuizCreationUI(quizController, () -> this.start(primaryStage)).show(primaryStage));
        takeQuizBtn.setOnAction(e -> new QuizTakingUI(quizController).show(primaryStage));
        manageQuizBtn.setOnAction(e -> new ManageQuizzesUI(quizController).show(primaryStage));
        exitBtn.setOnAction(e -> primaryStage.close());

        VBox vbox = new VBox(25, titleLabel, createQuizBtn, takeQuizBtn, manageQuizBtn, exitBtn);
        vbox.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8fafc, #dbeafe); -fx-padding: 60; -fx-alignment: center;");

        primaryStage.setTitle("Quizzify - Main Menu");
        if (primaryStage.getScene() == null) {
            Scene scene = new Scene(vbox, 500, 400);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(vbox);
        }
        primaryStage.show();
    }
} 