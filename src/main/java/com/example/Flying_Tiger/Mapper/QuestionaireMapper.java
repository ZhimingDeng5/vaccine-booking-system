package com.example.Flying_Tiger.mapper;

import com.example.Flying_Tiger.Class.Questionaire;

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
    private static QuestionaireMapper instance=new QuestionaireMapper();
    public static QuestionaireMapper getInstance(){return instance;};
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
                int minAge=rs.getInt("minAge");
                int maxAge=rs.getInt("maxAge");

                Questionaire result = new Questionaire(id, vacType, hcpID, minAge,maxAge,q1, q2, q3, q4, q5);
                return result;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
    public Questionaire find(long hcpid,String type) throws SQLException {
        // find questionaire by id
        dbc.openDB();
        // update
        String query = "SELECT * FROM " + this.table + " WHERE \"hcpID\" = ? AND \"vacType\" = ?; ";
        PreparedStatement myStmt = null;
        try {
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1, hcpid);
            myStmt.setString(2, type);
            ResultSet rs = myStmt.executeQuery();
            if(rs.next()) {
                long ID = rs.getLong("ID");
                String vacType = rs.getString("vacType");
                String q1 = rs.getString("q1");
                String q2 = rs.getString("q2");
                String q3 = rs.getString("q3");
                String q4 = rs.getString("q4");
                String q5 = rs.getString("q5");
                int minAge=rs.getInt("minAge");
                int maxAge=rs.getInt("maxAge");
                Questionaire result = new Questionaire(ID, vacType, hcpid,minAge,maxAge, q1, q2, q3, q4, q5);
                return result;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
    public int[] findminmax(long id) throws SQLException {
        // find questionaire by id
        ResultSet rs = super.findRow(id);
        try {
            if(rs.next()) {
                int minAge=rs.getInt("minAge");
                int maxAge=rs.getInt("maxAge");
                int[] minmax= {minAge,maxAge};
                return minmax;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return new int[0];

    }
    public Questionaire[] findall(long hcpid)
    {
        String query = "SELECT * FROM " + super.table+" Where \"hcpID\" =?";
        try {
            PreparedStatement myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1,hcpid);
            ResultSet rs = myStmt.executeQuery();
            int size = 0;
            while(rs.next()){
                size++;
            }
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1,hcpid);
            rs = myStmt.executeQuery();
            Questionaire[] questionaires= new Questionaire[size];
            for(int i =0; i< size; i++){
                rs.next();
                questionaires[i] = new Questionaire(rs.getLong("ID"), rs.getString("vacType"),
                        rs.getLong("hcpID"), rs.getInt("minAge"),rs.getInt("maxAge"),
                        rs.getString("q1"),rs.getString("q2"),
                        rs.getString("q3"),rs.getString("q4"),rs.getString("q5"));
            }
            return questionaires;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Questionaire[0];
    }
    public String insert(long id, String type, long hcpid,int minAge,int maxAge, String q1,String q2,String q3,
                       String q4,String q5) throws SQLException {
        dbc.openDB();
        String query0="SELECT \"ID\"  From vaccine Where \"vacType\" =? " ;
        PreparedStatement myStmt0 = dbc.setPreparedStatement(query0);
        myStmt0.setString(1,type);
        ResultSet rs= myStmt0.executeQuery();
        if(rs.next()== false){
            return ("The vaccine type has been changed!");
        }
        String query = "INSERT INTO public." + super.table +
                "(\"ID\", \"vacType\", \"hcpID\", q1, q2,q3,q4,q5,\"minAge\",\"maxAge\") VALUES (?, ?, ?,?,?,?, ?, ?, ?, ?)";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, id);
        myStmt.setString(2, type);
        myStmt.setLong(3, hcpid);
        myStmt.setString(4, q1);
        myStmt.setString(5, q2);
        myStmt.setString(6, q3);
        myStmt.setString(7, q4);
        myStmt.setString(8, q5);
        myStmt.setInt(9,minAge);
        myStmt.setInt(10,maxAge);
        myStmt.executeUpdate();
        return ("add successfully");
    }


    public void delete(long id) {

        String query = "DELETE FROM public."+this.table+" WHERE \"ID\"=? " ;
        try {
            PreparedStatement myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1,id);
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Questionaire questionaire) throws SQLException {
        dbc.openDB();
        // update
        String query = "UPDATE " + this.table + " set \"ID\" = ?, \"vacType\" = ?, \"hcpID\"=?, " +
                "\"q1\"=?, \"q2\"=?, \"q3\"=?, \"q4\"=?, \"q5\"=?,\"minAge\"=?,\"maxAge\"=? WHERE \"ID\" = ?; ";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, questionaire.getID());
        myStmt.setString(2, questionaire.getVacType());
        myStmt.setLong(3, questionaire.getHcpID());
        myStmt.setString(4, questionaire.getQ1());
        myStmt.setString(5, questionaire.getQ2());
        myStmt.setString(6, questionaire.getQ3());
        myStmt.setString(7, questionaire.getQ4());
        myStmt.setString(8, questionaire.getQ5());
        myStmt.setInt(9,questionaire.getMinAge());
        myStmt.setInt(10,questionaire.getMaxAge());
        myStmt.setLong(11, questionaire.getID());
        myStmt.executeUpdate();
    }
}
