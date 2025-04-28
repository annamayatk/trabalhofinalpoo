package trabalhofinalpoo.classes;

import java.time.LocalDate;
import trabalhofinalpoo.interfaces.Calculos;

public class FolhaPagamento implements Calculos {
	private int codigo;
	private Funcionario funcionario;
	private LocalDate dataPagamento;
	private double descontoINSS;
	private double descontoIR;
	private double salarioLiquido;

	public FolhaPagamento(int codigo, Funcionario funcionario, LocalDate dataPagamento) {
		this.codigo = codigo;
		this.funcionario = funcionario;
		this.dataPagamento = dataPagamento;
	}

	@Override
	public void calcularDescontos() {
	    double salarioBruto = funcionario.getSalarioBruto();

	    descontoINSS = calcularDescontoINSS(salarioBruto);

	    //calculo  IR
	    double valorPorDependentes = funcionario.getDependentes().size() * 189.59;
	    double baseCalculoIR = salarioBruto - descontoINSS - valorPorDependentes;
	    descontoIR = calcularDescontoIR(baseCalculoIR);

	    salarioLiquido = salarioBruto - descontoINSS - descontoIR;

	    //atualizar os valores no Funcionario
	    funcionario.setSalarioLiquido(salarioLiquido);
	    funcionario.setDescontoInss(descontoINSS);
	    funcionario.setDescontoIR(descontoIR);
	}

	private double calcularDescontoINSS(double salarioBruto) {
	    if (salarioBruto <= 1518.00) {
	        return salarioBruto * 0.075;
	    } else if (salarioBruto <= 2793.88) {
	        return (salarioBruto * 0.09) - 22.77;
	    } else if (salarioBruto <= 4190.83) {
	        return (salarioBruto * 0.12) - 106.60;
	    } else if (salarioBruto <= 8157.41) {
	        return (salarioBruto * 0.14) - 190.42;
	    } else {
	        return 951.62;
	    }
	}

	private double calcularDescontoIR(double baseCalculoIR) {
	    if (baseCalculoIR <= 2259.00) {
	        return 0;
	    } else if (baseCalculoIR <= 2826.65) {
	        return (baseCalculoIR * 0.075) - 169.44;
	    } else if (baseCalculoIR <= 3751.05) {
	        return (baseCalculoIR * 0.15) - 381.44;
	    } else if (baseCalculoIR <= 4664.68) {
	        return (baseCalculoIR * 0.225) - 662.77;
	    } else {
	        return (baseCalculoIR * 0.275) - 896.00;
	    }
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public double getDescontoINSS() {
		return descontoINSS;
	}

	public void setDescontoINSS(double descontoINSS) {
		this.descontoINSS = descontoINSS;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(double descontoIR) {
		this.descontoIR = descontoIR;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

}
