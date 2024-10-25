package br.unip.ccp42.negocio;

import java.util.ArrayList;

import br.unip.ccp42.dados.traducao.PerguntaTD;
import br.unip.ccp42.negocio.entidade.Usuario;
import br.unip.ccp42.negocio.mi.Hipotese;
import br.unip.ccp42.negocio.mi.Pergunta;

public class Analisador {
	
	private ArrayList<Hipotese> hipoteses = new ArrayList<Hipotese>();
	private Usuario usuario;

	/**
	 * @return Uma coleção de Hipoteses
	 */
	public ArrayList<Hipotese> getHipoteses() {		
		return hipoteses;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param hipoteses Colecao de Hipoteses a ser configurada.
	 */
	public void setHipoteses(ArrayList<Hipotese> hipotese) {
		this.hipoteses = hipotese;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Construtor sem argumentos
	 */
	public Analisador() {
		
	}

	/**
	 * Construtor que configura o atributo obrigatório para um novo 
	 * Analisador.
	 * @param usuario
	 */
	public Analisador(Usuario usuario){
		this.usuario = usuario;
	}
	
	/**
	 * Obtém a próxima pergunta sem resposta para o perfil do usuário.
	 * @return Objeto pergunta configurada do banco de dados.
	 * @throws Exception 
	 */
	public Pergunta obterProximaPergunta() throws Exception{
		PerguntaTD perguntaTD = new PerguntaTD();
		
		Pergunta pergunta = perguntaTD.obterProxima(usuario);
		
		return pergunta;
	}
}  
