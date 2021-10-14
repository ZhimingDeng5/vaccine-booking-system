package com.example.Flying_Tiger.Mapper;

import com.example.Flying_Tiger.Class.KeyTable;
import com.example.Flying_Tiger.Class.Recipient;
import com.example.Flying_Tiger.Class.Timeslot;
import com.example.Flying_Tiger.Mapper.Mapper;
import com.example.Flying_Tiger.Mapper.RecipientMapper;

import java.sql.*;

public class TimeslotMapper extends Mapper {
    public TimeslotMapper(){
        super("timeslot");
    }
    private final static TimeslotMapper instance=new TimeslotMapper();
    public static TimeslotMapper getInstance()
    {
        return instance;
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
    public void insert(Date date, Time time)
    {
        Long id= KeyTable.getKey("timeslot");
        try {
            insert(id,date,time);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(Long id, Date date, Time time) throws SQLException {
        dbc.openDB();
        String query = "INSERT INTO public." + super.table +
                "(\"ID\", \"date\",\"time\") VALUES (?, ?, ?)";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, id);
        myStmt.setDate(2, date);
        myStmt.setTime(3, time);
        myStmt.executeUpdate();
    }

    public void update(Timeslot timeslot) throws SQLException {
        dbc.openDB();
        // update
        String query = "UPDATE " + this.table + " set \"date\" = ?, \"time\"=?   WHERE  \"ID\" = ?; ";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setDate(1, timeslot.getDate());
        myStmt.setTime(2, timeslot.getTime());
        myStmt.setLong(3, timeslot.getTimeslotID());
        myStmt.executeUpdate();
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
    public int getNumLeft(long hcpid,long timeslotId) {
        try {
            dbc.openDB();
            String associationTable = "timeslot_healthcareprovider";
            String query = "SELECT \"numLeft\" FROM timeslot_healthcareprovider Where \"hcpID\" = ? and \"timeslotID\"=?;";
            PreparedStatement myStmt = null;
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1, hcpid);
            myStmt.setLong(2, timeslotId);
            ResultSet rs = myStmt.executeQuery();
            if (rs.next())
            {
                return rs.getInt("numLeft");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -100;
    }
    public int getVersion(long hcpid,long timeslotId) {
        try {
            dbc.openDB();
            String associationTable = "timeslot_healthcareprovider";
            String query = "SELECT \"version\" FROM timeslot_healthcareprovider Where \"hcpID\" = ? and \"timeslotID\"=?;";
            PreparedStatement myStmt = null;
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1, hcpid);
            myStmt.setLong(2, timeslotId);
            ResultSet rs = myStmt.executeQuery();
            if (rs.next())
            {
                return rs.getInt("version");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void insertTimeslotAssociation(long hcpId,long timeslotId,int numLeft)
    {
        try {
            dbc.openDB();
            String associationTable = "timeslot_healthcareprovider";
            String query = "INSERT INTO public."+associationTable+
                    "(\"timeslotID\",\"hcpID\",\"numLeft\",\"version\") VALUES (?, ?, ?, ?);";
            PreparedStatement myStmt = null;
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1, timeslotId);
            myStmt.setLong(2, hcpId);
            myStmt.setInt(3,numLeft);
            myStmt.setInt(4,1);
            myStmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteTimeslotAssociation(long hcpId,long timeslotId)
    {
        try {
            dbc.openDB();
            String associationTable = "timeslot_healthcareprovider";
            String query = "DELETE FROM public."+associationTable+" Where \"hcpID\" = ? and \"timeslotID\"=?;";
            PreparedStatement myStmt = null;
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1, hcpId);
            myStmt.setLong(2, timeslotId);
            myStmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String editTimeslotAssociation(long hcpId,long timeslotId,int numLeft,int version)
    {
        try {
            dbc.openDB();
            String associationTable = "timeslot_healthcareprovider";
            String query = "UPDATE "+associationTable+" SET \"numLeft\"=?,\"version\"=? Where \"hcpID\" = ? and \"timeslotID\"=? " +
                    "and \"version\"=?;";
            PreparedStatement myStmt = null;
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setInt(1, numLeft);
            myStmt.setInt(2,version+1);
            myStmt.setLong(3, hcpId);
            myStmt.setLong(4, timeslotId);
            myStmt.setInt(5,version);
            int rowcount= myStmt.executeUpdate();
            System.out.println(rowcount);
            if (rowcount==0)
            {
                String checkquery="SELECT version From "+associationTable+" Where \"hcpID\" = ? and \"timeslotID\"=? ";
                return throwConcurrencyException(checkquery,version,hcpId,timeslotId);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "the number of seats successfully modified!";
    }
    public String throwConcurrencyException(String checksql,int version,long hcpId,long timeslotId)
    {

        try {
            PreparedStatement myStmt = null;
            myStmt=dbc.setPreparedStatement(checksql);
            myStmt.setLong(1,hcpId);
            myStmt.setLong(2,timeslotId);
            ResultSet rs=myStmt.executeQuery();
            if(rs.next())
            {
                if(rs.getInt("version")>version)
                {
                    return "fail to modify the timeslot because before your modification " +
                            "it has been modified by another health care provider!" +
                            " Back to timeslot page!";
                }
            }
            else
            {
                return "The timeslot has been deleted!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "unexpected error!";
    }
}
