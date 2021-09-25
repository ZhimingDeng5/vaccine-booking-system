package com.example.Flying_Tiger;
import java.sql.Date;
import java.sql.Time;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;


public class Timeslot{
    private long ID;
    private Date date;
    private Time time;
    private Recipient[] recipients;
    Timeslot(long ID,Date date,Time time,Recipient[] recipients)
    {
        this.ID=ID;
        this.date=date;
        this.time=time;
        this.recipients = recipients;
    }
    public long getTimeslotID() {
        return ID;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
    public Recipient[] getRecipients(){
        return recipients;
    }


//    public static Set<Timeslot> getall()
//    {
//        Set timeslottable=new HashSet<Timeslot>();
//        DBConn dbc=new DBConn();
//        dbc.openDB();
//        String sql="select * from timeslot";
//        ResultSet rs= dbc.execQuery(sql);
//        boolean v=false;
//        try {
//            while (rs.next()) {
//                long timeslotID=rs.getLong("timeslotID");
//                Date date=rs.getString("date");
//                Time time=rs.getString("time");
//                int hcpID=rs.getInt("hcpID");
//                timeslottable.add(new Timeslot(timeslotID,date,time,hcpID));
///*            if(rs.getString("username").equals(usr)&&rs.getString("password").compareTo(pwd)==0){
//                v=true;
//                break;
//            }*/
//            }
//            rs.close();
//            dbc.closeDB();
//        }
//        catch(Exception e)
//        {
//            System.err.println(e.getMessage());
//        }
//        return timeslottable;
//
//    }
}