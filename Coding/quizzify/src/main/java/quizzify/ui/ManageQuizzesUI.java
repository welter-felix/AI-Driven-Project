// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import quizzify.controller.QuizController;
import quizzify.model.Quiz;

import java.util.List;

public class ManageQuizzesUI {
    private final QuizController quizController;

    public ManageQuizzesUI(QuizController quizController) {
        this.quizController = quizController;
    }

    public void show(Stage primaryStage) {
        VBox root = new VBox(18);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8fafc, #dbeafe);");

        Label titleLabel = new Label("Manage Quizzes");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1e293b; -fx-padding: 0 0 10 0;");

        List<Quiz> quizzes = quizController.getAvailableQuizzes();
        VBox quizListBox = new VBox(10);
        quizListBox.setAlignment(Pos.CENTER_LEFT);
        if (quizzes.isEmpty()) {
            Label emptyLabel = new Label("No quizzes available.");
            emptyLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #64748b;");
            quizListBox.getChildren().add(emptyLabel);
        } else {
            for (Quiz quiz : quizzes) {
                HBox quizRow = new HBox(10);
                quizRow.setAlignment(Pos.CENTER_LEFT);
                Label quizLabel = new Label(quiz.getTitle());
                quizLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #1e293b; -fx-background-color: #f1f5f9; -fx-background-radius: 8; -fx-padding: 6 12 6 12;");
                Button deleteBtn = new Button("Delete");
                deleteBtn.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 13px; -fx-padding: 6 18 6 18;");
                deleteBtn.setOnAction(e -> {
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this quiz?", ButtonType.YES, ButtonType.NO);
                    confirm.setHeaderText("Confirm Deletion");
                    confirm.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            quizController.deleteQuiz(quiz);
                            // Refresh the list
                            new ManageQuizzesUI(quizController).show(primaryStage);
                        }
                    });
                });
                quizRow.getChildren().addAll(quizLabel, deleteBtn);
                quizListBox.getChildren().add(quizRow);

                // Double-click to edit
                quizRow.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        new QuizCreationUI(quizController, () -> this.show(primaryStage)).show(primaryStage, quiz);
                    }
                });
            }
        }

        Button backBtn = new Button("Back to Main Menu");
        backBtn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 15px; -fx-padding: 8 24 8 24;");
        backBtn.setOnAction(e -> new MainMenuUI(quizController).start(primaryStage));

        root.getChildren().addAll(titleLabel, quizListBox, backBtn);
        primaryStage.setTitle("Manage Quizzes");
        if (primaryStage.getScene() == null) {
            Scene scene = new Scene(root, 600, 500);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(root);
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
} 