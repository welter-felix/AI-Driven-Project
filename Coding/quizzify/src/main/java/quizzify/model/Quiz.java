package quizzify.model;

import java.util.List;
import java.util.Date;

public class Quiz {
    private String title;
    private List<Question> questions;
    private Date createdDate;

    public Quiz(String title, List<Question> questions, Date createdDate) {
        this.title = title;
        this.questions = questions;
        this.createdDate = createdDate;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
} 