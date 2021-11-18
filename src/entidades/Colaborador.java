package entidades;

import java.util.Date;

public class Colaborador {
	private String matricula;
	private String cpf;
	private String name;
	private Date dataNascimento;
	private boolean situacao;
	
	
	public Colaborador(String matricula, String cpf, String name, Date dataNascimento) {
		this.matricula = matricula;
		this.cpf = cpf;
		this.name = name;
		this.dataNascimento = dataNascimento;
		this.situacao = false;
	}
	
	public boolean validarCpf() {
		if (cpf.length() != 11 || 
				cpf == "00000000000" || 
				cpf == "11111111111" || 
				cpf == "22222222222" || 
				cpf == "33333333333" || 
				cpf == "44444444444" || 
				cpf == "55555555555" || 
				cpf == "66666666666" || 
				cpf == "77777777777" || 
				cpf == "88888888888" || 
				cpf == "99999999999")
					return false;	
		// Valida 1o digito	
		int add = 0;	
		for (int i=0; i < 9; i ++) {		
			add += Integer.parseInt(cpf, cpf.charAt(i)) * (10 - i);	
			int rev = 11 - (add % 11);	
			if (rev == 10 || rev == 11)		
				rev = 0;	
			if (rev != Integer.parseInt(cpf, cpf.charAt(9)))		
				return false; 
			}		
		// Valida 2o digito	
		add = 0;	
		for (int i = 0; i < 10; i ++) {		
			add += Integer.parseInt(cpf, cpf.charAt(i)) * (11 - i);	
		}
		int rev = 11 - (add % 11);	
		if (rev == 10 || rev == 11) {	
			rev = 0;	
		}
		if (rev != Integer.parseInt(cpf, cpf.charAt(10))) {
			return false;		
		}
		return true;
	}
	
	public void ativar() {
		if (this.situacao == false) {
			this.situacao = true;
			System.out.println("Colaborador ativado");
		} else {
			System.out.println("Colaborador já se encontra ativo!");
		}
	}
	
	public void desativar() {
		if (this.situacao == true) {
			this.situacao = false;
			System.out.println("Colaborador desativado");
		} else {
			System.out.println("Colaborador já se encontra desativado!");
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getCpf() {
		return cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public boolean isSituacao() {
		return situacao;
	}
	
	
	
}
