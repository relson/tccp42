package br.unip.ccp42.dados.traducao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.TradutorBase;
import br.unip.ccp42.dados.util.Utils;
import br.unip.ccp42.negocio.mi.Classe;
import br.unip.ccp42.negocio.mi.Opcao;

public class ClasseTD extends TradutorBase {

	@Override
	protected EntidadeBase obterEntidade(ResultSet resultSet)
			throws SQLException {
		Classe classe = new Classe();
		
		classe.setCodigo(resultSet.getInt(COLUNA_CODIGO));
		classe.setNome(resultSet.getString(COLUNA_NOME));
		return (EntidadeBase)classe;
	}

	@Override
	public EntidadeBase obterPorCodigo(int codigo) throws SQLException,
			ClassNotFoundException {
		Classe classe = new Classe();
		
		String sql = this.getSQLTodos();
		
		sql += " WHERE  codigo_entidade = ?";
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, codigo);
				
		if (resultSet.next())
		{
			classe = (Classe)this.obterEntidade(resultSet);
		}
		
		utils.finalizarResultSet(resultSet);

		return classe;
	}

	@Override
	public ArrayList<EntidadeBase> obterTodos() throws SQLException,
			ClassNotFoundException {
		ArrayList<EntidadeBase> opcoes = new ArrayList<EntidadeBase>();
		
		String sql = obterSQLTodos();
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql);
				
		while (resultSet.next())
		{
			opcoes.add(this.obterEntidade(resultSet));
		}
		
		utils.finalizarResultSet(resultSet);

		return opcoes;
	}
	
	private String obterSQLTodos() {
		StringBuffer sbSQL = new StringBuffer();
		
		sbSQL
			.append(" SELECT ")
			.append("	codigo_entidade, ")
			.append("   nome_entidade ")
			.append(" FROM ")
			.append("   aplicacao ");
		
		return sbSQL.toString();
	}
	
	@Override
	protected void setNomeTabela() {
		this.setNomeTabela("aplicacao");
	}

	@Override
	public void excluir(EntidadeBase entidade) throws SQLException,
			ClassNotFoundException, Exception {
		Classe classe = (Classe)entidade;
		
		this.excluirOpcoesClasse(classe);
		
		String sql = this.getSQLDelete();
		Object[] parametros = this.getParametrosDelete(entidade);
		
		Utils utils = new Utils();
		
		utils.executarComandoSQL(sql, parametros);
	}

	@Override
	public void inserir(EntidadeBase entidade) throws SQLException,
			ClassNotFoundException, Exception {
		// TODO Implementar Método

		String sql = obterSQLInsert();
		Object[] parametros = this.getParametrosInsert(entidade);

		Utils utils = new Utils();

		ResultSet resultSet = utils.obterResultSet(sql, parametros);

		if (resultSet.next()) {
			entidade.setCodigo(resultSet.getInt(COLUNA_CODIGO));
		}

		utils.finalizarResultSet(resultSet);	}

	private Object[] getParametrosInsert(EntidadeBase entidade) {
		Classe classe = (Classe) entidade;
		Object[] parametros = { classe.getNome() };
		return parametros;
	}

	private String obterSQLInsert() {
		String sqlInsert = "INSERT INTO aplicacao(nome_entidade) VALUES (?) RETURNING codigo_entidade";
		return sqlInsert;
	}

	/**
	 * TODO Documentar Método
	 * @param classe
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void excluirOpcoesClasse(Classe classe) throws SQLException, ClassNotFoundException{
		StringBuffer sbSQLDelete = new StringBuffer();
		
		sbSQLDelete
			.append(" DELETE FROM aplicacao_pergunta_opcao ")
			.append(" WHERE codigo_aplicacao = ? ");
			
		Utils utils = new Utils();
		
		Object[] parametros = this.getParametrosDelete(classe);
		
		utils.executarComandoSQL(sbSQLDelete.toString(), parametros);
	}
	
	public void inserirOpcaoPerfil(Classe classe, Opcao opcao) throws SQLException, ClassNotFoundException {
		StringBuffer sbSQLInserirOpcaoPerfil = new StringBuffer();
		
		sbSQLInserirOpcaoPerfil
			.append(" INSERT INTO aplicacao_pergunta_opcao( ")
			.append("	codigo_aplicacao, codigo_pergunta_opcao) ")
			.append(" VALUES (?, ?)");
		
		Utils utils = new Utils();
		
		Object[] parametros = this.getParametrosInserirOpcao(classe, opcao);
		
		utils.executarComandoSQL(sbSQLInserirOpcaoPerfil.toString(), parametros);
	}

	private Object[] getParametrosInserirOpcao(Classe classe, Opcao opcao) {
		Object[] parametros = {
				classe.getCodigo(),
				opcao.getCodigo() 
		};
		
		return parametros;
	}
}
