package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "3008";

    public static Connection getConnection() {
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера");
        }
        return connection;
    }
}
