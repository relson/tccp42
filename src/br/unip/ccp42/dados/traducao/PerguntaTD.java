package br.unip.ccp42.dados.traducao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.TradutorBase;
import br.unip.ccp42.dados.util.Utils;
import br.unip.ccp42.negocio.entidade.Usuario;
import br.unip.ccp42.negocio.mi.Classe;
import br.unip.ccp42.negocio.mi.Opcao;
import br.unip.ccp42.negocio.mi.Pergunta;

public class PerguntaTD extends TradutorBase {
	
	public PerguntaTD() {
		super();
	}

	@Override
	protected EntidadeBase obterEntidade(ResultSet resultSet) throws SQLException {
		Pergunta pergunta = new Pergunta();
		
		pergunta.setCodigo(resultSet.getInt(COLUNA_CODIGO));
		pergunta.setNome(resultSet.getString(COLUNA_NOME));		
	
	return (EntidadeBase)pergunta;

	}

	@Override
	public EntidadeBase obterPorCodigo(int codigo) throws SQLException, ClassNotFoundException {
		Pergunta pergunta = new Pergunta();
		
		String sql = obterSQLTodos();
		
		sql += " WHERE codigo_entidade = ?";
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, codigo);
		
		if (resultSet.next())
		{
			pergunta = (Pergunta)this.obterEntidade(resultSet);
		}
				
		utils.finalizarResultSet(resultSet);
				
