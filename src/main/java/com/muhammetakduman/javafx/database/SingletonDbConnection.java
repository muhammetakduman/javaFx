package com.muhammetakduman.javafx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDbConnection {

    private static final String URL = "jdbc:h2:./h2db/blog" + "AUTO_SERVER=TRUE";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    //parametresiz connst
    private SingletonDbConnection() {

    }

    //For Database, Connection
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            try {
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                System.out.println("Veritabanı bağlantısı başarılııııııııııııııı");
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
                System.out.println("Veritabanı bağlanıtsı başarısızzzzzzzzz.");
                throw new RuntimeException("Veritabanı bağlantısı başarısız.");
            }
        }
        return connection;
    }
}
