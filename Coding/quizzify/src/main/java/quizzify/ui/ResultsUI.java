// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quizzify.model.Question;
import quizzify.model.QuizResult;
import quizzify.controller.QuizController;

public class ResultsUI {
    private final QuizController quizController;

    public ResultsUI(QuizController quizController) {
        this.quizController = quizController;
    }

    public void show(Stage primaryStage, QuizResult result) {
        VBox root = new VBox(18);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8fafc, #dbeafe);");
        Label scoreLabel = new Label("Score: " + result.getScore() + "/" + result.getQuiz().getQuestions().size());
        scoreLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1e293b; -fx-padding: 0 0 10 0;");
        Label percentLabel = new Label(String.format("Percentage: %.2f%%", result.getPercentage()));
        percentLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2563eb; -fx-padding: 0 0 10 0;");
        VBox feedbackBox = new VBox(8);
        feedbackBox.setAlignment(Pos.CENTER_LEFT);
        for (int i = 0; i < result.getQuiz().getQuestions().size(); i++) {
            Question q = result.getQuiz().getQuestions().get(i);
            int userAns = result.getUserAnswers().get(i);
            String correct = q.getAnswerOptions().get(q.getCorrectAnswerIndex());
            String userStr = (userAns >= 0 && userAns < q.getAnswerOptions().size()) ? q.getAnswerOptions().get(userAns) : "No answer";
            String feedback = "Q" + (i+1) + ": " + q.getQuestionText() + "\n  Your answer: " + userStr +
                    (userAns == q.getCorrectAnswerIndex() ? " (Correct)" : " (Incorrect)\n  Correct answer: " + correct);
            Label feedbackLabel = new Label(feedback);
            feedbackLabel.setStyle("-fx-background-color: #f1f5f9; -fx-background-radius: 8; -fx-padding: 8; -fx-font-size: 14px; -fx-border-color: #cbd5e1; -fx-border-radius: 8; -fx-border-width: 1;");
            feedbackBox.getChildren().add(feedbackLabel);
        }
        Button backBtn = new Button("Back to Main Menu");
        backBtn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 15px; -fx-padding: 8 24 8 24;");
        backBtn.setOnAction(e -> new MainMenuUI(quizController).start(primaryStage));
        root.getChildren().addAll(scoreLabel, percentLabel, feedbackBox, backBtn);
        primaryStage.setTitle("Quiz Results");
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(350);
    }
} 