package br.unip.ccp42.apresentacao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.traducao.AplicacaoLinkTD;
import br.unip.ccp42.dados.traducao.OpcaoTD;
import br.unip.ccp42.negocio.entidade.AplicacaoLink;
import br.unip.ccp42.negocio.entidade.Usuario;
import br.unip.ccp42.negocio.mi.Opcao;

public class UsuarioPerfil {
	
	private Usuario usuario; 
	
	// Implementar
	private ArrayList<Opcao> opcoes;
	
	/**
	 * @return the opcoes
	 */
	protected ArrayList<Opcao> getOpcoes() {
		
		if (opcoes == null) {
			OpcaoTD opcaoTD = new OpcaoTD();
			
			this.opcoes = opcaoTD.obterPorUsuario(this.usuario);
		}
		
		return opcoes;
	}

	/**
	 * @param opcoes the opcoes to set
	 */
	protected void setOpcoes(ArrayList<Opcao> opcoes) {		
		this.opcoes = opcoes;
	}

	public UsuarioPerfil() {		
	}
	
	/**
	 * @return the usuario
	 */
	protected Usuario getUsuario() {
		return usuario;
	}



	/**
	 * @param usuario the usuario to set
	 */
	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public ArrayList<AplicacaoLink> getAplicacoes()
	{
		//Indutor indutor  = new Indutor(this.getUsuario());
		
		AplicacaoLinkTD aplicacoes = new AplicacaoLinkTD();
		ArrayList<AplicacaoLink> list = new ArrayList<AplicacaoLink>();
		
		try {
			for(EntidadeBase app:aplicacoes.obterTodos())
			{
				list.add((AplicacaoLink)app);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;		
	} // fim do método getAplicacoes
} // fim da classe UsuarioPerfil
