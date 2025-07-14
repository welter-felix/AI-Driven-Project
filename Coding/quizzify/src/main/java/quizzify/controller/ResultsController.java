// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.controller;

import quizzify.model.Quiz;
import quizzify.model.QuizResult;
import quizzify.service.QuizService;

public class ResultsController {
    private final QuizService quizService;

    public ResultsController(QuizService quizService) {
        this.quizService = quizService;
    }

    public QuizResult calculateResult(Quiz quiz, int[] userAnswers) {
        return quizService.calculateResult(quiz, userAnswers);
    }
} 