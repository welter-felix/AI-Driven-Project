package quizzify.controller;

import quizzify.service.QuizService;

public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Methods to handle quiz creation and taking
} 