package trabalhofinalpoo.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import trabalhofinalpoo.classes.Dependente;
import trabalhofinalpoo.exceptions.DependenteException;

public class DependenteDAO {

    public void inserirDependente(Dependente dependente) throws DependenteException {
        String sql = "INSERT INTO dependente (nome, cpf, data_nascimento, parentesco, cpf_funcionario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = BancoDeDados.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Verifica se o CPF já existe na tabela de dependentes
            if (verificarCpfExistente(dependente.getCpf())) {
                throw new DependenteException("Já existe um dependente com o CPF: " + dependente.getCpf());
            }

            // Preenche os parâmetros do PreparedStatement
            stmt.setString(1, dependente.getNome());
            stmt.setString(2, dependente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(dependente.getDataNascimento()));
            stmt.setString(4, dependente.getParentesco().toString());
            stmt.setString(5, dependente.getFuncionarioCpf());

            stmt.executeUpdate();
            System.out.println("Dependente inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DependenteException("Erro ao inserir dependente: " + e.getMessage());
        }
    }

    private boolean verificarCpfExistente(String cpf) throws SQLException {
        String query = "SELECT COUNT(*) FROM dependente WHERE cpf = ?";
        try (Connection conn = BancoDeDados.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cpf);
            
            //consulta de cpf
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);  //A primeira coluna do resultado é o COUNT(*)
                    return count > 0;  //se o count for > 0, significa que o CPF ja existe
                }
            }
        }
        return false;  
    }
}
