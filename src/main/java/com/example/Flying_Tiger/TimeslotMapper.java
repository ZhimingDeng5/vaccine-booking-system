package com.example.Flying_Tiger;

import java.sql.*;

public class TimeslotMapper extends Mapper{
    public TimeslotMapper(){
        super("timeslot");
    }
    public Timeslot find(long id) throws SQLException {
        // get the timeslot information from timeslot table
        ResultSet rs = super.findRow(id);
        try {
            // get the recipients information
            RecipientMapper recipientMapper = new RecipientMapper();
            Recipient[] recipients = recipientMapper.findForTimeslot(id);
            if(rs.next()){
                Timeslot result = new Timeslot(id, rs.getDate("date"), rs.getTime("time"), recipients);
                return result;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return new Timeslot(id, null, null, new Recipient[0]);

    }

    /**
     * given timeslot date and timeslot time, return the ID
     * @param date, time
     * @return ID
     * @throws SQLException
     */
    public long getIDByDateTime(Date date, Time time) throws SQLException {
        dbc.openDB();
        // find id with name
        String query = "SELECT \"ID\" FROM " + this.table + " WHERE \"date\" = ? and \"time\" = ?";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setDate(1, date);
        myStmt.setTime(2, time);
        ResultSet rs = myStmt.executeQuery();
        if(rs.next()){
            return rs.getLong("ID");
        }
        return 0;
    }


    /**
     * Get the date of the row
     * @param id
     * @return date of timeslot
     */
    public Date timeslotDate(long id) throws SQLException {
        // get the timeslot information from timeslot table
        ResultSet rs = super.findRow(id);
        try {
            if(rs.next()){
                return rs.getDate("date");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the time of the row
     * @param id
     * @return time of timeslot
     */
    public Time timeslotTime(long id) throws SQLException {
        // get the timeslot information from timeslot table
        ResultSet rs = super.findRow(id);
        try {
            if(rs.next()){
                return rs.getTime("time");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
