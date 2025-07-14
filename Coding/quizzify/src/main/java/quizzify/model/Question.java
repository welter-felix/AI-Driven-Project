package quizzify.model;

import java.util.List;

public class Question {
    private String questionText;
    private List<String> answerOptions;
    private int correctAnswerIndex;

    public Question(String questionText, List<String> answerOptions, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public List<String> getAnswerOptions() { return answerOptions; }
    public void setAnswerOptions(List<String> answerOptions) { this.answerOptions = answerOptions; }

    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    public void setCorrectAnswerIndex(int correctAnswerIndex) { this.correctAnswerIndex = correctAnswerIndex; }
} 