		return pergunta;
	}

	@Override
	public ArrayList<EntidadeBase> obterTodos() throws SQLException, ClassNotFoundException {
		ArrayList<EntidadeBase> perguntas = new ArrayList<EntidadeBase>();
		
		String sql = obterSQLTodos();
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql);
		
		while (resultSet.next())
		{
			perguntas.add(this.obterEntidade(resultSet));
		}
				
		utils.finalizarResultSet(resultSet);
				
		return perguntas;
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception 
	 */
	public Pergunta obterProxima(Usuario usuario) throws Exception {
		
		Pergunta pergunta = new Pergunta();
		
		String sql = obterSQLTodos();
		
		sql += " WHERE NOT EXISTS (" +
			   "	SELECT " +
			   "		1" +
			   "	FROM usuario_pergunta_opcao" +
			   "	   INNER JOIN 	pergunta_opcao ON" +
			   "			pergunta_opcao.codigo_entidade = usuario_pergunta_opcao.codigo_pergunta_opcao " +
			   "	WHERE " +
			   "		pergunta_opcao.codigo_pergunta = pergunta.codigo_entidade AND " +
			   "		usuario_pergunta_opcao.codigo_usuario = ?" +	
			   ")";
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, usuario.getCodigo());
		
		if (resultSet.next())
		{
			pergunta = (Pergunta)this.obterEntidade(resultSet);
		}
		else
		{
			throw new Exception("NÃ£o existem perguntas sem resposta para o perfil do usuario '" + usuario.getNome() + "'");
		}
		utils.finalizarResultSet(resultSet);
				
		return pergunta;
	}
	
	/**
	 * 
	 * @param classe
	 * @return
	 * @throws Exception 
	 */
	public Pergunta obterProxima(Classe classe) throws Exception {
		
		Pergunta pergunta = new Pergunta();
		
		String sql = obterSQLTodos();
		
		sql += " WHERE NOT EXISTS (" +
			   "	SELECT " +
			   "		1" +
			   "	FROM aplicacao_pergunta_opcao" +
			   "	   INNER JOIN 	pergunta_opcao ON" +
			   "			pergunta_opcao.codigo_entidade = aplicacao_pergunta_opcao.codigo_pergunta_opcao " +
			   "	WHERE " +
			   "		pergunta_opcao.codigo_pergunta = pergunta.codigo_entidade AND " +
			   "		aplicacao_pergunta_opcao.codigo_aplicacao = ?" +	
			   ")";
		
		sql += " AND NOT EXISTS (" +
		   "	SELECT " +
		   "		1" +
		   "	FROM aplicacao_pergunta_nao_se_aplica" +
		   "	WHERE " +
		   "			pergunta.codigo_entidade = aplicacao_pergunta_nao_se_aplica.codigo_pergunta " +
		   "			AND	aplicacao_pergunta_nao_se_aplica.codigo_aplicacao = ? " +
		   ")";
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(
				sql, 
				new Object[] {
						classe.getCodigo(),
						classe.getCodigo()
				}
		);
		
		if (resultSet.next())
		{
			pergunta = (Pergunta)this.obterEntidade(resultSet);
		}
		else
		{
			throw new Exception("N&atilde;o existem perguntas sem resposta para o perfil da aplica&ccedil;&atilde;o '" + classe.getNome() + "'");
		}
		utils.finalizarResultSet(resultSet);
				
		return pergunta;
	}
	
	/**
	 * TODO Documentar Método
	 * @param pergunta
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	private void excluirOpcoesUsuarioPergunta(Pergunta pergunta) throws SQLException, ClassNotFoundException{
		StringBuffer sbSQLDelete = new StringBuffer();
		
		sbSQLDelete
			.append(" DELETE FROM usuario_pergunta_opcao ")
			.append(" WHERE codigo_pergunta_opcao IN ( ")
			.append(" SELECT ")
			.append(" codigo_entidade ")
			.append(" FROM ")
			.append(" pergunta_opcao ")
			.append(" WHERE ")
			.append(" codigo_pergunta = ? )");
		
		Utils utils = new Utils();
		
		Object[] parametros = this.getParametrosDelete(pergunta);
		
		utils.executarComandoSQL(sbSQLDelete.toString(), parametros);
	}
	
	private String obterSQLTodos() {
		
		StringBuffer sbSQL = new StringBuffer();
		
		sbSQL.append(" SELECT ");
		sbSQL.append(" 	codigo_entidade, ");
		sbSQL.append("	nome_entidade");		
		sbSQL.append(" FROM pergunta ");
		
		return sbSQL.toString();
	}
	
	public ArrayList<Opcao> obterOpcoes(Pergunta pergunta) 
			throws SQLException, ClassNotFoundException {
		
		OpcaoTD opcaoTD = new OpcaoTD(); 
		
		return opcaoTD.obterPorPergunta(pergunta);
	}

	@Override
	public void atualizar(EntidadeBase entidade) throws Exception {
		Pergunta pergunta = (Pergunta)entidade;
		
		for (Opcao  opcao:pergunta.getOpcoes()) {
			opcao.atualizar();
		}
		
		super.atualizar(entidade);
	}

	@Override
	public void excluir(EntidadeBase entidade) throws Exception {
		Pergunta pergunta = (Pergunta)entidade;
		
		this.excluirOpcoesUsuarioPergunta(pergunta);
		
		for(Opcao opcao:pergunta.getOpcoes()){
			opcao.excluir();
		}		
		
		String sql = this.getSQLDelete();
		Object[] parametros = this.getParametrosDelete(entidade);
		
		Utils utils = new Utils();
		
		utils.executarComandoSQL(sql, parametros);
	}

	@Override
	public void inserir(EntidadeBase entidade) throws Exception {
		String sql = obterSQLInsert();
		Object[] parametros = this.getParametrosInsert(entidade);
				
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, parametros);
		
		if (resultSet.next())
		{
			entidade.setCodigo(resultSet.getInt(COLUNA_CODIGO));
		}
				
		utils.finalizarResultSet(resultSet);
		
		Pergunta pergunta = (Pergunta)entidade;
		
		OpcaoTD opcaoTD = new OpcaoTD();
		
		opcaoTD.setPergunta(pergunta);
		
		for(Opcao opcao:pergunta.getOpcoes()) {
			opcaoTD.inserir(opcao);
		}
	}

	private Object[] getParametrosInsert(EntidadeBase entidade) {
		String nome = entidade.getNome();
		
		Object[] parametros = {
				nome
			};		
		return parametros;
	}

	private String obterSQLInsert() {
		String sqlInsert = "INSERT INTO pergunta(nome_entidade) VALUES (?) RETURNING codigo_entidade";
		return sqlInsert;
	}

	@Override
	protected void setNomeTabela() {
		this.setNomeTabela("pergunta");
	}

	public void configurarNaoSeAplica(Pergunta pergunta, Classe classe) throws SQLException, ClassNotFoundException {
		
		String sql = obterSQLInsertNaoSeAplica();
		
		Utils utils = new Utils();
		
		Object[] parametros = new Object[] {
				classe.getCodigo(),
				pergunta.getCodigo()								
		};
		
		utils.executarComandoSQL(sql, parametros);
	}

	private String obterSQLInsertNaoSeAplica() {
		StringBuffer sbSQL = new StringBuffer();
		
		sbSQL
			.append(" INSERT INTO aplicacao_pergunta_nao_se_aplica(")
			.append(" codigo_aplicacao, codigo_pergunta)")
            .append(" VALUES (?, ?) ");
		return sbSQL.toString();
	}

	public Pergunta obterPorOpcao(Opcao opcao) {
		// TODO Implementar método
		return null;
	}

	public void desvincularOpcao(Opcao opcao) {
		
	}
}