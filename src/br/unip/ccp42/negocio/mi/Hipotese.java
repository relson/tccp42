package br.unip.ccp42.negocio.mi;

import java.sql.SQLException;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.traducao.AplicacaoLinkTD;
import br.unip.ccp42.dados.traducao.HipoteseTD;
import br.unip.ccp42.negocio.entidade.AplicacaoLink;

public class Hipotese extends EntidadeBase implements Comparable<Hipotese>{
	
	private int pontuacao;	
	private AplicacaoLink aplicacaoLink ;
	private Classe aplicacao;
	
	public void setAplicacaoLink(AplicacaoLink aplicacaoLink) {
		this.aplicacaoLink = aplicacaoLink;
	}

	public AplicacaoLink getAplicacaoLink() throws SQLException, ClassNotFoundException {
		
		if (this.aplicacaoLink == null){
			AplicacaoLinkTD aplicacaoLinkTD = new AplicacaoLinkTD();
			
			this.setAplicacaoLink(
					aplicacaoLinkTD.getPorAplicacao(this.aplicacao)
			);
		}
		
		return aplicacaoLink;
	}	

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getPontuacao() {
		return pontuacao;
	}
	
	public void setAplicacao(Classe aplicacao) {
		this.aplicacao = aplicacao;
	}
	
	public Classe aplicacao() {
		return this.aplicacao;
	}
	
	@Override	
	public int compareTo(Hipotese o) {
		
		int resultado = 0; // a princípio os considera iguais.
		
		if (this.getPontuacao() < o.getPontuacao())
			resultado = -1;
		else if (this.getPontuacao() > o.getPontuacao())
			resultado = 1;
		
		return resultado;
	}

	@Override
	protected void setTradutorDados() {
		this.setTradutorDados(new HipoteseTD());
	}
	
}
