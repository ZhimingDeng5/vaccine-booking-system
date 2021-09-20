package com.example.Flying_Tiger;

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
    public ResultSet findRow(long id){
        DBConn dbc=new DBConn();
        dbc.openDB();
        // get the row with this id
        String query = "SELECT * FROM " + this.table + " WHERE \"ID\" = "+id;
        ResultSet rs = dbc.execQuery(query);
        return rs;

    }


}
