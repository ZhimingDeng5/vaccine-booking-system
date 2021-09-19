package com.example.Flying_Tiger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorMapper extends UserMapper{
    public AdministratorMapper(String table){
        super(table);
    }

    @Override
    public Administrator find(long id) {
        ResultSet rs = this.findRow(id);
        String password;
        String name;
        Administrator result;


            try {
                if (rs.next()) {
                    password = rs.getString("password");
                    name = rs.getString("name");
                    result = new Administrator(id, password, name);
                    return result;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
}
