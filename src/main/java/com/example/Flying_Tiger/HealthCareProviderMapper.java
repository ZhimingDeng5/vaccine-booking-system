package com.example.Flying_Tiger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthCareProviderMapper extends UserMapper{
    public HealthCareProviderMapper(){
        super("healthcareprovider");
    }
    @Override
    public HealthCareProvider find(long id) throws SQLException {
        ResultSet rs = this.findRow(id);
        String password;
        String name;
        String type;
        int postcode;
        Timeslot[] timeslots;
        HealthCareProvider result;
        try {
            if (rs.next()) {
                password = rs.getString("password");
                name = rs.getString("name");
                type = rs.getString("hcpType");
                postcode = rs.getInt("postcode");
                timeslots = loadTimeslots(id);
                result = new HealthCareProvider(id, password, name, type, postcode, timeslots);
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Timeslot[] loadTimeslots(long hcpID) throws SQLException {
        DBConn dbc=new DBConn();
        dbc.openDB();
        String associationTable = "timeslot_healthcareprovider";
        String query =  "SELECT \"hcpID\" , \"timeslotID\"  FROM " + associationTable + " WHERE \"hcpID\" = ?";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, hcpID);
        ResultSet rs = myStmt.executeQuery();
        try {
            int size = 0;
            while(rs.next()){
                size++;
            }
            rs = myStmt.executeQuery();
            Timeslot[] timeslots= new Timeslot[size];
            TimeslotMapper timeslotMapper = new TimeslotMapper();
            for(int i =0; i< size; i++){
                rs.next();
                timeslots[i] = timeslotMapper.find(rs.getLong("timeslotID"));
            }
            return timeslots;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Timeslot[0];
    }

    /**
     * given health care provider name, return the ID
     * @param name
     * @return ID
     * @throws SQLException
     */
    public long getIDByName(String name) throws SQLException {
        DBConn dbc=new DBConn();
        dbc.openDB();
        // find id with name
        String query = "SELECT \"ID\" FROM " + this.table + " WHERE \"name\" = ?";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setString(1, name);
        ResultSet rs = myStmt.executeQuery();
        if(rs.next()){
            return rs.getLong("ID");
        }
        return 0;
    }


    public void update(HealthCareProvider healthCareProvider) throws SQLException {
        DBConn dbc=new DBConn();
        dbc.openDB();
        // update
        String query = "UPDATE " + this.table + " set \"password\" = ?, \"hcpType\"=?, \"name\" = ?, \"postcode\"=?  WHERE  \"ID\" = ?; ";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setString(1, healthCareProvider.getPassword());
        myStmt.setString(2, healthCareProvider.getType());
        myStmt.setString(3, healthCareProvider.getName());
        myStmt.setInt(4, healthCareProvider.getPost());
        myStmt.setLong(5, healthCareProvider.getID());
        myStmt.executeUpdate();
    }

}


