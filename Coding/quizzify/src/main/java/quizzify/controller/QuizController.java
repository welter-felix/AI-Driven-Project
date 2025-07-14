// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.controller;

import quizzify.model.Quiz;
import quizzify.model.Question;
import quizzify.model.QuizResult;
import java.util.List;

public class QuizController {
    private final QuizManager quizManager;
    private final ResultsController resultsController;

    public QuizController(QuizManager quizManager, ResultsController resultsController) {
        this.quizManager = quizManager;
        this.resultsController = resultsController;
    }

    public void createQuiz(String title, List<Question> questions) {
        quizManager.saveQuiz(new Quiz(title, questions, new java.util.Date()));
    }

    public List<Quiz> getAvailableQuizzes() {
        return quizManager.loadQuizzes();
    }

    public Quiz getQuizByIndex(int index) {
        List<Quiz> quizzes = getAvailableQuizzes();
        if (index >= 0 && index < quizzes.size()) {
            return quizzes.get(index);
        }
        return null;
    }

    public QuizResult takeQuiz(Quiz quiz, int[] userAnswers) {
        return resultsController.calculateResult(quiz, userAnswers);
    }

    public void updateQuiz(Quiz oldQuiz, Quiz newQuiz) {
        quizManager.updateQuiz(oldQuiz, newQuiz);
    }

    public void deleteQuiz(Quiz quiz) {
        quizManager.deleteQuiz(quiz);
    }
} 