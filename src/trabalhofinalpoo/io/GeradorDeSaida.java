package trabalhofinalpoo.io;

import trabalhofinalpoo.classes.FolhaPagamento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class GeradorDeSaida {

    public void gerarArquivo(String caminho, List<FolhaPagamento> folhas) {
        DecimalFormat df = new DecimalFormat("0.00");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (FolhaPagamento folha : folhas) {
                String linha = String.join(";",
                        folha.getFuncionario().getNome(),
                        folha.getFuncionario().getCpf(),
                        df.format(folha.getDescontoINSS()).replace(",", "."),
                        df.format(folha.getDescontoIR()).replace(",", "."),
                        df.format(folha.getSalarioLiquido()).replace(",", ".")
                );
                bw.write(linha);
                bw.newLine();
            }
            System.out.println("Arquivo de saída gerado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
