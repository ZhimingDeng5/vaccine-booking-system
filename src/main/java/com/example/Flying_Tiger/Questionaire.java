package com.example.Flying_Tiger;

import java.sql.SQLException;

public class Questionaire {
    private long ID;
    private String vacType;
    private long hcpID;
    private Question q1;
    private Question q2;
    private Question q3;
    private Question q4;
    private Question q5;
    private int minAge;
    private int maxAge;
    public Questionaire(long ID, String vacType, long hcpID,int minAge,int maxAge, String q1, String q2,
                        String q3, String q4, String q5){
        this.ID = ID;
        this.vacType = vacType;
        this.hcpID = hcpID;
        this.q1 = new Question(q1);
        this.q2 = new Question(q2);
        this.q3 = new Question(q3);
        this.q4 = new Question(q4);
        this.q5 = new Question(q5);
        this.minAge=minAge;
        this.maxAge=maxAge;
    }
    public String[] getQuestions() throws SQLException {
        if(this.q1 == null || this.q2 == null || this.q3 == null || this.q4 == null || this.q5 == null){
            load();
        }
        String[] questions = {this.q1.getQuestionContent(), this.q2.getQuestionContent(), this.q3.getQuestionContent(), this.q4.getQuestionContent(), this.q5.getQuestionContent()};
        return questions;
    }

    public long getHcpID() {
        return hcpID;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public long getID() {
        return ID;
    }

    public String getVacType() {
        return vacType;
    }

    public String getQ1() {
        if(q1!=null)
            return q1.getQuestionContent();
            else
        {
            try {
                Questionaire q = this.getMapper().find(this.ID);
                this.setQ1(q.getQ1());
                return this.q1.getQuestionContent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            return null;
    }
    public String getQ2() {
        if(q2!=null)
            return q2.getQuestionContent();
        else
        {
            try {
                Questionaire q = this.getMapper().find(this.ID);
                this.setQ2(q.getQ2());
                return this.q2.getQuestionContent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public String getQ3() {
        if(q3!=null)
            return q3.getQuestionContent();
        else
        {
            try {
                Questionaire q = this.getMapper().find(this.ID);
                this.setQ3(q.getQ3());
                return this.q3.getQuestionContent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public String getQ4() {
        if(q4!=null)
            return q4.getQuestionContent();
        else
        {
            try {
                Questionaire q = this.getMapper().find(this.ID);
                this.setQ4(q.getQ4());
                return this.q4.getQuestionContent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public String getQ5() {
        if(q5!=null)
            return q5.getQuestionContent();
        else
        {
            try {
                Questionaire q = this.getMapper().find(this.ID);
                this.setQ5(q.getQ5());
                return this.q5.getQuestionContent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void setQ1(String q1) {
        this.q1 = new Question(q1);
    }

    public void setQ2(String q2) {
        this.q2 = new Question(q2);
    }

    public void setQ3(String q3) {
        this.q3 = new Question(q3);
    }

    public void setQ4(String q4) {
        this.q4 = new Question(q4);
    }

    public void setQ5(String q5) {
        this.q5 = new Question(q5);
    }

    /**
     * lazy load
     * @throws SQLException
     */
    void load() throws SQLException {
        Questionaire q = this.getMapper().find(this.ID);
        if(this.q1 == null) this.q1 = new Question(q.getQ1());
        if(this.q2 == null) this.q2 = new Question(q.getQ2());
        if(this.q3 == null) this.q3 = new Question(q.getQ3());
        if(this.q4 == null) this.q4 = new Question(q.getQ4());
        if(this.q5 == null) this.q5 = new Question(q.getQ5());
    }
    public static QuestionaireMapper getMapper()
    {
        return QuestionaireMapper.getInstance();
    }
}
