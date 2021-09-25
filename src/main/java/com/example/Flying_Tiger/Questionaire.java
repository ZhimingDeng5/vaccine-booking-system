package com.example.Flying_Tiger;

import java.sql.SQLException;

public class Questionaire {
    private long ID;
    private String vacType;
    private long hcpID;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    public Questionaire(long ID, String vacType, long hcpID, String q1, String q2, String q3, String q4, String q5){
        this.ID = ID;
        this.vacType = vacType;
        this.hcpID = hcpID;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;

    }
    public String[] getQuestions() throws SQLException {
        if(this.q1 == null || this.q2 == null || this.q3 == null || this.q4 == null || this.q5 == null){
            load();
        }
        String[] questions = {this.q1, this.q2, this.q3, this.q4, this.q5};
        return questions;
    }

    public long getHcpID() {
        return hcpID;
    }

    public long getID() {
        return ID;
    }

    public String getVacType() {
        return vacType;
    }

    public String getQ1(){return q1;}
    public String getQ2(){return q2;}
    public String getQ3(){return q3;}
    public String getQ4(){return q4;}
    public String getQ5(){return q5;}

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public void setQ5(String q5) {
        this.q5 = q5;
    }

    /**
     * lazy load
     * @throws SQLException
     */
    void load() throws SQLException {
        Questionaire q = this.getMapper().find(this.ID);
        if(this.q1 == null) this.q1 = q.getQ1();
        if(this.q2 == null) this.q2 = q.getQ2();
        if(this.q3 == null) this.q3 = q.getQ3();
        if(this.q4 == null) this.q4 = q.getQ4();
        if(this.q5 == null) this.q5 = q.getQ5();
    }
    public static QuestionaireMapper getMapper()
    {
        return new QuestionaireMapper();
    }
}
