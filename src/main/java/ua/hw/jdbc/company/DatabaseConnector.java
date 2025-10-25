package ua.hw.jdbc.company;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseConnector() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (in == null) throw new IllegalStateException("application.properties not found");
            Properties p = new Properties();
            p.load(in);
            url = p.getProperty("jdbc.url");
            user = p.getProperty("jdbc.user");
            password = p.getProperty("jdbc.password");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB properties", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
