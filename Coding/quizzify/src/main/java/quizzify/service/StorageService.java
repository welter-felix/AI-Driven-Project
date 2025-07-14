// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.service;

import quizzify.model.Quiz;
import quizzify.util.FileUtils;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.text.SimpleDateFormat;


public class StorageService {
    private static final String QUIZ_DIR = "quizzes";

    //manually edited
    public void saveQuiz(Quiz quiz) {
        try {
            File dir = new File(QUIZ_DIR);
            if (!dir.exists()) dir.mkdirs();

            String sanitizedTitle = quiz.getTitle().replaceAll("[^a-zA-Z0-9-_]", "_");
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(quiz.getCreatedDate());
            String fileName = sanitizedTitle + "_" + timestamp + ".json";
            String filePath = QUIZ_DIR + File.separator + fileName;
            FileUtils.writeJsonToFile(quiz, filePath);
            System.out.println("Quiz saved to: " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to save quiz: " + e.getMessage());
        }
    }

    public List<Quiz> loadQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        File dir = new File(QUIZ_DIR);
        if (!dir.exists() || !dir.isDirectory()) return quizzes;
        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                try {
                    Quiz quiz = FileUtils.readJsonFromFile(file.getAbsolutePath(), Quiz.class);
                    quizzes.add(quiz);
                } catch (Exception e) {
                    System.out.println("Failed to load quiz from " + file.getName() + ": " + e.getMessage());
                }
            }
        }
        return quizzes;
    }
} 