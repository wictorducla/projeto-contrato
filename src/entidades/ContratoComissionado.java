package entidades;

import java.util.Date;

public class ContratoComissionado extends Contrato{
	
	private float percComissao;
	private float ajudaCusto;
	
	public float calcVencimento(float vlFaturam) {
		float vencimento = (vlFaturam * percComissao / 100) + ajudaCusto;
		float seguro = (float) ((0.5/100) * ajudaCusto + ( ajudaCusto - (1/100 * vencimento))) ;
		if (seguro < 25) {
			seguro = 25f;
		}
		vencimento -= seguro;
		return vencimento;
	}

	public ContratoComissionado(Date dataInicio, Date dataEncerramento, Colaborador colaborador, float percComissao,
			float ajudaCusto) {
		super(dataInicio, dataEncerramento, colaborador);
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
