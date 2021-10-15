package com.example.Flying_Tiger.Mapper;

import com.example.Flying_Tiger.Class.User;
import com.example.Flying_Tiger.Class.Vaccine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VaccineMapper extends Mapper {
    public VaccineMapper() {
        super("vaccine");
    }

    private final static VaccineMapper instance = new VaccineMapper();

    public static VaccineMapper getInstance() {
        return instance;
    }

    public Vaccine[] findall() throws SQLException{
        // get the row with this id
        String query = "SELECT * FROM " + super.table;
        ResultSet rs = dbc.execQuery(query);
        try {
            int size = 0;
            while (rs.next()) {
                size++;
            }
            rs = dbc.execQuery(query);
            Vaccine[] vaccines = new Vaccine[size];
            for (int i = 0; i < size; i++) {
                rs.next();
                vaccines[i] = new Vaccine(rs.getLong("ID"), rs.getString("vacType"),rs.getInt("version"));
            }
            return vaccines;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Vaccine[0];
    }

    public Vaccine find(long id) throws SQLException {
        ResultSet rs = super.findRow(id);
        String type;
        int version;
        User result;
        try {
            if (rs.next()) {
                id = rs.getLong("ID");
                type = rs.getString("vacType");
                version=rs.getInt("version");
                return new Vaccine(id, type,version);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void insert(Long id, String vaccine) {
        dbc.openDB();
        String query = "INSERT INTO public." + super.table +
                "(\"ID\",\"vacType\",\"version\") VALUES (?,?,?)";
        PreparedStatement myStmt = null;
        try {
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1, id);
            myStmt.setString(2, vaccine);
            myStmt.setInt(3,1);
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        dbc.openDB();
        String query = "DELETE FROM public.vaccine WHERE \"ID\"=? ";
        PreparedStatement myStmt = null;
        try {
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1, id);
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Vaccine vaccine) throws SQLException {
        dbc.openDB();
        // update
        String query =
        "UPDATE " + this.table + " set \"vacType\" = ?, \"version\"=?  WHERE  \"ID\" = ?; ";
        PreparedStatement myStmt = null;
        try {
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setString(1, vaccine.getType());
            myStmt.setInt(2, vaccine.getVersion() + 1);
            myStmt.setLong(3, vaccine.getID());
            myStmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
