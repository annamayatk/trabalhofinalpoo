package trabalhofinalpoo.banco; 

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = BancoDeDados.getConnection()) {
            if (conn != null) {
                System.out.println("Conexão com o banco de dados bem-sucedida!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}

