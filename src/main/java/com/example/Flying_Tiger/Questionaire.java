package com.example.Flying_Tiger;

public class Questionaire {
    private long ID;
    private Recipient recipient;
    private int age;
    private Question[] questions;
    public Questionaire(long ID, Recipient recipient, int age, Question[] questions){
        this.ID = ID;
        this.recipient = recipient;
        this.age = age;
        this.questions = questions;
    }
    public int getAge(){return age; }
}
