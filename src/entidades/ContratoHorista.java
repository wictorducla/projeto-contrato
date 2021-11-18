package entidades;

import java.util.Date;

public class ContratoHorista extends Contrato{
	
	private int horasMes;
	private float valorHora;
	
	public ContratoHorista(int id, Date dataInicio, Colaborador colaborador, boolean ativo, int horasMes,
			float valorHora) {
		super(dataInicio, colaborador);
		this.horasMes = horasMes;
		this.valorHora = valorHora;
	}

	public float calcVencimento() {
		
		return 0;
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
