package com.muhammetakduman.javafx.database;

import java.sql.*;


public class SingletonDbConnection {
    //field
    //dt informatin data
    private static final String URL = "jdbc:h2:./h2db/user_management";
    //private static final String URL = "jdbc:h2:./h2db/user_management" + "AUTO_SERVER=TRUE";
    // kok dizin    private static final String URL = "jdbc:h2:~/h2db/user_management" ;
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
    public static void dataSet(){
        // Singleton Instance ile Bağlantıyı Al
        SingletonDbConnection dbInstance = SingletonDbConnection.getInstance();
        Connection conn = dbInstance.getConnection();

        try {
            Statement stmt = conn.createStatement();

            // Örnek bir tablo oluştur
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "email VARCHAR(255))";
            stmt.execute(createTableSQL);
            System.out.println("Users tablosu oluşturuldu!");

            // Veri Ekleme
            String insertDataSQL = "INSERT INTO Users (name, email) VALUES "
                    + "('Ali Veli', 'ali@example.com'), "
                    + "('Ayşe Fatma', 'ayse@example.com')";
            stmt.executeUpdate(insertDataSQL);
            System.out.println("Veriler eklendi!");

            // Veri Okuma
            String selectSQL = "SELECT * FROM Users";
            ResultSet rs = stmt.executeQuery(selectSQL);

            System.out.println("\nUsers Tablosu İçeriği:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Email: " + rs.getString("email"));
            }

            // Bağlantıyı Kapat
            SingletonDbConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //dataSet();
    }
}



    //For Database, Connection
