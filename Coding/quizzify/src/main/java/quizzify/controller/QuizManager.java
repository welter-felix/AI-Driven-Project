package quizzify.controller;

import quizzify.service.StorageService;
import quizzify.model.Quiz;
import java.util.List;

public class QuizManager {
    private final StorageService storageService;

    public QuizManager(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<Quiz> listQuizzes() {
        // Return list of quizzes
        return null;
    }

    // Methods to save/load quizzes
} 