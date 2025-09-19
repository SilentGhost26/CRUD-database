package org.dao;

import java.sql.DriverManager;
import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;database=Hospital;encrypy=false;trustServerCertificate=true";
    private static final String USER = "luis";
    private static final String PASSWORD = "12345678";

    public static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if(connection != null){
            return connection;
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a SQL Server!");
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            return connection;
        }
    }
}
