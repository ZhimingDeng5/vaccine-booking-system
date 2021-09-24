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
            if(rs.next()) {
                // look up the recipient and calculate the age of the recipient
                long hcpID = rs.getLong("hcpID");
                String vacType = rs.getString("vacType");
                String q1 = rs.getString("q1");
                String q2 = rs.getString("q2");
                String q3 = rs.getString("q3");
                String q4 = rs.getString("q4");
                String q5 = rs.getString("q5");


                Questionaire result = new Questionaire(id, vacType, hcpID, q1, q2, q3, q4, q5);
                return result;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return new Questionaire(id, null, 0, null,null,null,null,null);

    }
}
