import java.sql.*;

public class JDBCtest {
    private final String url = "jdbc:postgresql://localhost:5432/myDB";
    private final String user = "postgres";
    private final String password = "1234";
    /**
     * Connect to the PostgreSQL database
     * @return a Connection object
     */
    public void connect() {
        String sql = "SELECT * FROM users;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(url, user, password);
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            rs.next();
            String username = rs.getString(1);
            System.out.println(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCtest app = new JDBCtest();
        app.connect();
    }
}
