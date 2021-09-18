package com.example.Flying_Tiger;
import java.sql.*;

public class JDBCtest {

    public static void main(String[] args) {
//        RecipientMapper rm = new RecipientMapper("recipient");
//        Recipient[] rcs = rm.findForTimeslot(2021091808);
//        System.out.println(rcs[0].getBirth());
//        TimeslotMapper tm = new TimeslotMapper("timeslot");
//        Timeslot ts = tm.find(2021091808);
//        System.out.println(ts.getTimeslotID());
//        System.out.println(ts.getDate());
//        System.out.println(ts.getTime());
//        System.out.println(ts.getRecipients()[0].getID());
//        HealthCareProviderMapper healthCareProviderMapper= new HealthCareProviderMapper("healthcareprovider");
//        HealthCareProvider healthCareProvider = healthCareProviderMapper.find(200000000);
//        System.out.println(healthCareProvider.getTimeslots()[0].getTimeslotID());
//        QuestionMapper qm = new QuestionMapper("question");
//        Question[] questions = qm.findForQuestionaire(1000);
//        System.out.println(questions[0].getQuestionContent());

        QuestionaireMapper qnm = new QuestionaireMapper("questionaire");
        Questionaire questionaire = qnm.find(1000);
        System.out.println(questionaire.getAge());

    }
}
