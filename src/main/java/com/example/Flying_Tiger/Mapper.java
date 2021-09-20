package com.example.Flying_Tiger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * table inheritance
 */
public class Mapper {
    protected String table;
    public Mapper(String table){
        this.table = table;
    }
    // Find rows from table
    public ResultSet findRow(long id) throws SQLException {
        DBConn dbc=new DBConn();
        dbc.openDB();
        // get the row with this id
        String query = "SELECT * FROM " + this.table + " WHERE \"ID\" = ?";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, id);
        ResultSet rs = myStmt.executeQuery();
        return rs;

    }

    public int getnum()
    {
        DBConn dbc=new DBConn();
        dbc.openDB();
        // get the row with this id
        String query = "SELECT COUNT (*) FROM " + this.table;
        ResultSet rs = dbc.execQuery(query);
        try {
            if (rs.next()) {
                return rs.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
