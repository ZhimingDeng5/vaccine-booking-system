package com.example.Flying_Tiger;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class Timeslot {
    private long timeslotID;
    private String date;
    private String time;
    private int hcpID;
    Timeslot(long timeslotID,String date,String time,int hcpID)
    {
        this.timeslotID=timeslotID;
        this.date=date;
        this.time=time;
        this.hcpID=hcpID;
    }
    public long getTimeslotID() {
        return timeslotID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getHcpID() {
        return hcpID;
    }
    public static Set<Timeslot> getall()
    {
        Set timeslottable=new HashSet<Timeslot>();
        DBConn dbc=new DBConn();
        dbc.openDB();
        String sql="select * from timeslot";
        ResultSet rs= dbc.execQuery(sql);
        boolean v=false;
        try {
            while (rs.next()) {
              long timeslotID=rs.getLong("timeslotID");
              String date=rs.getString("date");
              String time=rs.getString("time");
              int hcpID=rs.getInt("hcpID");
              timeslottable.add(new Timeslot(timeslotID,date,time,hcpID));
/*            if(rs.getString("username").equals(usr)&&rs.getString("password").compareTo(pwd)==0){
                v=true;
                break;
            }*/
            }
            rs.close();
            dbc.closeDB();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        return timeslottable;
/*        if(v){
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("faillogin.jsp").forward(request, response);
        }*/

    }
}
