package entidades;

import java.util.Date;

public abstract class Contrato {
	
	private int id = 0;
	private Date dataInicio;
	private Colaborador colaborador;
	private boolean ativo;
	
	
	
	public Contrato(Date dataInicio, Colaborador colaborador) {
		super();
		this.id = id++;
		this.dataInicio = dataInicio;
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

	
	
}
