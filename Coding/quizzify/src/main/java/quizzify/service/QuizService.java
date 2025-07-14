// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.service;

import quizzify.model.Quiz;
import quizzify.model.QuizResult;
import quizzify.model.Question;
import java.util.List;
import java.util.ArrayList;

public class QuizService {
    // Methods for quiz validation, scoring, and session management

    public QuizResult calculateResult(Quiz quiz, int[] userAnswers) {
        List<Question> questions = quiz.getQuestions();
        int score = 0;
        List<Integer> userAnsList = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            int userAns = (i < userAnswers.length) ? userAnswers[i] : -1;
            userAnsList.add(userAns);
            if (userAns == questions.get(i).getCorrectAnswerIndex()) {
                score++;
            }
        }
        double percentage = questions.isEmpty() ? 0 : (score * 100.0 / questions.size());
        return new QuizResult(quiz, userAnsList, score, percentage);
    }
} 