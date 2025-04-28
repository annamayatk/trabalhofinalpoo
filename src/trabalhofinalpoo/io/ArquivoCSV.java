package trabalhofinalpoo.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import trabalhofinalpoo.banco.DependenteDAO;
import trabalhofinalpoo.banco.FuncionarioDAO;
import trabalhofinalpoo.classes.Dependente;
import trabalhofinalpoo.classes.FolhaPagamento;
import trabalhofinalpoo.classes.Funcionario;
import trabalhofinalpoo.enums.Parentesco;
import trabalhofinalpoo.exceptions.DependenteException;

public class ArquivoCSV {

	public List<FolhaPagamento> lerArquivo(String caminho) {
		List<FolhaPagamento> folhas = new ArrayList<>();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd");

		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String linha;
			Funcionario funcionarioAtual = null;
			List<Dependente> dependentes = new ArrayList<>();
			int codigo = 1;

			while ((linha = br.readLine()) != null) {

				linha = linha.trim();

				if (linha.isEmpty()) {
					if (funcionarioAtual != null) {

						funcionarioAtual = new Funcionario(funcionarioAtual.getNome(), funcionarioAtual.getCpf(),
								funcionarioAtual.getDataNascimento(), funcionarioAtual.getSalarioBruto(), 0, 0,
								new ArrayList<>(dependentes));

						FolhaPagamento folha = new FolhaPagamento(codigo++, funcionarioAtual, LocalDate.now());
						folha.calcularDescontos();
						folhas.add(folha);
						dependentes.clear();
						funcionarioAtual = null;
					}
				} else {

					String[] dados = linha.split(";");
					if (dados.length == 4 && !dados[3].matches("[A-Z]+")) {
						// funcionário
						String nome = dados[0];
						String cpf = dados[1];
						LocalDate dataNasc = LocalDate.parse(dados[2], formato);
						double salario = Double.parseDouble(dados[3]);
						funcionarioAtual = new Funcionario(nome, cpf, dataNasc, salario, 0, 0, new ArrayList<>());
						FuncionarioDAO dao = new FuncionarioDAO();
						dao.inserirFuncionario(funcionarioAtual);
					} else if (dados.length == 4) {
						// Dependente
						try {
							String nome = dados[0];
							String cpf = dados[1];
							LocalDate dataNasc = LocalDate.parse(dados[2], formato);
							Parentesco parentesco = Parentesco.valueOf(dados[3]);
							Dependente dependente = new Dependente(nome, cpf, dataNasc, parentesco, funcionarioAtual);
							dependentes.add(dependente);
							DependenteDAO dependenteDAO = new DependenteDAO();
							dependenteDAO.inserirDependente(dependente);
						} catch (DependenteException e) {
							System.out.println("Dependente ignorado: " + e.getMessage());
							// salvar no rejeitados.csv depois
						}
					}
				}
			}

			
			
			if (funcionarioAtual != null) {
	            funcionarioAtual = new Funcionario(funcionarioAtual.getNome(), funcionarioAtual.getCpf(),
	                    funcionarioAtual.getDataNascimento(), funcionarioAtual.getSalarioBruto(), 0, 0,
	                    new ArrayList<>(dependentes));

	            FolhaPagamento folha = new FolhaPagamento(codigo++, funcionarioAtual, LocalDate.now());
	            folha.calcularDescontos();
	            folhas.add(folha);
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return folhas;
	}

	
}
