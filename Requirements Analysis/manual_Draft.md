# Quizzify (Manual Draft)

## App Description

Quizzify is a Java Desktop application that enables users to create, manage, and participate in custome or predefined multiple-choice quizzes. Each quiz belongs to a category, may include a time limit, and supports real-time feedback after completion. Users can sign up and log in to track their performance statistics over time. The application offers quiz filtering based on categories and stores historical results for review.

## User Stories

### Quiz Creation
- As a user, I want to create a new quiz by entering a title, selecting a category, and adding multiple questions with answer choices, so that I can build personalized quizzes.
- As a user, I want to define a time limit when creating a quiz, so that I can control how much time I have per question.

### Quiz Participation

- As a user, I want to take a quiz and answer questions one at a time, so that I can focus on each question without distraction.
- As a user, I want to receive immediate feedback after submitting a quiz, so that I can understand my mistakes and improve.


### Result Tracking 
- As a user, I want to view a summary of my quiz results after submission, so that I can see my score and which answers I got wrong.

- As a logged-in user, I want to access my historical quiz results, so that I can monitor my progress over time.

### Authentication

- As a new user, I want to register an account, so that I can save my progress and access my statistics later.

- As a returning user, I want to log in to my account, so that I can access my personalized data and saved results.

## Acceptance Criteria

#### A quiz must contain:

- A title
- A selected category
- At least one question

#### Each question must:

- Contain one correct answer and at least two incorrect options
- Be presented individually during the quiz

#### During quiz-taking:

- The user cannot revisit previous questions (optional, depending on your logic)

- If a timer is set, it must start at start of each question and either skip the question or exit the quiz when time runs out

#### After quiz completion, the summary must display:

- Total score (number of correct answers)

- Percentage score

- A breakdown of correct vs. incorrect responses

#### Only authenticated users can access historical statistics