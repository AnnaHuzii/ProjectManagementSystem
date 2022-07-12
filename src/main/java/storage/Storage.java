package storage;

import connection.Prefs;

import java.sql.Connection;
import java.sql.DriverManager;

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

    public Connection getConnection() {
        return connection;
    }

}
