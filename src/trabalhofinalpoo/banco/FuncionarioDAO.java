package trabalhofinalpoo.banco;

import trabalhofinalpoo.classes.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioDAO {
    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cpf, data_nascimento, salario_bruto) VALUES (?, ?, ?, ?)";

        try (Connection conn = BancoDeDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(funcionario.getDataNascimento()));
            stmt.setDouble(4, funcionario.getSalarioBruto());

            stmt.executeUpdate();
            System.out.println("Funcionário " + funcionario.getNome() + " inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


