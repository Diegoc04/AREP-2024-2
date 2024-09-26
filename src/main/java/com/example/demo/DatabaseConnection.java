package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://mysql:3306/mydatabase";
        String user = "user";
        String password = "userpassword";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conectado a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}

