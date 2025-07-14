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
import quizzify.controller.QuizController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizCreationUI {
    private final QuizController quizController;
    private final Runnable onCancel;

    public QuizCreationUI(QuizController quizController, Runnable onCancel) {
        this.quizController = quizController;
        this.onCancel = onCancel;
    }

    public void show(Stage primaryStage) {
        show(primaryStage, null);
    }

    public void show(Stage primaryStage, Quiz quizToEdit) {
        VBox root = new VBox(18);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8fafc, #dbeafe);");

        boolean isEdit = quizToEdit != null;
        Label titleLabel = new Label(isEdit ? "Edit Quiz" : "Create a New Quiz");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1e293b; -fx-padding: 0 0 10 0;");
        TextField titleField = new TextField();
        titleField.setPromptText("Quiz Title");
        titleField.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-font-size: 15px; -fx-padding: 8;");

        VBox questionsBox = new VBox(12);
        questionsBox.setAlignment(Pos.CENTER_LEFT);
        Button addQuestionBtn = new Button("Add Question");
        addQuestionBtn.setStyle("-fx-background-color: #38bdf8; -fx-text-fill: #1e293b; -fx-background-radius: 8; -fx-font-size: 15px; -fx-padding: 8 24 8 24;");

        List<QuestionForm> questionForms = new ArrayList<>();

        if (isEdit) {
            titleField.setText(quizToEdit.getTitle());
            for (Question q : quizToEdit.getQuestions()) {
                QuestionForm qf = new QuestionForm();
                qf.setQuestion(q);
                questionsBox.getChildren().add(qf.getPane());
                questionForms.add(qf);
            }
        }

        addQuestionBtn.setOnAction(e -> {
            QuestionForm qf = new QuestionForm();
            questionsBox.getChildren().add(qf.getPane());
            questionForms.add(qf);
        });

        Button saveBtn = new Button(isEdit ? "Save Changes" : "Save Quiz");
        saveBtn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 15px; -fx-padding: 8 24 8 24;");
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: #e5e7eb; -fx-text-fill: #111827; -fx-background-radius: 8; -fx-font-size: 15px; -fx-padding: 8 24 8 24;");

        saveBtn.setOnAction(e -> {
            String title = titleField.getText().trim();
            List<Question> questions = new ArrayList<>();
            for (QuestionForm qf : questionForms) {
                Question q = qf.toQuestion();
                if (q != null) questions.add(q);
            }
            if (title.isEmpty() || questions.isEmpty()) {
                showAlert("Quiz must have a title and at least one question.");
                return;
            }
            Quiz quiz = new Quiz(title, questions, new Date());
            if (isEdit) {
                quizController.updateQuiz(quizToEdit, quiz); // update logic to be implemented
            } else {
                quizController.createQuiz(title, questions);
            }
            onCancel.run(); // After save, go back to previous screen
        });
        cancelBtn.setOnAction(e -> onCancel.run());

        HBox buttonBox = new HBox(15, saveBtn, cancelBtn);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel, titleField, new Label("Questions:"), questionsBox, addQuestionBtn, buttonBox);

        primaryStage.setTitle(isEdit ? "Edit Quiz" : "Create Quiz");
        if (primaryStage.getScene() == null) {
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(root);
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    // Helper class for question form
    private static class QuestionForm {
        private final TextField questionField = new TextField();
        private final List<TextField> optionFields = new ArrayList<>();
        private final ToggleGroup correctGroup = new ToggleGroup();
        private final VBox pane = new VBox(7);

        public QuestionForm() {
            questionField.setPromptText("Question text");
            questionField.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-font-size: 14px; -fx-padding: 6;");
            pane.getChildren().add(questionField);
            for (int i = 0; i < 4; i++) {
                HBox hbox = new HBox(7);
                RadioButton rb = new RadioButton();
                rb.setToggleGroup(correctGroup);
                TextField opt = new TextField();
                opt.setPromptText("Option " + (i + 1));
                opt.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-font-size: 14px; -fx-padding: 6;");
                optionFields.add(opt);
                hbox.getChildren().addAll(rb, opt);
                pane.getChildren().add(hbox);
            }
            pane.setStyle("-fx-background-color: #f1f5f9; -fx-background-radius: 8; -fx-padding: 10; -fx-border-color: #cbd5e1; -fx-border-radius: 8; -fx-border-width: 1;");
        }

        public VBox getPane() { return pane; }

        public void setQuestion(Question q) {
            questionField.setText(q.getQuestionText());
            for (int i = 0; i < optionFields.size(); i++) {
                optionFields.get(i).setText(q.getAnswerOptions().get(i));
                RadioButton rb = (RadioButton)((HBox)pane.getChildren().get(i+1)).getChildren().get(0);
                rb.setSelected(i == q.getCorrectAnswerIndex());
            }
        }

        public Question toQuestion() {
            String qText = questionField.getText().trim();
            List<String> opts = new ArrayList<>();
            int correctIdx = -1;
            for (int i = 0; i < optionFields.size(); i++) {
                String opt = optionFields.get(i).getText().trim();
                opts.add(opt);
                if (((RadioButton)((HBox)pane.getChildren().get(i+1)).getChildren().get(0)).isSelected()) {
                    correctIdx = i;
                }
            }
            if (qText.isEmpty() || opts.stream().anyMatch(String::isEmpty) || correctIdx == -1) return null;
            return new Question(qText, opts, correctIdx);
        }
    }
} 