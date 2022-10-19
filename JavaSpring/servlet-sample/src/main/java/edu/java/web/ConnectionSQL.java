package edu.java.web;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSQL {
    static ConnectionSQL singeltonPatern;
    private static Connection connection;

    public ConnectionSQL() {
        File file = new File("./user_db");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + "; create=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectionSQL getSingeltonPatern() {
        if (singeltonPatern == null) {
            singeltonPatern = new ConnectionSQL();
        }
        return singeltonPatern;
    }

    public Connection getConnection() {
        return connection;
    }
}