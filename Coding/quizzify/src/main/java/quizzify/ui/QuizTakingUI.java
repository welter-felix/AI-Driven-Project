// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import quizzify.model.Question;
import quizzify.model.Quiz;
import quizzify.model.QuizResult;
import quizzify.controller.QuizController;

import java.util.List;
import java.util.ArrayList;

public class QuizTakingUI {
    private final QuizController quizController;

    public QuizTakingUI(QuizController quizController) {
        this.quizController = quizController;
    }

    public void show(Stage primaryStage) {
        List<Quiz> quizzes = quizController.getAvailableQuizzes();
        if (quizzes.isEmpty()) {
            showAlert("No quizzes available.");
            new MainMenuUI(quizController).start(primaryStage);
            return;
        }
        ListView<String> quizList = new ListView<>();
        for (Quiz q : quizzes) quizList.getItems().add(q.getTitle());
        Button startBtn = new Button("Start Quiz");
        Button cancelBtn = new Button("Cancel");
        VBox root = new VBox(20, new Label("Select a quiz:"), quizList, startBtn, cancelBtn);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8fafc, #dbeafe);");
        quizList.setMaxHeight(120);
        quizList.setStyle("-fx-background-radius: 8; -fx-border-radius: 8;");
        startBtn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 14px;");
        cancelBtn.setStyle("-fx-background-color: #e5e7eb; -fx-text-fill: #111827; -fx-background-radius: 8; -fx-font-size: 14px;");

        startBtn.setOnAction(e -> {
            int idx = quizList.getSelectionModel().getSelectedIndex();
            if (idx < 0) {
                showAlert("Select a quiz first.");
                return;
            }
            Quiz quiz = quizController.getQuizByIndex(idx);
            showQuizWindow(primaryStage, quiz);
        });
        cancelBtn.setOnAction(e -> new MainMenuUI(quizController).start(primaryStage));

        primaryStage.setTitle("Take Quiz");
        if (primaryStage.getScene() == null) {
            Scene scene = new Scene(root, 500, 400);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(root);
        }
    }

    private void showQuizWindow(Stage primaryStage, Quiz quiz) {
        List<Question> questions = quiz.getQuestions();
        List<Integer> userAnswers = new ArrayList<>();
        VBox root = new VBox(30);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8fafc, #dbeafe);");
        Label qLabel = new Label();
        qLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1e293b; -fx-padding: 0 0 10 0;");
        GridPane grid = new GridPane();
        grid.setHgap(18);
        grid.setVgap(18);
        grid.setAlignment(Pos.CENTER);
        Button nextBtn = new Button("Next");
        nextBtn.setDisable(true);
        nextBtn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 15px; -fx-padding: 8 24 8 24;");
        int[] idx = {0};
        int[] selected = {-1};

        Runnable showQuestion = () -> {
            if (idx[0] >= questions.size()) {
                QuizResult result = quizController.takeQuiz(quiz, userAnswers.stream().mapToInt(i->i).toArray());
                new ResultsUI(quizController).show(primaryStage, result);
                return;
            }
            Question q = questions.get(idx[0]);
            qLabel.setText("Q" + (idx[0]+1) + ": " + q.getQuestionText());
            grid.getChildren().clear();
            selected[0] = -1;
            nextBtn.setDisable(true);
            List<String> opts = q.getAnswerOptions();
            int numCols = 2;
            int numRows = (int) Math.ceil(opts.size() / (double) numCols);
            for (int i = 0; i < opts.size(); i++) {
                Button optBtn = new Button(opts.get(i));
                optBtn.setMaxWidth(Double.MAX_VALUE);
                optBtn.setMinHeight(50);
                optBtn.setStyle("-fx-background-color: #e0e7ef; -fx-font-size: 15px; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #cbd5e1; -fx-border-width: 2;");
                optBtn.setWrapText(true);
                int answerIdx = i;
                optBtn.setOnAction(e -> {
                    selected[0] = answerIdx;
                    // Highlight selected button
                    for (int j = 0; j < grid.getChildren().size(); j++) {
                        Button b = (Button) grid.getChildren().get(j);
                        b.setStyle("-fx-background-color: #e0e7ef; -fx-font-size: 15px; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #cbd5e1; -fx-border-width: 2;");
                    }
                    optBtn.setStyle("-fx-background-color: #38bdf8; -fx-text-fill: white; -fx-font-size: 15px; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #2563eb; -fx-border-width: 2;");
                    nextBtn.setDisable(false);
                });
                // Add hover effect
                optBtn.setOnMouseEntered(e -> {
                    if (selected[0] != answerIdx)
                        optBtn.setStyle("-fx-background-color: #bae6fd; -fx-font-size: 15px; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #2563eb; -fx-border-width: 2;");
                });
                optBtn.setOnMouseExited(e -> {
                    if (selected[0] != answerIdx)
                        optBtn.setStyle("-fx-background-color: #e0e7ef; -fx-font-size: 15px; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #cbd5e1; -fx-border-width: 2;");
                });
                grid.add(optBtn, i % numCols, i / numCols);
                GridPane.setHgrow(optBtn, Priority.ALWAYS);
                GridPane.setVgrow(optBtn, Priority.ALWAYS);
            }
        };

        nextBtn.setOnAction(e -> {
            if (selected[0] == -1) {
                showAlert("Select an answer.");
                return;
            }
            userAnswers.add(selected[0]);
            idx[0]++;
            showQuestion.run();
        });

        root.getChildren().setAll(qLabel, grid, nextBtn);
        showQuestion.run();

        primaryStage.setTitle("Quiz: " + quiz.getTitle());
        if (primaryStage.getScene() == null) {
            Scene scene = new Scene(root, 500, 400);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(root);
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
} 