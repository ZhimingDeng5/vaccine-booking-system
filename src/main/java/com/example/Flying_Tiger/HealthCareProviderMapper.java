package com.example.Flying_Tiger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class HealthCareProviderMapper extends UserMapper{
    public HealthCareProviderMapper(String table){
        super(table);
    }
    @Override
    public HealthCareProvider find(long id) {
        ResultSet rs = this.findRow(id);
        String password;
        String name;
        String type;
        int postcode;
        Timeslot[] timeslots;
        HealthCareProvider result;

        try {
            rs.next();
            password = rs.getString("password");
            name = rs.getString("name");
            type = rs.getString("hcpType");
            postcode = rs.getInt("postcode");
            timeslots= loadTimeslots(id);
            result = new HealthCareProvider(id, password, name, type, postcode, timeslots);
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HealthCareProvider(id, "", "", "", 0, new Timeslot[0]);
    }

    public Timeslot[] loadTimeslots(long hcpID){
        DBConn dbc=new DBConn();
        dbc.openDB();
        String associationTable = "timeslot_healthcareprovider";
        String query =  "SELECT \"hcpID\" , \"timeslotID\"  FROM " + associationTable + " WHERE \"hcpID\" = "+ hcpID;
        ResultSet rs = dbc.execQuery(query);
        try {
            int size = 0;
            while(rs.next()){
                size++;
            }
            rs = dbc.execQuery(query);
            Timeslot[] timeslots= new Timeslot[size];
            TimeslotMapper timeslotMapper = new TimeslotMapper("timeslot");
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
}


