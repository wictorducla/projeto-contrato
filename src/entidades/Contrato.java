package entidades;

import java.util.Date;

public abstract class Contrato {
	
	private int id = 0;
	private Date dataInicio;
	private Date dataEncerramento;
	private Colaborador colaborador;
	private boolean ativo;
	
	
	
	public Contrato(Date dataInicio, Date dataEncerramento, Colaborador colaborador) {
		this.id = id++;
		this.dataInicio = dataInicio;
		this.dataEncerramento = dataEncerramento;
		this.colaborador = colaborador;
		this.ativo = true;
	}

	public void encerrarContrato() {
		if (ativo == true) {
			ativo = false;
			System.out.println("Contrato encerrado!");
		} else {
			System.out.println("Contrato já se encontra encerrado!");
		}
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getId() {
		return id;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}
	
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	@Override
	public String toString() {
		return "Contrato id= " + id + 
				"\nData de inicio= " + dataInicio + 
				"\nData de encerramento= " + dataEncerramento + 
				"\nColaborador= " + colaborador + 
				"\nAtivo= " + ativo;
	}


	
	
}
