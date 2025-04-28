package trabalhofinalpoo.classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import trabalhofinalpoo.enums.Parentesco;
import trabalhofinalpoo.exceptions.DependenteException;

public class Dependente extends Pessoa {
	private Funcionario funcionario; 
	private Parentesco parentesco;
	private Set<String> cpfsRegistrados = new HashSet<>();
	
	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco, Funcionario funcionario) throws DependenteException {
		super(nome, cpf, dataNascimento);

		if (Period.between(dataNascimento, LocalDate.now()).getYears() >= 18) {
			throw new DependenteException("O dependente deve ter menos de 18 anos.");
		}
		
		if (cpfsRegistrados.contains(cpf)) {
	            throw new DependenteException("Já existe um dependente com o CPF " + cpf);
	    }
		
        cpfsRegistrados.add(cpf);

		this.parentesco = parentesco;
		this.funcionario = funcionario;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return getNome() + ";" + getCpf() + ";" + getDataNascimento() + ";" + parentesco;
	}
	
	public String getFuncionarioCpf() {
        return funcionario.getCpf();  
    }

}
