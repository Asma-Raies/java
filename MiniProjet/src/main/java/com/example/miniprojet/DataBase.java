package com.example.miniprojet;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    public static Connection connectDB() {
        Connection con = null ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://" + "localhost:3306/hospital", "root", "");
           // System.out.println("connexion effectuee");



        } catch (Exception e) {
            System.out.println("impossible de se connecter a la base de donnes");
        }
        return con ;
    }
}
