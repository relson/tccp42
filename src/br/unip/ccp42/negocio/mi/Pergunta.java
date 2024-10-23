package br.unip.ccp42.negocio.mi;

import java.sql.SQLException;
import java.util.ArrayList;
import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.traducao.PerguntaTD;

public class Pergunta extends EntidadeBase {
	private ArrayList<Opcao> opcoes;

	public void setOpcoes(ArrayList<Opcao> opcoes) {
		this.opcoes = opcoes;
	}
	/**
	 * Obt�m um lista de todas as opções possíveis da pergunta. 
	 * @return Lista com as opções da pergunta
	 * @throws Exception Quando acontece um erro ao tentar obter as opções
	 */
	public ArrayList<Opcao> getOpcoes() throws Exception {
		
		if (this.opcoes == null) {
			PerguntaTD perguntaTD = new PerguntaTD();
			
			try {
				this.opcoes = perguntaTD.obterOpcoes(this);
			} catch (SQLException e) {
				throw new 
					Exception("Erro ao tentar obter as opções para a pergunta.\n" 
							+ e.getMessage());
			}
		}
		
		
		return opcoes;
	}
	
	@Override
	protected void setTradutorDados() {
		this.setTradutorDados(new PerguntaTD());		
	}
	
	public void configurarNaoSeAplica(Classe classe) throws SQLException, ClassNotFoundException {
		PerguntaTD perguntaTD = new PerguntaTD();
		
		perguntaTD.configurarNaoSeAplica(this, classe);
	}
	
	/**
	 * Exclui todas a op��es do perfil da pergunta para que ent�o
	 * possa ser feita sua reavalia��o
	 * @throws Exception
	 */
	public  void reclassificar() throws Exception{
		for(Opcao opcao:this.getOpcoes()){
			desvincularOpcao(opcao);
		}
	}
	private void desvincularOpcao(Opcao opcao) {
		PerguntaTD perguntaTD = new PerguntaTD();
		
		perguntaTD.desvincularOpcao(opcao);
	}
}