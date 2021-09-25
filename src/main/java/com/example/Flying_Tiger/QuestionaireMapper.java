package com.example.Flying_Tiger;

import java.sql.PreparedStatement;
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
        // find questionaire by id
        ResultSet rs = super.findRow(id);
        try {
            if(rs.next()) {
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
    public void insert(long id, String type, long hcpid, String q1,String q2,String q3,
                       String q4,String q5) throws SQLException {
        dbc.openDB();
        String query = "INSERT INTO public." + super.table +
                "(\"ID\", \"vacType\", \"hcpID\", q1, q2,q3,q4,q5) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, id);
        myStmt.setString(2, type);
        myStmt.setLong(3, hcpid);
        myStmt.setString(4, q1);
        myStmt.setString(5, q2);
        myStmt.setString(6, q3);
        myStmt.setString(7, q4);
        myStmt.setString(8, q5);
        myStmt.executeUpdate();
    }
}
