package com.sparta.leanne;

import com.sparta.leanne.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection postgresConnection;
    public static Connection connectToDB(){
        String url = PropertiesLoader.getProperties("url");
        String username = PropertiesLoader.getProperties("userName") ;
        String password = PropertiesLoader.getProperties("password");

        try {
            postgresConnection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postgresConnection;

    }

    public static void closeConnection(){
        try {
            postgresConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
