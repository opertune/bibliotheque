package fr.romain.bibliotheque.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBDD implements Constant {
    private Connection databaseLink;

    public Connection getConnection() {
        String user = DBUSER;
        String password = DBPW;
        String url = DBURL;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, user, password);
            System.out.println("Connection open");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur de connexion");
        }

        return databaseLink;
    }
}