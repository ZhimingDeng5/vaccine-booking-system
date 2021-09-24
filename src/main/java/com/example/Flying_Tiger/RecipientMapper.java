package com.example.Flying_Tiger;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class RecipientMapper extends UserMapper {
    public RecipientMapper(){
        super("recipient");
    }
    @Override
    public Recipient find(long id) throws SQLException {
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
    public void insert(Long id, String password, String name, Date date) throws SQLException {
        dbc.openDB();
        String query = "INSERT INTO public." + super.table +
                "(\"ID\", password, name,\"birthDate\") VALUES (?, ?, ?, ?)";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, id);
        myStmt.setString(2, password);
        myStmt.setString(3, name);
        myStmt.setDate(4, date);
        myStmt.executeUpdate();
    }
    public void delete(long id) {

        String query = "DELETE FROM public.recipient WHERE \"ID\"=? " ;
        try {
            PreparedStatement myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1,id);
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletebooking(long id) {

        dbc.openDB();
        // update
        String query = "UPDATE " + this.table + " set \"timeslotID\" = null, \"hcpID\" = null,  \"suitable\" = false, \"vacID\"=null  WHERE  \"ID\" = ?; ";
        try {
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, id);
        myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getnumOfBooking()
    {
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
        // get the row with this id
        String query = "SELECT * FROM " + super.table;
        try {
            PreparedStatement myStmt = dbc.setPreparedStatement(query);
            ResultSet rs = myStmt.executeQuery();
            int size = 0;
            while(rs.next()){
                size++;
            }
            myStmt = dbc.setPreparedStatement(query);
            rs = myStmt.executeQuery();
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
    public Recipient[] findallbooking(){
        // get the row with this id
        String query = "SELECT * FROM " + super.table + " WHERE \"timeslotID\" is not null";
        try {
            PreparedStatement myStmt = dbc.setPreparedStatement(query);
            ResultSet rs = myStmt.executeQuery();
            int size = 0;
            while(rs.next()){
                size++;
            }
            rs = myStmt.executeQuery();
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
    public Recipient[] findForTimeslot(long timeslotID) throws SQLException {
        // get the row with this id
        String query = "SELECT * FROM " + super.table + " WHERE \"timeslotID\" = ?";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, timeslotID);
        ResultSet rs = myStmt.executeQuery();
        try {
            int size = 0;
            while(rs.next()){
                size++;
            }
            rs = myStmt.executeQuery();
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
     * get the vaccineType the recipient book
     * @param id
     * @return vaccineType
     * @throws SQLException
     */
    public String getVaccineType(long id) throws SQLException{
        ResultSet rs = this.findRow(id);
        if(rs.next()){
            long vaccineid = rs.getLong("vacID");
            Vaccine vaccine=VaccineMapper.getInstance().find(vaccineid);
            String vaccineType=vaccine.getType();
            return vaccineType;
        }
        return null;
    }

    /**
     * get the date which the recipient chose
     * @param id
     * @return
     * @throws SQLException
     */
    public Date timeslotDate(long id) throws SQLException {
        ResultSet rs = this.findRow(id);
        if(rs.next()){
            long timeslotID = rs.getLong("timeslotID");
            if( timeslotID != 0){
                TimeslotMapper timeslotMapper = new TimeslotMapper();
                return timeslotMapper.find(timeslotID).getDate();
            }

        }
        return null;
    }

    /**
     * get the time which the recipient chose
     * @param id
     * @return
     * @throws SQLException
     */
    public Time timeslotTime(long id) throws SQLException {
        ResultSet rs = this.findRow(id);
        if(rs.next()){
            long timeslotID = rs.getLong("timeslotID");
            if( timeslotID != 0){
                TimeslotMapper timeslotMapper = new TimeslotMapper();
                return timeslotMapper.find(timeslotID).getTime();
            }

        }
        return null;
    }
    public String getHcpName(long id) throws SQLException {
        ResultSet rs = this.findRow(id);
        if (rs.next()) {
            long hcpID = rs.getLong("hcpID");
            if (hcpID != 0) {
                return HealthCareProvider.getMapper().find(hcpID).getName();
            }
        }
        return null;
    }
    // calculate the age of the recipients when timeslot
    public int calculateAge(long id) throws SQLException {
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
        TimeslotMapper tm = new TimeslotMapper();
        Date timeslotDate =  tm.timeslotDate(timeslotID);
        LocalDate timeslotDateLocal = timeslotDate.toLocalDate();
        LocalDate birthDateLocal = birthDate.toLocalDate();
        int age = Period.between(birthDateLocal, timeslotDateLocal).getYears();

        return age;
    }

    /**
     * update the row with recipient
     * @param recipient
     * @throws SQLException
     */
    public void update(Recipient recipient) throws SQLException {
        dbc.openDB();
        // update
        String query = "UPDATE " + this.table + " set \"password\" = ?, \"name\" = ?, \"birthDate\"=?, \"suitable\"=?, \"injected\"=?  WHERE  \"ID\" = ?; ";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setString(1, recipient.getPassword());
        myStmt.setString(2, recipient.getName());
        myStmt.setDate(3, recipient.getBirth());
        myStmt.setBoolean(4, recipient.getSuitable());
        myStmt.setBoolean(5, recipient.getInjected());
        myStmt.setLong(6, recipient.getID());
        myStmt.executeUpdate();
    }

    /**
     * update the row about timeslotID and hcpID given the contents submitted by web
     * @param ID,timeslotDate,timeslotTime,hcpName
     * @throws SQLException
     */
    public void updateTimeslotIDHcpID(long ID, Date timeslotDate, Time timeslotTime, String hcpName) throws SQLException {

        // get the hcpID given contents submitted by web
        HealthCareProviderMapper healthCareProviderMapper = new HealthCareProviderMapper();
        long hcpID = healthCareProviderMapper.getIDByName(hcpName);
        // get the timeslotID given Date and time submitted by web
        TimeslotMapper timeslotMapper = new TimeslotMapper();
        long timeslotID = timeslotMapper.getIDByDateTime(timeslotDate,timeslotTime);
        dbc.openDB();
        // update
        String query = "UPDATE " + this.table + " set \"timeslotID\" = ?, \"hcpID\" = ? WHERE  \"ID\" = ?; ";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, timeslotID);
        myStmt.setLong(2, hcpID);
        myStmt.setLong(3, ID);
        myStmt.executeUpdate();
    }


}
