package com.example.Flying_Tiger.Mapper;

import com.example.Flying_Tiger.Class.Administrator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorMapper extends UserMapper{
    public AdministratorMapper(){
        super("administrator");
    }

    @Override
    public Administrator find(long id) throws SQLException {
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

    public void update(Administrator administrator) throws SQLException {
        dbc.openDB();
        // update
        String query = "UPDATE " + this.table + " set \"password\" = ?, \"name\" = ?  WHERE  \"ID\" = ?; ";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setString(1, administrator.getPassword());
        myStmt.setString(2, administrator.getName());
        myStmt.setLong(3, administrator.getID());
        myStmt.executeUpdate();
    }


}
