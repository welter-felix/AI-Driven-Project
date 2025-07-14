// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.ui;

import quizzify.model.Question;
import quizzify.model.Quiz;
import quizzify.model.QuizResult;
import quizzify.service.StorageService;
import quizzify.service.QuizService;
import java.util.*;

public class QuizUI {
    private final StorageService storageService;
    private final QuizService quizService;
    private final Scanner scanner;

    public QuizUI(StorageService storageService, QuizService quizService) {
        this.storageService = storageService;
        this.quizService = quizService;
        this.scanner = new Scanner(System.in);
    }

    public void createQuiz() {
        System.out.println("\n--- Create a New Quiz ---");
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine();
        List<Question> questions = new ArrayList<>();
        while (true) {
            System.out.print("Enter question text (or leave blank to finish): ");
            String qText = scanner.nextLine();
            if (qText.trim().isEmpty()) break;
            List<String> options = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.print("Enter answer option " + (i+1) + ": ");
                options.add(scanner.nextLine());
            }
            int correctIdx = -1;
            while (correctIdx < 0 || correctIdx > 3) {
                System.out.print("Enter the number of the correct answer (1-4): ");
                try {
                    correctIdx = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    correctIdx = -1;
                }
                if (correctIdx < 0 || correctIdx > 3) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            }
            questions.add(new Question(qText, options, correctIdx));
        }
        if (questions.isEmpty()) {
            System.out.println("Quiz must have at least one question.");
            return;
        }
        Quiz quiz = new Quiz(title, questions, new Date());
        storageService.saveQuiz(quiz);
    }

    public void takeQuiz() {
        System.out.println("\n--- Take a Quiz ---");
        List<Quiz> quizzes = storageService.loadQuizzes();
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create one first.");
            return;
        }
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i+1) + ". " + quizzes.get(i).getTitle());
        }
        int choice = -1;
        while (choice < 1 || choice > quizzes.size()) {
            System.out.print("Select a quiz by number: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            if (choice < 1 || choice > quizzes.size()) {
                System.out.println("Invalid choice. Try again.");
            }
        }
        Quiz quiz = quizzes.get(choice - 1);
        List<Question> questions = quiz.getQuestions();
        int[] userAnswers = new int[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i+1) + ": " + q.getQuestionText());
            List<String> opts = q.getAnswerOptions();
            for (int j = 0; j < opts.size(); j++) {
                System.out.println((j+1) + ". " + opts.get(j));
            }
            int ans = -1;
            while (ans < 1 || ans > opts.size()) {
                System.out.print("Your answer (1-" + opts.size() + "): ");
                try {
                    ans = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    ans = -1;
                }
                if (ans < 1 || ans > opts.size()) {
                    System.out.println("Invalid input. Try again.");
                }
            }
            userAnswers[i] = ans - 1;
        }
        QuizResult result = quizService.calculateResult(quiz, userAnswers);
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Score: " + result.getScore() + "/" + questions.size());
        System.out.printf("Percentage: %.2f%%\n", result.getPercentage());
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            int userAns = result.getUserAnswers().get(i);
            String correct = q.getAnswerOptions().get(q.getCorrectAnswerIndex());
            String userStr = (userAns >= 0 && userAns < q.getAnswerOptions().size()) ? q.getAnswerOptions().get(userAns) : "No answer";
            System.out.println("Q" + (i+1) + ": " + q.getQuestionText());
            System.out.println("  Your answer: " + userStr + (userAns == q.getCorrectAnswerIndex() ? " (Correct)" : " (Incorrect)"));
            if (userAns != q.getCorrectAnswerIndex()) {
                System.out.println("  Correct answer: " + correct);
            }
        }
    }
} 