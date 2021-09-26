package com.example.Flying_Tiger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * table inheritance
 */
public class UserMapper extends Mapper {
    public UserMapper(String table){
        super(table);
    }

    public User find(long id) throws SQLException {
        ResultSet rs = super.findRow(id);
        String password;
        User result;
        try {
            if (rs.next()) {
                password = rs.getString("password");
                result = new User(id, password);
                return result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


}
