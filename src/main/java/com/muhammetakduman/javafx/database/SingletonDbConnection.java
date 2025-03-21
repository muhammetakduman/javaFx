package com.muhammetakduman.javafx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SingletonDbConnection {
    //field
    //dt informatin data
    //private static final String URL = "jdbc:h2:./h2db/user_management" + "AUTO_SERVER=TRUE";
    private static final String URL = "jdbc:h2:~/h2db/user_management" ;
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    //Singleton design pattern
    private static SingletonDbConnection instance ;
    private  Connection connection;

    //parametresiz connst (private amaç dışardan erişilmez)
    private SingletonDbConnection() {
        try {
            //jdbc yükle
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(URL,USERNAME , PASSWORD);
            System.out.println("Database bağlantısı başarılıııııı.");
        } catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Veritabanı bağlanıtsı başarısızzzzzzzzz.");
            throw new RuntimeException("Veritabanı bağlantısı başarısız.");
        }
    }
    // Singleton Design Intance
    public static synchronized SingletonDbConnection getInstance(){
        if (instance==null) {
            instance = new SingletonDbConnection();
        }
        return instance;
    }
    //bağlantı nesnesini çağırma
    public  Connection getConnection() {
        return connection;
    }

    // db bağlantı kapatma

    public static void closeConnection(){
        if (instance !=null && instance.connection!=null){
            try {
                instance.connection.close();
                System.out.println("Db bağlantısı kapatıldı.");
            } catch (SQLException e){
                throw new RuntimeException(e);
            }

        }
    }


    //db test
    public static void main(String[] args) {

    }


    //For Database, Connection

}
