package quizzify.model;

import java.util.List;

public class QuizResult {
    private Quiz quiz;
    private List<Integer> userAnswers;
    private int score;
    private double percentage;

    public QuizResult(Quiz quiz, List<Integer> userAnswers, int score, double percentage) {
        this.quiz = quiz;
        this.userAnswers = userAnswers;
        this.score = score;
        this.percentage = percentage;
    }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public List<Integer> getUserAnswers() { return userAnswers; }
    public void setUserAnswers(List<Integer> userAnswers) { this.userAnswers = userAnswers; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
} 