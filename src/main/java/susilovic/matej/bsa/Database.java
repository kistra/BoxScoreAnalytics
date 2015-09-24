package susilovic.matej.bsa;

import java.sql.*;

public class Database {

    public static Database db;
    public Connection connection;
    private PreparedStatement statement;

    private Database() {

        String url = "jdbc:postgresql://localhost:5432/";
        String dbName = "msr";
        String username = "msr";
        String password = "7ncvji";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url + dbName, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }

    public static Database getDatabase() {

        if(db == null) {
            db = new Database();
        }
        return db;
    }
}
