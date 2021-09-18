package com.example.Flying_Tiger;
import java.sql.*;

public class JDBCtest {
    private final String url = "jdbc:postgresql://localhost:5432/myDB";
    private final String user = "postgres";
    private final String password = "1234";
    /**
     * Connect to the PostgreSQL database
     * @return a Connection object
     */
    public void connect() {
        String sql = "SELECT * FROM users;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(url, user, password);
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            rs.next();
            String username = rs.getString(1);
            System.out.println(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
