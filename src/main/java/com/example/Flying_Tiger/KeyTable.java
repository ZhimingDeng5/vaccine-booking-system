package com.example.Flying_Tiger;
import java.util.*;
import java.sql.*;
/**
 * Identity field pattern
 * Connect to the PostgreSQL database and keep track or table-unique keys
 * return a new next key
 */
public class KeyTable {
    public long getKey(String tableName){
        DBConn dbc=new DBConn();
        dbc.openDB();
        // get the next key from the table
        String query = "SELECT * FROM keys WHERE \"table\" = '" + tableName +"'";
        ResultSet rs = dbc.execQuery(query);
        long result = 0;
        try {
            while(rs.next()){
                result = rs.getLong("nextID");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //update the table with the next key
        long nextKey = result + 1;
        String update = "UPDATE keys SET \"nextID\" = " + nextKey + " WHERE \"table\" = '" + tableName +"'";
        return  nextKey;



    }



}
