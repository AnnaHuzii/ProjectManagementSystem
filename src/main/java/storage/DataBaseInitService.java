package storage;

import connection.Prefs;
import org.flywaydb.core.Flyway;

public class DataBaseInitService {
    public void initDb(String connectionUrl) {

        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, Prefs.DB_USERNAME, Prefs.DB_PASSWORD)
                .load();

        flyway.migrate();
    }
}
