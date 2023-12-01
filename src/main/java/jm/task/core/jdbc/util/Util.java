package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String URl = "jdbc:postgresql://localhost:5432/task1";
    public static final String NAME = "postgres";
    public static final String PASSWORD = "root";

    public static Connection getConnection ()  {
        try {
          Class<Driver> driverClass = Driver.class;
            return DriverManager.getConnection(URl,NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
