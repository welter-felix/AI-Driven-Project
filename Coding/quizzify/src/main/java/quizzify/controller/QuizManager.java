// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.controller;

import quizzify.model.Quiz;
import quizzify.service.StorageService;
import java.util.List;
import java.io.File;
import java.text.SimpleDateFormat;

public class QuizManager {
    private final StorageService storageService;

    public QuizManager(StorageService storageService) {
        this.storageService = storageService;
    }

    public void saveQuiz(Quiz quiz) {
        storageService.saveQuiz(quiz);
    }

    public List<Quiz> loadQuizzes() {
        return storageService.loadQuizzes();
    }

    public void updateQuiz(Quiz oldQuiz, Quiz newQuiz) {
        // Delete old quiz file
        String oldFileName = getQuizFileName(oldQuiz);
        File oldFile = new File("quizzes" + File.separator + oldFileName);
        if (oldFile.exists()) {
            oldFile.delete();
        }
        // Save new quiz
        storageService.saveQuiz(newQuiz);
    }

    public void deleteQuiz(Quiz quiz) {
        String fileName = getQuizFileName(quiz);
        File file = new File("quizzes" + File.separator + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private String getQuizFileName(Quiz quiz) {
        String sanitizedTitle = quiz.getTitle().replaceAll("[^a-zA-Z0-9-_]", "_");
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(quiz.getCreatedDate());
        return sanitizedTitle + "_" + timestamp + ".json";
    }
} 