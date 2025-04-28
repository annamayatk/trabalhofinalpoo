package trabalhofinalpoo.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {
    private static final String URL = "jdbc:postgresql://localhost:5432/folha_pagamento_db";  // Substitua pelo seu banco
    private static final String USUARIO = "postgres"; 
    private static final String SENHA = "postgres";       
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            throw e;
        }
    }
}
