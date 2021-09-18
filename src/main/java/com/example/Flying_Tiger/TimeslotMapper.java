package com.example.Flying_Tiger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class TimeslotMapper extends Mapper{
    public TimeslotMapper(String table){
        super(table);
    }
    public Timeslot find(long id){
        // get the timeslot information from timeslot table
        ResultSet rs = super.findRow(id);
        try {
            // get the recipients information
            RecipientMapper recipientMapper = new RecipientMapper("recipient");
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
     *
     * @param id
     * @return date of timeslot
     */
    public Date timeslotDate(long id){
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

}
