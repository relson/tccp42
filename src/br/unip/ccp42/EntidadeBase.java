package br.unip.ccp42;

import br.unip.ccp42.dados.TradutorBase;

/**
 * Classe que tem os atributos básicos de um entidade.
 * Todas as classes de entidade a herdarão.
 * @author relson
 *
 */
public abstract class EntidadeBase implements CRUD {
	
	private int codigo;
	private String nome;
	
	private TradutorBase tradutorDados;
	
	public EntidadeBase(){
		this.setTradutorDados();
	}
	
	/**
	 * Obtém o código da entidade
	 * @return inteiro que representa o identificador único para a entidade.
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Configura o código da entidade
	 * @param codigo inteiro que representa o identificador único para a entidade.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	protected abstract void setTradutorDados();
	
	protected void setTradutorDados(TradutorBase tradutorDados) {
		this.tradutorDados = tradutorDados;
	}
	
	/**
	 * TODO Documentar M�todo
	 * @return
	 */
	protected TradutorBase getTradutorDados() {
		return this.tradutorDados;
	}
	
	@Override
	public void atualizar() throws Exception {
		this.getTradutorDados().atualizar(this);
	}

	@Override
	public void carregar() throws Exception {
		
		if(this.getCodigo() == 0) {
			throw new Exception("N�o existem atribuitos suficientes para obter a entidade");
		}
		
		EntidadeBase entidadeBase  =
			this.getTradutorDados().obterPorCodigo(getCodigo());
		
		this.setNome(entidadeBase.getNome());
	}

	@Override
	public void excluir() throws Exception { 
		this.getTradutorDados().excluir(this);
	}

	@Override
	public void novo() throws Exception {
		this.getTradutorDados().inserir(this);		
	}	
	
} // fim da classe EntidadeBase
