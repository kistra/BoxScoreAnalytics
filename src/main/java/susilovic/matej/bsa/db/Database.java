package susilovic.matej.bsa.db;

import java.sql.*;

import static susilovic.matej.bsa.util.PropertyLoader.getProperty;

public class Database {

    public static Database db;
    public Connection connection;
    private PreparedStatement statement;

    private Database() {

        String url = getProperty("dburl");
        String dbName = getProperty("dbname");
        String username = getProperty("dbusername");
        String password = getProperty("dbpassword");

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
