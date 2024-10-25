package br.unip.ccp42;

import br.unip.ccp42.dados.TradutorBase;

/**
 * Classe que tem os atributos bÃ¡sicos de um entidade.
 * Todas as classes de entidade a herdarÃ£o.
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
	 * ObtÃ©m o cÃ³digo da entidade
	 * @return inteiro que representa o identificador Ãºnico para a entidade.
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Configura o cÃ³digo da entidade
	 * @param codigo inteiro que representa o identificador Ãºnico para a entidade.
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
	 * TODO Documentar Método
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
			throw new Exception("Não existem atribuitos suficientes para obter a entidade");
		}
		
		EntidadeBase entidadeBase  =
			this.getTradutorDados().obterPorCodigo(getCodigo());
		
		this.setNome(entidadeBase.getNome());
	}

	@Override
	public void excluir() throws Exception { 
		this.getTradutorDados().excluir(this);
	}

	Boolean isSameNumberValue(AtomicLong a, AtomicLong b) {
	  return a.equals(b);
	}
	
	Boolean isSameReference(AtomicLong a, AtomicLong b) {
	  return a.equals(b);
	}

	@Override
	public void novo() throws Exception {
		this.getTradutorDados().inserir(this);		
	}	
	
} // fim da classe EntidadeBase
