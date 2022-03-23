package com.example.demo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = ""; //schemaName
        String databaseUser = "root";
        String databasePassword = ""; //pass
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
