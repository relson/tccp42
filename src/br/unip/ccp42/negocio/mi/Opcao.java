package br.unip.ccp42.negocio.mi;

import java.sql.SQLException;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.traducao.OpcaoTD;
import br.unip.ccp42.dados.traducao.PerguntaTD;

public class Opcao extends EntidadeBase{
	
	private Pergunta pergunta;	
	
	/**
	 * @return the pergunta
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	protected Pergunta getPergunta() throws SQLException, ClassNotFoundException {
		
		if (pergunta == null){
			PerguntaTD perguntaTD = new PerguntaTD();
			
			this.pergunta = (Pergunta)perguntaTD.obterPorOpcao(this);
		}
		
		return pergunta;
	}



	/**
	 * @param pergunta the pergunta to set
	 */
	protected void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}



	@Override
	protected void setTradutorDados() {
		this.setTradutorDados(new OpcaoTD());
	}

}
