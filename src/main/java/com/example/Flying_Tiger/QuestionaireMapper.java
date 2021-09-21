package com.example.Flying_Tiger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * pattern: embedded value
 */
public class QuestionaireMapper extends Mapper {
    public QuestionaireMapper(){
        super("questionaire");
    }
    public Questionaire find(long id) throws SQLException {
        // get the timeslot information from timeslot table
        ResultSet rs = super.findRow(id);
        try {
            rs.next();
            // look up the recipient and calculate the age of the recipient
            long recID = rs.getLong("recID");

            RecipientMapper recipientMapper = new RecipientMapper();
            Recipient recipient = recipientMapper.find(recID);
            int age = recipientMapper.calculateAge(recID);

            // get the questions information
            QuestionMapper questionMapper = new QuestionMapper();
            Question[] questions = questionMapper.findForQuestionaire(id);
            Questionaire result = new Questionaire(id, recipient, age, questions);
            return result;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return new Questionaire(id, null, 0, new Question[0]);

    }
}
