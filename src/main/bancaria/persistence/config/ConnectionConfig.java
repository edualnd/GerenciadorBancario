package main.bancaria.persistence.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/banco_digital?useSSL=false";
    private static final String USER = "duda";
    private static final String PASS = "sete77";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASS);
    }
}
