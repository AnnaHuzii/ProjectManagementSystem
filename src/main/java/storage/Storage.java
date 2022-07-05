package storage;

import connection.Prefs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Storage {
    private static final Storage INSTANCE = new Storage();

    private Connection connection;

    private Storage() {
        try {
            Prefs prefs = new Prefs(Prefs.PREFS_FILENAME);

            String url = prefs.getString(Prefs.DB_URL);
            String username = prefs.getString(Prefs.DB_USERNAME);
            String password = prefs.getString(Prefs.DB_PASSWORD);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Storage getInstance() {
        return INSTANCE;
    }

    public int executeUpdate(String sql) {
        try(Statement st = connection.createStatement()) {
            return st.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
