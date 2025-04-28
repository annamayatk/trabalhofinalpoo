package trabalhofinalpoo.main;

import java.util.List;
import java.util.Scanner;

import trabalhofinalpoo.banco.FolhaPagamentoDAO;
import trabalhofinalpoo.classes.FolhaPagamento;
import trabalhofinalpoo.io.ArquivoCSV;
import trabalhofinalpoo.io.GeradorDeSaida;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Pedir o caminho do arquivo de entrada via console
		System.out.print("Digite o caminho do arquivo de entrada (exemplo: C:\\Caminho\\para\\entrada.csv): ");
		String caminhoEntrada = scanner.nextLine();

		ArquivoCSV leitor = new ArquivoCSV();
		List<FolhaPagamento> folhas = leitor.lerArquivo(caminhoEntrada);

		System.out.println("Folhas processadas:");
		for (FolhaPagamento folha : folhas) {
			System.out.println(folha.getFuncionario().getNome() + " - Salário Líquido: " + folha.getSalarioLiquido());
		}

		// Pedir o caminho do arquivo de saída
		System.out.print("Digite o caminho do arquivo de saída (exemplo: C:\\Caminho\\para\\saida.csv): ");
		String caminhoSaida = scanner.nextLine();

		GeradorDeSaida gerador = new GeradorDeSaida();
		gerador.gerarArquivo(caminhoSaida, folhas);
		
		FolhaPagamentoDAO folhaPagamentoDAO = new FolhaPagamentoDAO();
	    for (FolhaPagamento folha : folhas) {
	        folhaPagamentoDAO.inserirFolha(folha);
	    }
	    
		scanner.close();
	}
}
