package com.example.Flying_Tiger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper extends Mapper{
    public QuestionMapper(){
        super("question");
    }
    /**
     * foreign key mapping
     * @param questionaireID
     * @return Question[]
     */
    public Question[] findForQuestionaire(long questionaireID){
        DBConn dbc=new DBConn();
        dbc.openDB();
        // get the row with this id
        String query = "SELECT * FROM " + super.table + " WHERE \"questionaireID\" = "+questionaireID;
        ResultSet rs = dbc.execQuery(query);
        try {
            int size = 0;
            while(rs.next()){
                size++;
            }
            rs = dbc.execQuery(query);
            Question[] questions= new Question[size];
            for(int i =0; i< size; i++){
                rs.next();
                questions[i] = new Question(rs.getLong("ID"), rs.getString("questionContent"),rs.getBoolean("answer"));
            }
            return questions;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Question[0];
    }
}
