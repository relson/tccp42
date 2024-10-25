package br.unip.ccp42.negocio.entidade;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.traducao.AplicacaoLinkTD;
import br.unip.ccp42.negocio.mi.Classe;

/**
 * @author relson
 *
 */
public class AplicacaoLink extends EntidadeBase {
	
	private String url;
	private Classe aplicacao;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Classe getAplicacao () {
		return aplicacao;
	}
	
	public void setAplicacao (Classe aplicacao){
		this.aplicacao = aplicacao;
	}
	
	@Override
	protected void setTradutorDados() {
		this.setTradutorDados(new AplicacaoLinkTD());	
	}
}