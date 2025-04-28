package trabalhofinalpoo.classes;

import java.time.LocalDate;
import java.util.List;

public class Funcionario extends Pessoa {

	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;
	private List<Dependente> dependentes;
	private double salarioLiquido;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto, double descontoInss,
			double descontoIR, List<Dependente> dependentes) {

		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIR = descontoIR;
		this.dependentes = dependentes;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public double getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(double descontoIR) {
		this.descontoIR = descontoIR;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void adicionarDependente(Dependente dependente) {
		this.dependentes.add(dependente);
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
}
