package entidades;

import java.util.Date;

public abstract class Contrato {
	private int id;
	private Date dataInicio;
	private Colaborador colaborador;
	private boolean ativo;
	
	
	
	public Contrato(int id, Date dataInicio, Colaborador colaborador, boolean ativo) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.colaborador = colaborador;
		this.ativo = ativo;
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
