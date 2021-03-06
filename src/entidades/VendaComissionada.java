package entidades;

public class VendaComissionada {
	
	private int id = 0;
	private int mes;
	private int ano;
	private float valor;
	private ContratoComissionado contrComissionado;
	
	public VendaComissionada(int mes, int ano, float valor, ContratoComissionado contrComissionado) {
		this.id = id++;
		this.mes = mes;
		this.ano = ano;
		this.valor = valor;
		this.contrComissionado = contrComissionado;
	}
	
	public int getId() {
		return id;	
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public ContratoComissionado getContrComissionado() {
		return contrComissionado;
	}
	public void setContrComissionado(ContratoComissionado contrComissionado) {
		this.contrComissionado = contrComissionado;
	}
	
}
