package com.controledereagentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/controle_de_reagentes";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar-se com o banco de dados!", e);
        }
    }
}
