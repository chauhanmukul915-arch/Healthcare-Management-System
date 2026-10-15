package com.healthcare.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class DB {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream in = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) throw new IllegalStateException(
                "Missing db.properties. Copy db.properties.example to db.properties and configure MySQL.");
            PROPERTIES.load(in);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private DB() {}

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
            PROPERTIES.getProperty("db.url"),
            PROPERTIES.getProperty("db.username"),
            PROPERTIES.getProperty("db.password"));
    }
}
