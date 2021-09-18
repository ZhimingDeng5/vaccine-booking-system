package com.example.Flying_Tiger;

public class Question {
    private long ID;
    private String questionContent;
    private boolean answer;
    public Question(long ID, String questionContent, boolean answer){
        this.ID = ID;
        this.questionContent = questionContent;
        this.answer = answer;
    }
    public Question(long ID, String questionContent){
        this.ID = ID;
        this.questionContent = questionContent;
    }
    public String getQuestionContent() {return questionContent; }
}
