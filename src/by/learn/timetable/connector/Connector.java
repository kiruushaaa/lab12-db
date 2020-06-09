package by.learn.timetable.connector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            properties.load(in);
        }
        String url = "jdbc:mysql://localhost:3306/tablesdb";
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url,username,password);
    }
}
