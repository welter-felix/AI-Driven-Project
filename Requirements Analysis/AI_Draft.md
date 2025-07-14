### Prompt : I’m building a Java application called Quizzify. The idea is to let users create and play custom multiple-choice quizzes. Each quiz has a title and a set of questions. Each question has a correct answer and multiple wrong choices. Users can take quizzes one question at a time and get a score at the end. 
Can you generate detailed user stories, acceptance criteria, and a functional requirements list from the description?



# Quizzify (AI Draft)

## App Description

Quizzify is a Java Desktop application that lets users create and play custom quizzes, as well as premade ones. Each quiz can have multiple questions with multiple-choice answers. Users can take quizzes, receive immediate feedback, and track their results.

## User Stories

### Quiz Creation
As a user, I want to create a quiz by entering a title and adding multiple questions with answer choices, so I can build personalized quizzes.

**Acceptance Criteria:**
- I can enter a quiz title (required field)
- I can add multiple questions to a quiz
- Each question must have a question text (required field)
- Each question must have exactly one correct answer and at least 2 incorrect options
- I can edit or delete questions before saving the quiz
- I can save the quiz with a unique name
- I can cancel quiz creation and return to the main menu

### Quiz Taking
As a user, I want to take a quiz and select answers to questions one by one, so I can test my knowledge.

**Acceptance Criteria:**
- I can view a list of available quizzes (both custom and premade)
- I can select a quiz to take
- Only one question is displayed at a time
- I can see the current question number and total questions
- I can select only one answer per question
- I can navigate between questions (if allowed by quiz settings)
- I can submit the quiz when all questions are answered

### Quiz Results
As a user, I want to see my final score and which questions I got right or wrong after submitting a quiz, so I can review my performance.

**Acceptance Criteria:**
- I see a summary page showing:
  - Total score (e.g., "8 out of 10 correct")
  - Percentage score (e.g., "80%")
  - List of questions with correct/incorrect indicators
  - My selected answers vs. correct answers
- I can review each question and see the correct answer
- I can return to the main menu after viewing results

### Quiz Management
As a user, I want to manage my created quizzes so I can organize and reuse them.

**Acceptance Criteria:**
- I can view a list of all quizzes I've created
- I can edit existing quizzes (title, questions, answers)
- I can delete quizzes I no longer need
- I can duplicate existing quizzes as a starting point for new ones

### Premade Quizzes
As a user, I want to access premade quizzes so I can test my knowledge on various topics.

**Acceptance Criteria:**
- I can see a separate section for premade quizzes
- Premade quizzes are organized by categories (e.g., Science, History, Literature)
- I can filter premade quizzes by category
- I can take premade quizzes without modification

## Functional Requirements

### Core Features
1. **Quiz Creation System**
   - Create new quizzes with custom titles
   - Add multiple questions per quiz
   - Set one correct answer and multiple incorrect options per question
   - Validate quiz data (minimum questions, required fields)
   - Save quizzes to local storage

2. **Quiz Taking System**
   - Display quizzes one question at a time
   - Track user progress through the quiz
   - Validate that all questions are answered before submission
   - Calculate and display final scores

3. **Results and Feedback System**
   - Calculate percentage scores
   - Show correct/incorrect answers
   - Display detailed results with question-by-question breakdown
   - Provide immediate feedback on performance

4. **Quiz Management System**
   - List all created quizzes
   - Edit existing quizzes
   - Delete unwanted quizzes
   - Duplicate quizzes for modification

5. **Premade Quiz System**
   - Include a library of premade quizzes
   - Organize premade quizzes by categories
   - Allow filtering and searching of premade quizzes

### User Interface Requirements
1. **Main Menu**
   - Clear navigation options
   - Separate sections for custom and premade quizzes
   - Easy access to quiz management

2. **Quiz Creation Interface**
   - Intuitive form for entering quiz details
   - Dynamic question addition/removal
   - Real-time validation feedback
   - Preview functionality

3. **Quiz Taking Interface**
   - Clean, distraction-free question display
   - Clear answer selection mechanism
   - Progress indicator
   - Navigation controls

4. **Results Interface**
   - Comprehensive score summary
   - Detailed question review
   - Clear correct/incorrect indicators
   - Easy navigation back to main menu

### Technical Requirements
1. **Data Storage**
   - Local file-based storage for custom quizzes
   - Persistent user data across sessions
   - Backup and restore functionality

2. **Performance**
   - Fast application startup
   - Responsive user interface
   - Smooth navigation between screens

3. **Error Handling**
   - Graceful handling of invalid data
   - User-friendly error messages
   - Data validation and recovery

4. **Accessibility**
   - Keyboard navigation support
   - Clear visual feedback
   - Readable font sizes and contrast

## Non-Functional Requirements

### Usability
- Intuitive user interface requiring minimal training
- Consistent design patterns throughout the application
- Clear and helpful error messages
- Responsive design that works on different screen sizes

### Reliability
- Application should not crash during normal operation
- Data should be saved automatically to prevent loss
- Graceful handling of unexpected input or system errors

### Performance
- Application should start within 3 seconds
- UI interactions should respond within 1 second
- Quiz loading should be nearly instantaneous

### Maintainability
- Well-structured, documented code
- Modular design for easy feature additions
- Clear separation of concerns between UI and business logic