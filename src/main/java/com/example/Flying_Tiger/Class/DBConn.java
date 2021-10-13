package com.example.Flying_Tiger.Class;
import java.sql.*;
import java.sql.Connection;

public class DBConn {
    private final String url = "jdbc:postgresql://ec2-52-45-238-24.compute-1.amazonaws.com:5432/dccothu1bdft1b";
    private final String user = "dsjwzngeawuwgg";
    private final String password = "303fe3367d9d5b0afbed2710251d75d64515baa57f5e134a90f2ed1ac30fd478";
    /**
     * Connect to the PostgreSQL database
     * @return a Connection object
     */
    Connection conn;
    Statement stmt;
    ResultSet rs;
    public DBConn()
    {
        openDB();
    }
    public void openDB(){
        if(conn==null) {
            try {
                //String url="jdbc:postgresql://localhost:5432/myDB";
                DriverManager.registerDriver(new org.postgresql.Driver());
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public void connect() {
        if(conn==null) {
            try {
                DriverManager.registerDriver(new org.postgresql.Driver());
                conn = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //  查询
    public ResultSet execQuery(String sql){
        try {

            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }
    // prepared statement
    public PreparedStatement setPreparedStatement(String sql) throws SQLException {
        PreparedStatement myStmt = conn.prepareStatement(sql);
        return myStmt;
    }

    //增删改
    public int execUpdate(String sql){
        int n=0;
        try {
            stmt=conn.createStatement();
            n=stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return n;
    }
    public void closeDB(){
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
//    public void connect() {
//        String sql = "SELECT * FROM users;";
//        PreparedStatement findStatement = null;
//        ResultSet rs = null;
//        Connection conn = null;
//        try {
//            DriverManager.registerDriver(new org.postgresql.Driver());
//            conn = DriverManager.getConnection(url, user, password);
//            findStatement = conn.prepareStatement(sql);
//            findStatement.execute();
//            rs = findStatement.getResultSet();
//            rs.next();
//            String username = rs.getString(1);
//            System.out.println(username);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


}
