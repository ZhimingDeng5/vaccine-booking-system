package com.example.Flying_Tiger;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class RecipientMapper extends UserMapper {
    public RecipientMapper(String table){
        super(table);
    }
    @Override
    public Recipient find(long id) {
        ResultSet rs = this.findRow(id);
        String password;
        String name;
        Date birthDate;
        Recipient result;
        boolean suitable;
        boolean injected;
            try {
                if (rs.next()) {
                    password = rs.getString("password");
                    name = rs.getString("name");

                    birthDate = rs.getDate("birthDate");
                    suitable = rs.getBoolean("suitable");
                    injected = rs.getBoolean("injected");
                    result = new Recipient(id, password, name, birthDate, suitable, injected);
                    return result;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
    public int getnumOfBooking()
    {
        DBConn dbc=new DBConn();
        dbc.openDB();
        String query = "SELECT COUNT (*) FROM " + this.table+" WHERE \"timeslotID\" is not null";
        ResultSet rs = dbc.execQuery(query);
        try {
            if (rs.next()) {
                return rs.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Recipient[] findall(){
        DBConn dbc=new DBConn();
        dbc.openDB();
        // get the row with this id
        String query = "SELECT * FROM " + super.table;
        ResultSet rs = dbc.execQuery(query);
        try {
            int size = 0;
            while(rs.next()){
                size++;
            }
            rs = dbc.execQuery(query);
            Recipient[] recipients= new Recipient[size];
            for(int i =0; i< size; i++){
                rs.next();
                recipients[i] = new Recipient(rs.getLong("ID"), rs.getString("password"), rs.getString("name"), rs.getDate("birthDate"), rs.getBoolean("suitable"),rs.getBoolean("injected"));
            }
            return recipients;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Recipient[0];

    }
    /**
     * foreign key mapping
     * @param timeslotID
     * @return Recipient[]
     */
    public Recipient[] findForTimeslot(long timeslotID){
        DBConn dbc=new DBConn();
        dbc.openDB();
        // get the row with this id
        String query = "SELECT * FROM " + super.table + " WHERE \"timeslotID\" = "+timeslotID;
        ResultSet rs = dbc.execQuery(query);
        try {
            int size = 0;
            while(rs.next()){
                size++;
            }
            rs = dbc.execQuery(query);
            Recipient[] recipients= new Recipient[size];
            for(int i =0; i< size; i++){
                rs.next();
                recipients[i] = new Recipient(rs.getLong("ID"), rs.getString("password"), rs.getString("name"), rs.getDate("birthDate"), rs.getBoolean("suitable"),rs.getBoolean("injected"));
            }
            return recipients;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Recipient[0];

    }
    // calculate the age of the recipients when timeslot
    public int calculateAge(long id){
        ResultSet rs = this.findRow(id);
        Date birthDate = null;
        long timeslotID = 0;
        try {
            rs.next();
            birthDate = rs.getDate("birthDate");
            timeslotID = rs.getLong("timeslotID");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        TimeslotMapper tm = new TimeslotMapper("timeslot");
        Date timeslotDate =  tm.timeslotDate(timeslotID);
        LocalDate timeslotDateLocal = timeslotDate.toLocalDate();
        LocalDate birthDateLocal = birthDate.toLocalDate();
        int age = Period.between(birthDateLocal, timeslotDateLocal).getYears();

        return age;
    }
}
