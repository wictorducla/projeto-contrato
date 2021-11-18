package entidades;

import java.util.Date;

public class ContratoComissionado extends Contrato{
	
	private float percComissao;
	private float ajudaCusto;
	
	public float calcVencimento(float vlFaturam) {
		
		return 0;
	}

	public ContratoComissionado(int id, Date dataInicio, Colaborador colaborador, boolean ativo, float percComissao,
			float ajudaCusto) {
		super(dataInicio, colaborador);
		this.percComissao = percComissao;
		this.ajudaCusto = ajudaCusto;
	}

	public float getPercComissao() {
		return percComissao;
	}

	public void setPercComissao(float percComissao) {
		this.percComissao = percComissao;
	}

	public float getAjudaCusto() {
		return ajudaCusto;
	}

	public void setAjudaCusto(float ajudaCusto) {
		this.ajudaCusto = ajudaCusto;
	}
	
	

}
