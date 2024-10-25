package br.unip.ccp42.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.util.Utils;

/**
 * Classe base para a tradu√ß√£o de dados.
 * @author relson
 */
public abstract class TradutorBase implements TraducaoDadosCRUD{
	/**
	 * Constante para o √≠ndice da coluna c√≥digo da entidade base
	 */
	protected final int COLUNA_CODIGO = 1;
	/**
	 * Constante para o √≠ndice da coluna nome da entidade base.
	 */
	protected final int COLUNA_NOME = 2;
	
	private String nomeTabela;

	/**
	 * TODO Documentar MÈtodo
	 * @return the nomeTabela
	 */
	protected String getNomeTabela() {
		return nomeTabela;
	}

	/**
	 * TODO Documentar MÈtodo
	 * @param nomeTabela o nome da tabela a ser configurado
	 */
	protected void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	/**
	 * TODO Documentar MÈtodo
	 */
	protected abstract void setNomeTabela();
	
	/**
	 * TODO Documentar construtor
	 */
	public TradutorBase() {
		super();
		this.setNomeTabela();
	}
	
	/**
	 * Obt√©m uma lista com todas as entidades do tipo herdado no entanto
	 * empacotadas como entidade base.
	 * Uma convers√£o seria necessario para acessar os atributos e MÈtodos da classe.
	 * 
	 * @return ArrayList com todas as entidades
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public abstract ArrayList<EntidadeBase> obterTodos() throws SQLException, ClassNotFoundException;
	
	/**
	 * Obt√©m a entidade pelo identificador √∫nico, sua chave prim√°ria.
	 * @param codigo da chave do objeto, c√≥digo √∫nico no sistema, pois
	 * 		  todos herdam da tabela entidade base.
	 * @return Objeto empacotado com EntidadeBase
	 * @throws SQLException se um erro no SGBD acontecer
	 * @throws ClassNotFoundException 
	 */
	public abstract EntidadeBase obterPorCodigo(int codigo) throws SQLException, ClassNotFoundException;
	
	/**
	 * Converte uma linha do ResultSet em um entidade empacotada com EntidadeBase
	 * @param resultSet posicionado com no cursor que deseja se obter o objeto.
	 * @return objeto empacotado com EntidadeBase
	 * @throws SQLException
	 */
	protected abstract EntidadeBase obterEntidade(ResultSet resultSet) throws SQLException;
	
	/**
	 * TODO Documentar MÈtodo
	 * @param entidade
	 * @return
	 */
	protected Object[] getParametrosDelete(EntidadeBase entidade) {
		Object[] parametros = {
				entidade.getCodigo()
		};
		
		return parametros;
	}
	
	/**
	 * TODO Documentar MÈtodo
	 * @param entidade
	 * @return
	 */
	protected Object[] getParametrosUpdate(EntidadeBase entidade) {
		Object[] parametros = {
				entidade.getNome(),
				entidade.getCodigo()
		};
		
		return parametros;
	}
	
	/**
	 * TODO Documentar MÈtodo
	 * @return
	 */
	protected String getSQLDelete() {
		StringBuffer sbSQL = new StringBuffer();
		
		sbSQL
			.append("DELETE FROM ")
			.append(this.getNomeTabela())
			.append(" WHERE codigo_entidade = ? ");
		
		return sbSQL.toString();
	}
	
	/**
	 * TODO Documentar MÈtodo
	 * @return
	 */
	protected String getSQLUpdate() {
		StringBuffer sbSQL = new StringBuffer();
		
		sbSQL
			.append(" UPDATE ")
			.append(this.getNomeTabela())
			.append(" SET nome_entidade = ? ")
			.append(" WHERE codigo_entidade = ? ");
		
		return sbSQL.toString();
	}
		
	public void atualizar(EntidadeBase entidade) throws SQLException, ClassNotFoundException, Exception {
		String sql = this.getSQLUpdate();
		Object[] parametros = this.getParametrosUpdate(entidade);
		
		Utils utils = new Utils();
		
		utils.executarComandoSQL(sql, parametros);		
	}
	@SuppressWarnings("unchecked")
	public <T extends EntidadeBase> T getPorCodigo(int codigo) throws SQLException, ClassNotFoundException {
		T entidade = null;
		
		String sql = this.getSQLTodos();
		
		sql += " WHERE  codigo_entidade = ?";
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, codigo);
				
		if (resultSet.next())
		{
			entidade = (T) this.obterEntidade(resultSet);				
		}
		
		utils.finalizarResultSet(resultSet);

		return entidade;
	}

	/**
	 * TODO Documentar MÈtodo
	 * @return
	 */
	protected String getSQLTodos() {
		StringBuffer sbSQL = new StringBuffer();
		
		sbSQL
			.append(" SELECT ")
			.append("	codigo_entidade, ")
			.append("   nome_entidade ")
			.append(" FROM ")
			.append(this.getNomeTabela());
		
		return sbSQL.toString();
	}
}