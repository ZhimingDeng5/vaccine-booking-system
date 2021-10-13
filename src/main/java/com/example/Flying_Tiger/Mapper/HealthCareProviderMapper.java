package com.example.Flying_Tiger.Mapper;

import com.example.Flying_Tiger.Class.HealthCareProvider;
import com.example.Flying_Tiger.Class.Questionaire;
import com.example.Flying_Tiger.Class.Timeslot;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthCareProviderMapper extends UserMapper {
    public HealthCareProviderMapper() {
        super("healthcareprovider");
    }
    private final static HealthCareProviderMapper instance=new HealthCareProviderMapper();
    public static HealthCareProviderMapper getInstance()
    {
        return instance;
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
    public HealthCareProvider find(long id,Date date) throws SQLException {
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
                timeslots = loadTimeslots(id,date);
                result = new HealthCareProvider(id, password, name, type, postcode, timeslots);
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Timeslot[] loadTimeslots(long hcpID) throws SQLException {
        dbc.openDB();
        String associationTable = "timeslot_healthcareprovider";
        String query = "SELECT \"hcpID\" , \"timeslotID\"  FROM " + associationTable + " WHERE \"hcpID\" = ?";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, hcpID);
        ResultSet rs = myStmt.executeQuery();
        try {
            int size = 0;
            while (rs.next()) {
                size++;
            }
            rs = myStmt.executeQuery();
            Timeslot[] timeslots = new Timeslot[size];
            TimeslotMapper timeslotMapper = new TimeslotMapper();
            for (int i = 0; i < size; i++) {
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
     * given hcpID and a specific date, return the timeslots which satisfy these
     * @param hcpID
     * @param date
     * @return timeslot[]
     * @throws SQLException
     */
    public Timeslot[] loadTimeslots(long hcpID, Date date) throws SQLException {
        dbc.openDB();
        String associationTable = "timeslot_healthcareprovider";
        String query = "SELECT * FROM timeslot inner join timeslot_healthcareprovider on timeslot.\"ID\" = timeslot_healthcareprovider.\"timeslotID\"\n" +
                "WHERE timeslot_healthcareprovider.\"hcpID\" = ? and \"date\"=?;";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, hcpID);
        myStmt.setDate(2,date);
        ResultSet rs = myStmt.executeQuery();
        try {
            int size = 0;
            while (rs.next()) {
                size++;
            }
            rs = myStmt.executeQuery();
            Timeslot[] timeslots = new Timeslot[size];
            TimeslotMapper timeslotMapper = new TimeslotMapper();
            for (int i = 0; i < size; i++) {
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
     *
     * @param name
     * @return ID
     * @throws SQLException
     */
    public long getIDByName(String name) throws SQLException {
        dbc.openDB();
        // find id with name
        String query = "SELECT \"ID\" FROM " + this.table + " WHERE \"name\" = ?";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setString(1, name);
        ResultSet rs = myStmt.executeQuery();
        if (rs.next()) {
            return rs.getLong("ID");
        }
        return 0;
    }

    public HealthCareProvider[] findall() {
        // get the row with this id
        String query = "SELECT * FROM " + super.table;
        ResultSet rs = dbc.execQuery(query);
        try {
            int size = 0;
            while (rs.next()) {
                size++;
            }
            rs = dbc.execQuery(query);
            HealthCareProvider[] hcps = new HealthCareProvider[size];
            for (int i = 0; i < size; i++) {
                rs.next();
                hcps[i] = new HealthCareProvider(rs.getLong("ID"), rs.getString("password"),
                        rs.getString("name"), rs.getString("hcpType"), rs.getInt("postcode"), null);
            }
            return hcps;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HealthCareProvider[0];

    }
    public HealthCareProvider[] findallWithTimeslot()
    {
        // get the row with this id
        String query = "SELECT * FROM " + super.table;
        ResultSet rs = dbc.execQuery(query);
        try {
            int size = 0;
            while (rs.next()) {
                size++;
            }
            rs = dbc.execQuery(query);
            HealthCareProvider[] hcps = new HealthCareProvider[size];
            for (int i = 0; i < size; i++) {
                rs.next();
                hcps[i] = this.find(rs.getLong("ID"));
            }
            return hcps;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HealthCareProvider[0];
    }
    public Questionaire findquestionnaire(Long hcpid, String type){
        dbc.openDB();
        // update
        String query = "SELECT * FROM " + this.table + " WHERE \"hcpID\" = ?, \"vacType\"=?; ";
        PreparedStatement myStmt = null;
        try {
            myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1, hcpid);
            myStmt.setString(2, type);
            ResultSet rs = myStmt.executeQuery();
            if (rs.next())
            {

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public HealthCareProvider[] findallWithTimeslot(Date date)
    {
        // get the row with this id
        String query = "SELECT * FROM " + super.table;
        ResultSet rs = dbc.execQuery(query);
        try {
            int size = 0;
            while (rs.next()) {
                size++;
            }
            rs = dbc.execQuery(query);
            HealthCareProvider[] hcps = new HealthCareProvider[size];
            for (int i = 0; i < size; i++) {
                rs.next();
                hcps[i] = this.find(rs.getLong("ID"),date);
            }
            return hcps;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HealthCareProvider[0];

    }
    public void update(HealthCareProvider healthCareProvider) throws SQLException {
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

    public void insert(Long id, String password, String type, String name, int post) throws SQLException {
        dbc.openDB();
        String query = "INSERT INTO public." + super.table +
                "(\"ID\", password, \"hcpType\", name, postcode) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement myStmt = dbc.setPreparedStatement(query);
        myStmt.setLong(1, id);
        myStmt.setString(2, password);
        myStmt.setString(3, type);
        myStmt.setString(4, name);
        myStmt.setInt(5, post);
        myStmt.executeUpdate();
    }

    public void delete(long id) {

    String query = "DELETE FROM public.healthcareprovider WHERE \"ID\"=?";
        try {
            PreparedStatement myStmt = dbc.setPreparedStatement(query);
            myStmt.setLong(1,id);
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


