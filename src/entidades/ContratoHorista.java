package entidades;

import java.util.Date;

public class ContratoHorista extends Contrato{
	
	private int horasMes;
	private float valorHora;
	
	public ContratoHorista(Date dataInicio, Date dataEncerramento, Colaborador colaborador, int horasMes,
			float valorHora) {
		super(dataInicio, dataEncerramento, colaborador);
		this.horasMes = horasMes;
		this.valorHora = valorHora;
	}

	public float calcVencimento() {
		float vencimento = valorHora * horasMes;
		float seguro = 0;
		if(vencimento <= 5000) {
			seguro = (2/100) * vencimento;
			vencimento -= seguro;
		}else if(seguro > 5000) {
			seguro = (float) ((2.5/100) * vencimento);
			vencimento -= seguro;
			
		}
		
		return vencimento;
	}

	public int getHorasMes() {
		return horasMes;
	}

	public void setHorasMes(int horasMes) {
		this.horasMes = horasMes;
	}

	public float getValorHora() {
		return valorHora;
	}

	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}
	
	
	
}
