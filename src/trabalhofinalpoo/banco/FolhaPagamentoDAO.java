package trabalhofinalpoo.banco;

import trabalhofinalpoo.classes.FolhaPagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FolhaPagamentoDAO {
    public void inserirFolha(FolhaPagamento folha) {
        String sql = "INSERT INTO folha_pagamento (codigo, cpf_funcionario, desconto_inss, desconto_ir, salario_liquido, data_pagamento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = BancoDeDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, folha.getCodigo());
            stmt.setString(2, folha.getFuncionario().getCpf());
            stmt.setDouble(3, folha.getDescontoINSS());
            stmt.setDouble(4, folha.getDescontoIR());
            stmt.setDouble(5, folha.getSalarioLiquido());
            stmt.setDate(6, java.sql.Date.valueOf(folha.getDataPagamento()));

            stmt.executeUpdate();
            System.out.println("Folha de pagamento do funcionário " + folha.getFuncionario().getNome() + " inserida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

