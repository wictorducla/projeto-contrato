package entidades;

import java.util.Date;

public class ContratoAssalariado extends Contrato{
	
	private float salarioMensal;
	private float percInsalubridade;
	private float percPericulosidade;
	
	public float calcVencimento() {
		float vencimento = salarioMensal + (salarioMensal * percPericulosidade) + (salarioMensal * percInsalubridade);
		float seguro = vencimento * (2/100);
		if(seguro < 25) {
			seguro = 25f;
		}else if (seguro > 150) {
			seguro = 150f;
		}
		vencimento -= seguro;
		return vencimento;
	}


	public ContratoAssalariado(Date dataInicio, Date dataEncerramento, Colaborador colaborador, float salarioMensal,
			float percInsalubridade, float percPericulosidade) {
		super(dataInicio, dataEncerramento, colaborador);
		this.salarioMensal = salarioMensal;
		this.percInsalubridade = percInsalubridade;
		this.percPericulosidade = percPericulosidade;
	}



	public float getSalarioMensal() {
		return salarioMensal;
	}

	public void setSalarioMensal(float salarioMensal) {
		this.salarioMensal = salarioMensal;
	}

	public float getPercInsalubridade() {
		return percInsalubridade;
	}

	public void setPercInsalubridade(float percInsalubridade) {
		this.percInsalubridade = percInsalubridade;
	}

	public float getPercPericulosidade() {
		return percPericulosidade;
	}

	public void setPercPericulosidade(float percPericulosidade) {
		this.percPericulosidade = percPericulosidade;
	}
	
}
